package io.geekidea.boot.framework.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 处理json响应参数
 *
 * @author geekidea
 * @date 2021-12-26
 */
public class JacksonStringSerializer extends JsonSerializer<String> {

    public static final JacksonStringSerializer INSTANCE = new JacksonStringSerializer();

    @Override
    public void serialize(String string, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (string != null) {
            // 去除字符串空格
            string = string.trim();
        }
        jsonGenerator.writeString(string);
    }
}
