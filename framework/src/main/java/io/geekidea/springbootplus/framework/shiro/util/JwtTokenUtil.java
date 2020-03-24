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

package io.geekidea.springbootplus.framework.shiro.util;

import io.geekidea.springbootplus.config.properties.JwtProperties;
import io.geekidea.springbootplus.framework.util.HttpServletRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtToken工具类
 *
 * @author geekidea
 * @date 2019-10-03
 * @since 1.3.0.RELEASE
 **/
@Slf4j
@Component
public class JwtTokenUtil {

    private static String tokenName;

    public JwtTokenUtil(JwtProperties jwtProperties) {
        tokenName = jwtProperties.getTokenName();
        log.debug("tokenName:{}", tokenName);
    }

    /**
     * 获取token名称
     *
     * @return
     */
    public static String getTokenName() {
        return tokenName;
    }

    /**
     * 从请求头或者请求参数中
     *
     * @return
     */
    public static String getToken() {
        return getToken(HttpServletRequestUtil.getRequest());
    }

    /**
     * 从请求头或者请求参数中
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(tokenName);
        if (StringUtils.isBlank(token)) {
            // 从请求参数中获取token
            token = request.getParameter(tokenName);
        }
        return token;
    }
}
