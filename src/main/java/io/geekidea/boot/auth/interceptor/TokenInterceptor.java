package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.auth.cache.TokenCache;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.framework.interceptor.BaseExcludeMethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器
 *
 * @author geekidea
 * @date 2023/12/03
 **/
@Slf4j
public class TokenInterceptor extends BaseExcludeMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        // 获取token
        String token = TokenUtil.getToken(request);
        if (StringUtils.isBlank(token)) {
            return true;
        }
        // 设置token值到当前线程中，避免重复获取
        TokenCache.set(token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenCache.remove();
    }

}
