package io.geekidea.boot.framework.interceptor;

import io.geekidea.boot.common.constant.CommonConstant;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2023/12/3
 **/
public abstract class BaseExcludeMethodInterceptor extends BaseMethodInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 如果是排除路径，则跳过处理
            Boolean isExcludePath = (Boolean) request.getAttribute(CommonConstant.REQUEST_PARAM_EXCLUDE_PATH);
            if (isExcludePath != null && isExcludePath) {
                return true;
            }
            return preHandleMethod(request, response, handlerMethod);
        }
        return true;
    }

}
