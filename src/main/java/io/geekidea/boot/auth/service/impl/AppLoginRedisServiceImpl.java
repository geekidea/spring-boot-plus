package io.geekidea.boot.auth.service.impl;

import io.geekidea.boot.auth.service.AppLoginRedisService;
import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.auth.vo.AppLoginVo;
import io.geekidea.boot.common.constant.RedisKey;
import io.geekidea.boot.config.properties.LoginAppProperties;
import io.geekidea.boot.framework.exception.LoginException;
import io.geekidea.boot.framework.exception.LoginTokenException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author geekidea
 * @date 2022/7/12
 **/
@Slf4j
@Service
public class AppLoginRedisServiceImpl implements AppLoginRedisService {

    private static final TimeUnit TOKEN_TIME_UNIT = TimeUnit.DAYS;

    @Autowired
    private LoginAppProperties loginAppProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    private Integer tokenExpireDays;

    @PostConstruct
    public void init() {
        log.info("loginAppProperties = " + loginAppProperties);
        tokenExpireDays = loginAppProperties.getTokenExpireDays();
    }

    @Override
    public String getLoginRedisKey(String token) {
        String loginRedisKey = String.format(RedisKey.LOGIN_TOKEN, token);
        return loginRedisKey;
    }

    @Override
    public void setLoginVo(String token, AppLoginVo appLoginVo) {
        if (appLoginVo == null) {
            throw new LoginException("登录用户信息不能为空");
        }
        if (loginAppProperties.isSingleLogin()) {
            // 单点登录，则删除之前该用户的key
            deleteLoginInfoByToken(token);
        }
        // 用户信息
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.opsForValue().set(loginTokenRedisKey, appLoginVo, tokenExpireDays, TOKEN_TIME_UNIT);
    }

    @Override
    public AppLoginVo getLoginVo(String token) {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        String loginRedisKey = getLoginRedisKey(token);
        AppLoginVo appLoginVo = (AppLoginVo) redisTemplate.opsForValue().get(loginRedisKey);
        return appLoginVo;
    }

    @Override
    public void deleteLoginVo(String token) {
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.delete(loginTokenRedisKey);
    }

    @Override
    public void refreshToken() {
        // 刷新token
        String token = TokenUtil.getToken();
        if (StringUtils.isBlank(token)) {
            return;
        }
        // 刷新key的过期时间
        String loginTokenRedisKey = getLoginRedisKey(token);
        redisTemplate.expire(loginTokenRedisKey, tokenExpireDays, TOKEN_TIME_UNIT);
    }

    @Override
    public void deleteLoginInfoByToken(String token) {
        log.info("清除用户的所有redis登录信息：" + token);
        if (StringUtils.isBlank(token)) {
            throw new LoginTokenException("token不能为空");
        }
        int lastIndexOf = token.lastIndexOf(".");
        String userTokenPrefix = token.substring(0, lastIndexOf + 1);
        // 删除之前该用户的key
        String userTokenRedisPrefix = userTokenPrefix + "*";
        String formatRedisTokenPrefix = String.format(RedisKey.LOGIN_TOKEN, userTokenRedisPrefix);
        Set keys = redisTemplate.keys(formatRedisTokenPrefix);
        if (CollectionUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

}
