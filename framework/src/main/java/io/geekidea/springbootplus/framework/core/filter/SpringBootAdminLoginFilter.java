/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.framework.core.filter;

import io.geekidea.springbootplus.framework.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Xss过滤器
 *
 * @author geekidea
 * @date 2019-10-10
 * @since 1.3.1.RELEASE
 **/
@Slf4j
@WebFilter(filterName = "adminLoginFilter", urlPatterns = "/admin/*", asyncSupported = true)
public class SpringBootAdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SpringBootAdminLoginFilter init.......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestedSessionId() == null){
            filterChain.doFilter(request, servletResponse);
            return;
        }
        String requestPath = request.getRequestURI();
        if ("/api/admin/login".equals(requestPath)){
            log.info("登陆放行");
            filterChain.doFilter(request, servletResponse);
            return;
        }

        Boolean adminIsLogin = (Boolean) request.getSession().getAttribute(CommonConstant.ADMIN_LOGIN_SESSION);
        // 如果未登陆，则抛出错误提示，或重定向到登陆页面
        if (!Boolean.TRUE.equals(adminIsLogin)){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/api/static/login.html");
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
