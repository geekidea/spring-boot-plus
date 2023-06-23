package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.annotation.IgnoreLogin;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.auth.exception.LoginTokenException;
import io.geekidea.boot.auth.exception.LoginException;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.config.properties.LoginProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginProperties loginProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        System.out.println("requestUrl = " + requestUrl);
        // 如果访问的是控制器
        if (handler instanceof HandlerMethod) {
            // 排除不需要登录验证的路径 路径配置 注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            IgnoreLogin ignoreLogin = handlerMethod.getMethodAnnotation(IgnoreLogin.class);
            if (ignoreLogin != null) {
                return true;
            }
            ignoreLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(IgnoreLogin.class);
            if (ignoreLogin != null) {
                return true;
            }
            String token = TokenUtil.getToken(request);
            if (StringUtils.isBlank(token)) {
                throw new LoginTokenException("token不能为空");
            }
            // 获取登录用户信息
            LoginRedisVo loginRedisVo = LoginUtil.getLoginRedisVo();
            if (loginRedisVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
            LoginVo loginVo = loginRedisVo.getLoginVo();
            List<String> roleCodes = loginVo.getRoleCodes();
            boolean loginPermission = loginProperties.isLoginPermission();
            if (loginPermission) {
                Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
                if (permission != null) {
                    // 从redis中获取权限列表
                    List<String> permissions = loginRedisVo.getPermissions();
                    if (CollectionUtils.isEmpty(permissions)) {
                        throw new LoginException("当前用户未设置权限");
                    }
                    String value = permission.value();
                    String role = permission.role();
                    String[] roles = permission.roles();
                    if (!permissions.contains(value)) {
                        log.error("没有访问权限的登录用户：" + loginVo);
                        throw new LoginException("没有访问权限");
                    }
                    if (StringUtils.isNotBlank(role)) {
                        if (roleCodes.contains(role)) {
                            log.error("没有访问权限的登录用户：" + loginVo);
                            throw new LoginException("该角色没有访问权限");
                        }
                    }
                    if (ArrayUtils.isNotEmpty(roles)) {
                        boolean containRole = false;
                        for (String roleCode : roleCodes) {
                            if (ArrayUtils.contains(roles, roleCode)) {
                                containRole = true;
                                break;
                            }
                        }
                        if (!containRole) {
                            log.error("没有访问权限的登录用户：" + loginVo);
                            throw new LoginException("该角色没有访问权限");
                        }
                    }
                }
            }
        } else {
            String token = TokenUtil.getToken(request);
            if (StringUtils.isBlank(token)) {
                throw new LoginTokenException("token不能为空");
            }
            // 获取登录用户信息
            LoginVo loginVo = LoginUtil.getLoginVo();
            if (loginVo == null) {
                throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
            }
        }
        return true;
    }


}
