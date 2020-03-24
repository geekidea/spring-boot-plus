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

package io.geekidea.springbootplus.framework.shiro.service;

import io.geekidea.springbootplus.framework.shiro.jwt.JwtToken;

import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2020/3/24
 **/
public interface ShiroLoginService {
    /**
     * 如果(当前时间+倒计时) > 过期时间，则刷新token
     * 并更新缓存
     * 当前token失效，返回新token
     * 当前请求有效，返回状态码：460
     * 前端下次使用新token
     * 如果token继续发往后台，则提示，此token已失效，请使用新token，不在返回新token，返回状态码：461
     *
     * @param jwtToken
     * @param httpServletResponse
     * @throws Exception
     */
    void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse) throws Exception;

}
