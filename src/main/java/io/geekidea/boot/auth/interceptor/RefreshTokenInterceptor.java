package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.service.LoginRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginRedisService loginRedisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        loginRedisService.refreshToken();
        return true;
    }

}
