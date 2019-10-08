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

import com.alibaba.fastjson.JSON;
import io.geekidea.springbootplus.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 设置允许跨域
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
public class CrossDomainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader("Access-Control-Request-Headers","*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type,token");
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "*");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CrossDomainFilter...");
        System.out.println(request.getRequestURL());
        System.out.println();

        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setCharacterEncoding("UTF-8");
            PrintWriter w = response.getWriter();
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(200);
            apiResult.setMsg("ok...");
            w.append(JSON.toJSONString(apiResult));
            return;
        }

        filterChain.doFilter(servletRequest, httpServletResponse);

    }

    @Override
    public void destroy() {

    }
}
