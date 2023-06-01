package io.geekidea.boot.framework.jackson.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * 处理json请求参数
 *
 * @author geekidea
 * @date 2021-12-26
 */
public class JacksonStringDeserializer extends JsonDeserializer<String> {

    public static final JacksonStringDeserializer INSTANCE = new JacksonStringDeserializer();

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();
        if (value != null) {
            // 去除字符串空格
            value = value.trim();
        }
        return value;
    }
}
