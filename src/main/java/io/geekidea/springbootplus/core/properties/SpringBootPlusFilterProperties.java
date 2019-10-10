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

package io.geekidea.springbootplus.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author geekidea
 * @date 2019-09-29
 * @since 1.3.0.RELEASE
 **/
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.filter")
public class SpringBootPlusFilterProperties {

    /**
     * 请求路径Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig requestPath = new FilterConfig();

    @Data
    public static class FilterConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 包含的路径
         */
        private String[] includePaths;

        /**
         * 排除路径
         */
        private String[] excludePaths;

    }
}
