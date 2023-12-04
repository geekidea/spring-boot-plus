package io.geekidea.boot.auth.interceptor;

import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.config.properties.BootProperties;
import io.geekidea.boot.framework.interceptor.BaseMethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 排除路径拦截器
 *
 * @author geekidea
 * @date 2023/12/03
 **/
@Slf4j
public class ExcludePathInterceptor extends BaseMethodInterceptor {

    @Autowired
    private BootProperties bootProperties;

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        String servletPath = request.getServletPath();
        List<String> excludePaths = bootProperties.getExcludePaths();
        if (CollectionUtils.isNotEmpty(excludePaths)) {
            for (String excludePath : excludePaths) {
                AntPathMatcher antPathMatcher = new AntPathMatcher();
                boolean match = antPathMatcher.match(excludePath, servletPath);
                if (match) {
                    request.setAttribute(CommonConstant.REQUEST_PARAM_EXCLUDE_PATH, true);
                    break;
                }
            }
        }
        return true;
    }

}
