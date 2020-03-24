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

package io.geekidea.springbootplus.framework.shiro.cache;


import io.geekidea.springbootplus.framework.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserRedisVo;
import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserVo;

/**
 * 登录信息Redis缓存操作服务
 *
 * @author geekidea
 * @date 2019-09-30
 * @since 1.3.0.RELEASE
 **/
public interface LoginRedisService {

    /**
     * 缓存登录信息
     *
     * @param jwtToken
     * @param loginSysUserVo
     */
    void cacheLoginInfo(JwtToken jwtToken, LoginSysUserVo loginSysUserVo);


    /**
     * 刷新登录信息
     *
     * @param oldToken
     * @param username
     * @param newJwtToken
     */
    void refreshLoginInfo(String oldToken, String username, JwtToken newJwtToken);

    /**
     * 通过用户名，从缓存中获取登录用户LoginSysUserRedisVo
     *
     * @param username
     * @return
     */
    LoginSysUserRedisVo getLoginSysUserRedisVo(String username);

    /**
     * 获取登录用户对象
     *
     * @param username
     * @return
     */
    LoginSysUserVo getLoginSysUserVo(String username);

    /**
     * 通过用户名称获取盐值
     *
     * @param username
     * @return
     */
    String getSalt(String username);

    /**
     * 删除对应用户的Redis缓存
     *
     * @param token
     * @param username
     */
    void deleteLoginInfo(String token, String username);

    /**
     * 判断token在redis中是否存在
     *
     * @param token
     * @return
     */
    boolean exists(String token);

    /**
     * 删除用户所有登录缓存
     *
     * @param username
     */
    void deleteUserAllCache(String username);

}
