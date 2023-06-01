package io.geekidea.boot.framework.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Jackson BigDecimal 反序列化器
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class JacksonBigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal param, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (param != null) {
            BigDecimal result = param.setScale(2, BigDecimal.ROUND_HALF_UP);
            jsonGenerator.writeString(result.toString());
        } else {
            jsonGenerator.writeNull();
        }

    }
}
