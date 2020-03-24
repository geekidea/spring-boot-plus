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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.TimeZone;

/**
 * Jackson序列化工具类
 *
 * @author geekidea
 * @date 2019-11-01
 **/
public class Jackson {

    /**
     * 时区
     */
    private static final TimeZone timeZone = TimeZone.getTimeZone("GMT+8");

    /**
     * 键按自然顺序输出
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return toJsonString(object, false);
    }

    /**
     * 键按自然顺序格式化输出
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJsonString(Object object, boolean prettyFormat) {
        if (object == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 格式化输出
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, prettyFormat);
            // 键按自然顺序输出
            objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
            // 设置时区
            objectMapper.setTimeZone(timeZone);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 键按自然顺序格式化输出
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJsonStringNonNull(Object object) {
        return toJsonStringNonNull(object, false);
    }


    /**
     * 键按自然顺序格式化输出
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJsonStringNonNull(Object object, boolean prettyFormat) {
        if (object == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 格式化输出
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, prettyFormat);
            // 键按自然顺序输出
            objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
            // 为空的序列化
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 设置时区
            objectMapper.setTimeZone(timeZone);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
