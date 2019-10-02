/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.shiro.cache.impl;

import io.geekidea.springbootplus.common.constant.CommonRedisKey;
import io.geekidea.springbootplus.shiro.jwt.JwtToken;
import io.geekidea.springbootplus.shiro.cache.LoginRedisService;
import io.geekidea.springbootplus.shiro.convert.ShiroMapstructConvert;
import io.geekidea.springbootplus.shiro.vo.ClientInfo;
import io.geekidea.springbootplus.shiro.vo.JwtTokenRedisVo;
import io.geekidea.springbootplus.shiro.vo.LoginSysUserRedisVo;
import io.geekidea.springbootplus.shiro.vo.LoginSysUserVo;
import io.geekidea.springbootplus.util.ClientInfoUtil;
import io.geekidea.springbootplus.util.HttpServletRequestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author geekidea
 * @date 2019-09-30
 * @since 1.3.0.RELEASE
 **/
@Service
public class LoginRedisServiceImpl implements LoginRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * key-value: 有过期时间-->token过期时间
     * 1. tokenMd5:jwtTokenRedisVo
     * 2. username:loginSysUserRedisVo
     * 3. username:salt
     * hash: 没有过期时间，统计在线的用户信息
     * username:num
     */
    @Override
    public void cacheLoginInfo(JwtToken jwtToken, LoginSysUserVo loginSysUserVo, boolean generate) {
        if (jwtToken == null) {
            throw new IllegalArgumentException("jwtToken不能为空");
        }
        if (loginSysUserVo == null) {
            throw new IllegalArgumentException("loginSysUserVo不能为空");
        }
        // token
        String token = jwtToken.getToken();
        // 盐值
        String salt = jwtToken.getSalt();
        // 登陆用户名称
        String username = loginSysUserVo.getUsername();
        // token md5值
        String tokenMd5 = DigestUtils.md5Hex(token);

        // Redis缓存JWT Token信息
        JwtTokenRedisVo jwtTokenRedisVo = ShiroMapstructConvert.INSTANCE.jwtTokenToJwtTokenRedisVo(jwtToken);

        // 用户客户端信息
        ClientInfo clientInfo = ClientInfoUtil.get(HttpServletRequestUtil.getRequest());

        // Redis缓存登陆用户信息
        // 将LoginSysUserVo对象复制到LoginSysUserRedisVo，使用mapstruct进行对象属性复制
        LoginSysUserRedisVo loginSysUserRedisVo = ShiroMapstructConvert.INSTANCE.loginSysUserVoToLoginSysUserRedisVo(loginSysUserVo);
        loginSysUserRedisVo.setSalt(salt);
        loginSysUserRedisVo.setClientInfo(clientInfo);

        // Redis过期时间与JwtToken过期时间一致
        Duration expireDuration = Duration.ofSeconds(jwtToken.getExpireSecond());

        // 1. tokenMd5:jwtTokenRedisVo
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5), jwtTokenRedisVo, expireDuration);
        // 2. username:loginSysUserRedisVo
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_USER, username), loginSysUserRedisVo, expireDuration);
        // 3. salt hash,方便获取盐值鉴权
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_SALT, username), salt, expireDuration);
        if (generate) {
            // 4. username hash,统计用户登陆次数
            redisTemplate.opsForHash().increment(CommonRedisKey.LOGIN_USER_HASH, username, 1);
        }

    }

    @Override
    public LoginSysUserRedisVo getLoginSysUserRedisVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        return (LoginSysUserRedisVo) redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_USER, username));
    }

    @Override
    public LoginSysUserVo getLoginSysUserVo(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        LoginSysUserRedisVo userRedisVo = getLoginSysUserRedisVo(username);
        return userRedisVo;
    }

    @Override
    public String getSalt(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username不能为空");
        }
        String salt = (String) redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_SALT, username));
        return salt;
    }

    @Override
    public void deleteLoginInfo(JwtToken jwtToken) {
        if (jwtToken == null) {
            throw new IllegalArgumentException("jwtToken不能为空");
        }
        String username = jwtToken.getUsername();
        String token = jwtToken.getToken();
        String tokenMd5 = DigestUtils.md5Hex(token);
        // 1. delete tokenMd5
        redisTemplate.delete(String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5));
        // 2. delete username
        redisTemplate.delete(String.format(CommonRedisKey.LOGIN_USER, username));
        // 3. delete salt
        redisTemplate.delete(String.format(CommonRedisKey.LOGIN_SALT, username));
        // 4. delete username hash
        redisTemplate.opsForHash().increment(CommonRedisKey.LOGIN_USER_HASH, username, -1);
    }

    @Override
    public boolean exists(String token) {
        if (token == null) {
            throw new IllegalArgumentException("token不能为空");
        }
        String tokenMd5 = DigestUtils.md5Hex(token);
        Object object = redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_TOKEN, tokenMd5));
        return object != null;
    }
}
