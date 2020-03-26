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

package io.geekidea.springbootplus.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Filter配置属性
 *
 * @author geekidea
 * @date 2019-09-29
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.filter")
public class SpringBootPlusFilterProperties {

    /**
     * RequestDetail Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig request = new FilterConfig();

    /**
     * XSS Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig xss = new FilterConfig();

    @Data
    public static class FilterConfig {

        /**
         * 是否启用
         */
        private boolean enable;

        /**
         * 过滤的路径
         */
        private String[] urlPatterns;

        /**
         * 排序
         */
        private int order;

        /**
         * 是否支持异步
         */
        private boolean async;

    }
}
