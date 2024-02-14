package io.geekidea.boot.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义RedisKey前缀
 *
 * @author geekidea
 * @date 2023/11/17
 **/
@Slf4j
public class CustomStringRedisSerializer extends StringRedisSerializer {

    private String redisKeyPrefix;

    public CustomStringRedisSerializer(String redisKeyPrefix) {
        this.redisKeyPrefix = redisKeyPrefix;
        log.info("redisKeyPrefix:" + redisKeyPrefix);
    }

    @Override
    public byte[] serialize(String string) {
        if (StringUtils.isNotBlank(redisKeyPrefix) && string != null && !string.startsWith(redisKeyPrefix)) {
            string = redisKeyPrefix + "." + string;
        }
        return super.serialize(string);
    }

    @Override
    public String deserialize(byte[] bytes) {
        String string = super.deserialize(bytes);
        if (StringUtils.isNotBlank(redisKeyPrefix) && string != null && !string.startsWith(redisKeyPrefix)) {
            string = redisKeyPrefix + "." + string;
        }
        return string;
    }
}
