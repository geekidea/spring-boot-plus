package io.geekidea.boot.auth.service.impl;

import io.geekidea.boot.auth.exception.LoginException;
import io.geekidea.boot.auth.exception.LoginTokenException;
import io.geekidea.boot.auth.service.LoginRedisService;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.auth.vo.LoginRedisVo;
import io.geekidea.boot.auth.vo.LoginVo;
import io.geekidea.boot.config.properties.LoginProperties;
import io.geekidea.boot.framework.constant.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class LoginRedisServiceImpl implements LoginRedisService {

    private static final TimeUnit TOKEN_TIME_UNIT = TimeUnit.MINUTES;

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getLoginRedisKey(String token) throws Exception {
        String loginRedisKey = String.format(RedisKey.LOGIN_TOKEN, token);
        return loginRedisKey;
    }

    @Override
    public void setLoginRedisVo(String token, LoginVo loginVo, List<String> permissions) throws Exception {
        if (loginVo == null) {
            throw new LoginException("登录用户信息不能为空" );
        }
        Long userId = loginVo.getUserId();
        if (loginProperties.isSingleLogin()) {
            // 单点登录，则删除之前该用户的key
            deleteLoginInfoByUserId(userId);
        }
        // 用户token
        String loginUserRedisKey = String.format(RedisKey.LOGIN_USER, userId, token);
        redisTemplate.opsForValue().set(loginUserRedisKey, token, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
        // 用户信息
        String loginTokenRedisKey = getLoginRedisKey(token);
        LoginRedisVo loginRedisVo = new LoginRedisVo();
        loginRedisVo.setLoginVo(loginVo);
        loginRedisVo.setPermissions(permissions);
        redisTemplate.opsForValue().set(loginTokenRedisKey, loginRedisVo, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
        // 用户ID
        String loginUserIdRedisKey = String.format(RedisKey.LOGIN_USER_ID, token);
        redisTemplate.opsForValue().set(loginUserIdRedisKey, userId, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
    }

    @Override
    public LoginRedisVo getLoginRedisVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空" );
        }
        String loginRedisKey = getLoginRedisKey(token);
        LoginRedisVo loginRedisVo = (LoginRedisVo) redisTemplate.opsForValue().get(loginRedisKey);
        return loginRedisVo;
    }

    @Override
    public void deleteLoginRedisVo(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空" );
        }
        String loginTokenRedisKey = getLoginRedisKey(token);
        String loginUserIdRedisKey = String.format(RedisKey.LOGIN_USER_ID, token);
        Object userId = redisTemplate.opsForValue().get(loginUserIdRedisKey);
        if (userId != null) {
            String loginUserRedisKey = String.format(RedisKey.LOGIN_USER, userId, token);
            redisTemplate.delete(loginUserRedisKey);
        }
        redisTemplate.delete(loginTokenRedisKey);
        redisTemplate.delete(loginUserIdRedisKey);
    }

    @Override
    public void refreshToken() throws Exception {
        // 刷新token
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return;
        }
        String loginUserIdRedisKey = String.format(RedisKey.LOGIN_USER_ID, token);
        Object userId = redisTemplate.opsForValue().get(loginUserIdRedisKey);
        if (userId == null) {
            return;
        }
        // 刷新key的过期时间
        String loginUserRedisKey = String.format(RedisKey.LOGIN_USER, userId, token);
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.expire(loginUserIdRedisKey, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
        redisTemplate.expire(loginUserRedisKey, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
        redisTemplate.expire(loginTokenRedisKey, loginProperties.getTokenExpireMinutes(), TOKEN_TIME_UNIT);
    }

    @Override
    public Long getLoginUserId(String token) throws Exception {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String loginUserIdRedisKey = String.format(RedisKey.LOGIN_USER_ID, token);
        Object userIdObject = redisTemplate.opsForValue().get(loginUserIdRedisKey);
        if (userIdObject == null) {
            return null;
        }
        Long userId = Long.parseLong(userIdObject.toString());
        return userId;
    }

    @Override
    public void deleteLoginInfoByUserId(Long userId) throws Exception {
        log.info("清除用户的所有redis登录信息：" + userId);
        // 删除之前该用户的key
        String loginUserAllTokenRedisKey = String.format(RedisKey.LOGIN_USER, userId, "*" );
        Set<String> set = redisTemplate.keys(loginUserAllTokenRedisKey);
        if (CollectionUtils.isNotEmpty(set)) {
            for (String key : set) {
                String beforeToken = (String) redisTemplate.opsForValue().get(key);
                String userTokenKey = getLoginRedisKey(beforeToken);
                String loginUserIdRedisKey = String.format(RedisKey.LOGIN_USER_ID, beforeToken);
                redisTemplate.delete(key);
                redisTemplate.delete(userTokenKey);
                redisTemplate.delete(loginUserIdRedisKey);

            }
        }
    }


}
