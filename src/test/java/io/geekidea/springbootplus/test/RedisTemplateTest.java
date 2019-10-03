package io.geekidea.springbootplus.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void put(){
        redisTemplate.opsForHash().increment("hello","world",1);
        System.out.println(redisTemplate.opsForHash().get("hello","world"));
        redisTemplate.opsForHash().increment("hello","world",-1);
        System.out.println(redisTemplate.opsForHash().get("hello","world"));
    }
}
