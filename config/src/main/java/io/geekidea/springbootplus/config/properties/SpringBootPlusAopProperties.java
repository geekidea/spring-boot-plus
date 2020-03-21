/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.config.properties;

import io.geekidea.springbootplus.config.enums.LogPrintType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * AOP配置属性
 *
 * @author geekidea
 * @date 2019-09-29
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.aop")
public class SpringBootPlusAopProperties {

    /**
     * 请求路径Filter配置
     */
    @NestedConfigurationProperty
    private LogAopConfig log = new LogAopConfig();

    @Data
    public static class AopConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class LogAopConfig extends AopConfig {

        /**
         * 日志输出类型：print-type
         */
        LogPrintType logPrintType = LogPrintType.ORDER;

        /**
         * 请求日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
         */
        private boolean requestLogFormat = true;

        /**
         * 响应日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
         */
        private boolean responseLogFormat = true;

    }

}
