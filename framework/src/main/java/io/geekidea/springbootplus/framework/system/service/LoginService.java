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

package io.geekidea.springbootplus.framework.system.service;

import io.geekidea.springbootplus.framework.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.framework.system.param.LoginParam;
import io.geekidea.springbootplus.framework.system.entity.SysUser;
import io.geekidea.springbootplus.framework.system.vo.LoginSysUserTokenVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @throws Exception
     */
    LoginSysUserTokenVo login(LoginParam loginParam) throws Exception;

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

    /**
     * 退出
     * @param request
     * @throws Exception
     */
    void logout(HttpServletRequest request) throws Exception;

    /**
     * 根据用户名获取系统用户对象
     *
     * @param username
     * @return
     * @throws Exception
     */
    SysUser getSysUserByUsername(String username) throws Exception;

    /**
     * 检查验证码是否正确
     *
     * @param verifyToken
     * @param code
     * @throws Exception
     */
    void checkVerifyCode(String verifyToken, String code) throws Exception;

}
