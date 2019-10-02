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

package io.geekidea.springbootplus.shiro.service;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.shiro.param.LoginParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 登录服务接口
 * </p>
 *
 * @author geekidea
 * @date 2019-05-23
 **/
public interface LoginService {

    /**
     * 登陆
     *
     * @param loginParam
     * @return
     */
    ApiResult login(LoginParam loginParam, HttpServletResponse response);

    /**
     * 刷新token
     *
     * @param jwtToken
     * @param httpServletResponse
     */
    void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse);

    /**
     * 退出
     *
     * @param userName
     */
    void logout(String userName);

    /**
     * 获取用户角色
     *
     * @param id
     * @return
     */
    List<String> getUserRoles(Long id);

}
