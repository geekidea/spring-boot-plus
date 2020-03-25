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

package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.SysUser;
import io.geekidea.springbootplus.system.param.LoginParam;
import io.geekidea.springbootplus.system.vo.LoginSysUserTokenVo;

import javax.servlet.http.HttpServletRequest;

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
     * 登录
     *
     * @param loginParam
     * @return
     * @throws Exception
     */
    LoginSysUserTokenVo login(LoginParam loginParam) throws Exception;


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
