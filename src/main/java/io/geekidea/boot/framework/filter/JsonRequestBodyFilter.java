package io.geekidea.boot.framework.filter;

import io.geekidea.boot.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author geekidea
 * @date 2022/3/22
 **/
@Slf4j
public class JsonRequestBodyFilter implements Filter {

    private static final String APPLICATION_JSON = "application/json";
    private static final String METHOD_POST = "POST";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        JsonHttpServletRequestWrapper requestWrapper = null;
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                String method = httpServletRequest.getMethod();
                String contentType = httpServletRequest.getContentType();
                if (METHOD_POST.equalsIgnoreCase(method) && StringUtils.isNotBlank(contentType)) {
                    contentType = contentType.toLowerCase();
                    if (contentType.startsWith(APPLICATION_JSON)) {
                        requestWrapper = new JsonHttpServletRequestWrapper((HttpServletRequest) request);
                        String bodyString = requestWrapper.getBodyString();
                        requestWrapper.setAttribute(CommonConstant.REQUEST_PARAM_BODY_STRING, bodyString);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

}
