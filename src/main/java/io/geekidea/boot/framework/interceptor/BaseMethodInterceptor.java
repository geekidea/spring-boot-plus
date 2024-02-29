package io.geekidea.boot.framework.interceptor;

import io.geekidea.boot.auth.annotation.AppUserRole;
import io.geekidea.boot.auth.annotation.IgnoreLogin;
import io.geekidea.boot.auth.annotation.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author geekidea
 * @date 2023/12/3
 **/
public abstract class BaseMethodInterceptor implements HandlerInterceptor {

    /**
     * 只处理方法的控制器
     *
     * @param request
     * @param response
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    protected abstract boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return preHandleMethod(request, response, handlerMethod);
        }
        return true;
    }

    /**
     * 判断方法上和类上是否存在@Login注解
     *
     * @param handlerMethod
     * @return
     */
    protected boolean existsLoginAnnotation(HandlerMethod handlerMethod) {
        // 从方法上获取登录注解
        Login login = handlerMethod.getMethodAnnotation(Login.class);
        if (login != null) {
            return true;
        }
        // 从类上获取登录注解
        login = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Login.class);
        if (login != null) {
            return true;
        }
        return false;
    }

    /**
     * 判断方法上和类上是否存在@IgnoreLogin注解
     *
     * @param handlerMethod
     * @return
     */
    protected boolean existsIgnoreLoginAnnotation(HandlerMethod handlerMethod) {
        IgnoreLogin ignoreLogin = handlerMethod.getMethodAnnotation(IgnoreLogin.class);
        if (ignoreLogin != null) {
            return true;
        }
        ignoreLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(IgnoreLogin.class);
        if (ignoreLogin != null) {
            return true;
        }
        return false;
    }


    /**
     * 获取方法上和类上的@AppUserRole注解
     *
     * @param handlerMethod
     * @return
     */
    protected AppUserRole getAppUserRoleAnnotation(HandlerMethod handlerMethod) {
        AppUserRole appUserRole = handlerMethod.getMethodAnnotation(AppUserRole.class);
        if (appUserRole != null) {
            return appUserRole;
        }
        appUserRole = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AppUserRole.class);
        if (appUserRole != null) {
            return appUserRole;
        }
        return null;
    }

    /**
     * 判断当前请求的路径，是否是不需要的路径
     *
     * @param request
     * @param ignoreLoginPaths
     * @return
     */
    protected boolean isIgnoreLoginPath(HttpServletRequest request, List<String> ignoreLoginPaths) {
        String servletPath = request.getServletPath();
        if (CollectionUtils.isNotEmpty(ignoreLoginPaths)) {
            for (String ignoreLoginPath : ignoreLoginPaths) {
                AntPathMatcher antPathMatcher = new AntPathMatcher();
                boolean match = antPathMatcher.match(ignoreLoginPath, servletPath);
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前请求的路径，是否是需要登录的路径
     *
     * @param request
     * @param loginPaths
     * @return
     */
    protected boolean isCheckLoginPath(HttpServletRequest request, List<String> loginPaths) {
        String servletPath = request.getServletPath();
        if (CollectionUtils.isNotEmpty(loginPaths)) {
            for (String loginPath : loginPaths) {
                AntPathMatcher antPathMatcher = new AntPathMatcher();
                boolean match = antPathMatcher.match(loginPath, servletPath);
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

}
