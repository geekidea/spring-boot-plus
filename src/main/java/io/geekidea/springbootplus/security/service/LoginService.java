/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.security.service;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.security.param.LoginParam;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  登录服务接口
 * </p>
 * @author geekidea
 * @date 2019-05-23
 **/
public interface LoginService {

    ApiResult login(LoginParam loginParam);

    void refreshToken(HttpServletResponse response, Jws<Claims> jws);

}
