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

package io.geekidea.springbootplus.filter;

import io.geekidea.springbootplus.core.properties.SpringBootPlusFilterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * 请求路径过滤器
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
public class RequestPathFilter implements Filter {

    private static String[] excludePaths;

    private boolean isEnabled;

    public RequestPathFilter(SpringBootPlusFilterProperties.FilterConfig filterConfig) {
        isEnabled = filterConfig.isEnabled();
        excludePaths = filterConfig.getExcludePaths();
        log.debug("isEnabled:" + isEnabled);
        log.debug("excludePaths:" + Arrays.toString(excludePaths));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isEnabled) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        String url = req.getRequestURL().toString();
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean isOut = true;
        if (ArrayUtils.isNotEmpty(excludePaths)) {
            for (String pattern : excludePaths) {
                if (pathMatcher.match(pattern, path)) {
                    isOut = false;
                    break;
                }
            }
        }
        if (isOut) {
            log.debug(url);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
