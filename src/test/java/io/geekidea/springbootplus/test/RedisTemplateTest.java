package io.geekidea.springbootplus.test;

import io.geekidea.springbootplus.common.constant.CommonRedisKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void put() {

        String username = "junit-test-admin";
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, "111"), "111");
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, "222"), "222");
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, "333"), "333");

        Set<String> set = redisTemplate.keys(String.format(CommonRedisKey.LOGIN_USER_TOKEN, username, "*"));
        System.out.println("set = " + set);

        List<String> list = redisTemplate.opsForValue().multiGet(set);
        System.out.println("list = " + list);

        Long listResult = redisTemplate.delete(list);
        Long setResult = redisTemplate.delete(set);
        System.out.println("listResult = " + listResult);
        System.out.println("setResult = " + setResult);

        Long count = redisTemplate.countExistingKeys(set);
        System.out.println("count = " + count);
    }
}
