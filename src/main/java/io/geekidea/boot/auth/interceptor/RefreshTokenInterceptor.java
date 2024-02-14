package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.service.AppLoginRedisService;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import io.geekidea.boot.util.SystemTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Slf4j
public class RefreshTokenInterceptor extends BaseExcludeMethodInterceptor {

    @Autowired
    private LoginRedisService loginRedisService;

    @Autowired
    private AppLoginRedisService appLoginRedisService;

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        SystemType systemType = SystemTypeUtil.getSystemTypeByPath(request);
        if (SystemType.ADMIN == systemType) {
            loginRedisService.refreshToken();
        } else if (SystemType.APP == systemType) {
            appLoginRedisService.refreshToken();
        }
        return true;
    }

}
