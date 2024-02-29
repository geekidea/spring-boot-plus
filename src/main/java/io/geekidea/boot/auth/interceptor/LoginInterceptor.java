package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.auth.cache.LoginCache;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.config.properties.LoginAdminProperties;
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.framework.exception.LoginTokenException;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class LoginInterceptor extends BaseExcludeMethodInterceptor {

    @Autowired
    private LoginAdminProperties loginAdminProperties;

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 如果token
        String token = TokenUtil.getToken();
        LoginVo loginVo = null;
        if (StringUtils.isNotBlank(token)) {
            // 获取登录用户信息
            loginVo = LoginUtil.getLoginVo(token);
            if (loginVo != null) {
                // 将管理后台的登录信息保存到当前线程中
                LoginCache.set(loginVo);
            }
        }
        // 判断是否存在@IgnoreLogin，存在，则跳过
        boolean existsIgnoreLoginAnnotation = existsIgnoreLoginAnnotation(handlerMethod);
        if (existsIgnoreLoginAnnotation) {
            return true;
        }
        // 校验登录
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("请登录后再操作");
        }
        // 校验登录用户信息
        if (loginVo == null) {
            throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
        }
        // 获取登录信息判断
        String roleCode = loginVo.getRoleCode();
        boolean loginPermission = loginAdminProperties.isLoginPermission();
        boolean admin = loginVo.isAdmin();
        if (!admin && loginPermission) {
            Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
            if (permission != null) {
                // 从redis中获取权限列表
                List<String> permissions = loginVo.getPermissions();
                if (CollectionUtils.isEmpty(permissions)) {
                    throw new LoginException("当前用户未设置权限");
                }
                String value = permission.value();
                String role = permission.role();
                if (!permissions.contains(value)) {
                    log.error("没有访问权限的登录用户：" + loginVo);
                    throw new LoginException("没有访问权限");
                }
                if (StringUtils.isNotBlank(role)) {
                    if (roleCode.equals(role)) {
                        log.error("没有访问权限的登录用户：" + loginVo);
                        throw new LoginException("该角色没有访问权限");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginCache.remove();
    }

}
