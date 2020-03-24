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

package io.geekidea.springbootplus.framework.util;

import io.geekidea.springbootplus.config.constant.CommonRedisKey;
import io.geekidea.springbootplus.framework.shiro.util.JwtTokenUtil;
import io.geekidea.springbootplus.framework.shiro.util.JwtUtil;
import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserRedisVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * 获取登录信息工具类
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
@Component
public class LoginUtil {

    private static RedisTemplate redisTemplate;

    public LoginUtil(RedisTemplate redisTemplate) {
        LoginUtil.redisTemplate = redisTemplate;
    }


    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public static LoginSysUserRedisVo getLoginSysUserRedisVo() {
        // 获取当前登录用户
        String token = JwtTokenUtil.getToken();
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return (LoginSysUserRedisVo) redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_USER, username));
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return
     */
    public static Long getUserId() {
        LoginSysUserRedisVo loginSysUserRedisVo = getLoginSysUserRedisVo();
        if (loginSysUserRedisVo == null) {
            return null;
        }
        return loginSysUserRedisVo.getId();
    }

    /**
     * 获取当前登录用户的账号
     *
     * @return
     */
    public static String getUsername() {
        LoginSysUserRedisVo loginSysUserRedisVo = getLoginSysUserRedisVo();
        if (loginSysUserRedisVo == null) {
            return null;
        }
        return loginSysUserRedisVo.getUsername();
    }

}
