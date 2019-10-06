/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.common.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求路径过滤器
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
public class RequestPathFilter implements Filter {

    private static List<String> excludes = new ArrayList<>();

    static {
        // 控制台日志忽略spring boot admin访问路径
        excludes.add("/actuator");
        excludes.add("/instances");
        excludes.add("/logfile");
        excludes.add("/sba-settings.js");
        excludes.add("/assets/img/favicon.png");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        String url = req.getRequestURL().toString();

        boolean isOut = true;
        for (String p : excludes){
            if (path.startsWith(p)){
                isOut = false;
                break;
            }
        }
        if (isOut){
            log.debug("requestURL:"+url);
        }
        chain.doFilter(req,response);
    }

    @Override
    public void destroy() {

    }
}
