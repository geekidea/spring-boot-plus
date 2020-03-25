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

package io.geekidea.springbootplus.framework.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author geekidea
 * @date 2019/8/5
 */
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.interceptor")
public class SpringBootPlusInterceptorProperties {

    /**
     * 权限拦截器排除路径
     */
    @NestedConfigurationProperty
    private InterceptorConfig permission = new InterceptorConfig();

    /**
     * 资源拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig resource = new InterceptorConfig();

    /**
     * 上传拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig upload = new InterceptorConfig();

    /**
     * 下载拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig download = new InterceptorConfig();

    @Data
    public static class InterceptorConfig {

        /**
         * 是否启用
         */
        private boolean enable;

        /**
         * 排除路径
         */
        private String[] excludePaths;

        /**
         * 包含的路径
         */
        private String[] includePaths;

    }

}
