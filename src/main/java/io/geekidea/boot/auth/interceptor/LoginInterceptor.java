package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.annotation.IgnoreLogin;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.config.properties.LoginAdminProperties;
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.framework.exception.LoginTokenException;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.geekidea.boot.framework.interceptor.BaseMethodInterceptor;
import io.geekidea.boot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        IgnoreLogin ignoreLogin = handlerMethod.getMethodAnnotation(IgnoreLogin.class);
        if (ignoreLogin != null) {
            return true;
        }
        ignoreLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(IgnoreLogin.class);
        if (ignoreLogin != null) {
            return true;
        }
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        // 获取登录用户信息
        LoginRedisVo loginRedisVo = LoginUtil.getLoginRedisVo();
        if (loginRedisVo == null) {
            throw new LoginTokenException("登录已过期或登录信息不存在，请重新登录");
        }
        LoginVo loginVo = loginRedisVo.getLoginVo();
        String roleCode = loginVo.getRoleCode();
        boolean loginPermission = loginAdminProperties.isLoginPermission();
        boolean admin = loginVo.isAdmin();
        if (!admin && loginPermission) {
            Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
            if (permission != null) {
                // 从redis中获取权限列表
                List<String> permissions = loginRedisVo.getPermissions();
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

}
