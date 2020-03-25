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
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Set;

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

    /**
     * 操作日志配置
     */
    @NestedConfigurationProperty
    private OperationLogConfig operationLog;

    /**
     * 登录日志配置
     */
    private LoginLogConfig loginLog;

    @Data
    public static class AopConfig {

        /**
         * 是否启用
         */
        private boolean enable;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class LogAopConfig extends AopConfig {

        /**
         * 是否启用requestId
         */
        private boolean enableRequestId = true;

        /**
         * 日志输出类型：print-type
         */
        private LogPrintType logPrintType = LogPrintType.ORDER;

        /**
         * 请求ID生成类型
         */
        private RequestIdType requestIdType = RequestIdType.IDWORK;

        /**
         * 请求日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
         */
        private boolean requestLogFormat = true;

        /**
         * 响应日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
         */
        private boolean responseLogFormat = true;

        /**
         * 排除路径
         */
        private Set<String> excludePaths;
    }

    /**
     * 操作日志配置
     */
    @Data
    public static class OperationLogConfig {

        /**
         * 是否启用
         */
        private boolean enable = true;

        /**
         * 排除路径
         */
        private Set<String> excludePaths;

    }

    /**
     * 登录日志配置
     */
    @Data
    public static class LoginLogConfig {

        /**
         * 是否启用
         */
        private boolean enable = true;

        /**
         * 登录路径
         */
        private String loginPath = "/login";

        /**
         * 登出路径
         */
        private String logoutPath = "/logout";

    }

    /**
     * 日志打印类型
     *
     * @author geekidea
     * @date 2020/3/19
     **/
    public enum LogPrintType {

        /**
         * 不打印日志
         */
        NONE,
        /**
         * 请求和响应日志，按照执行顺序分开打印
         */
        ORDER,
        /**
         * 方法执行结束时，连续分开打印请求和响应日志
         */
        LINE,
        /**
         * 方法执行结束时，合并请求和响应日志，同时打印
         */
        MERGE;

    }

    /**
     * 请求ID生成类型
     *
     * @author geekidea
     * @date 2020/3/25
     **/
    public enum RequestIdType {
        /**
         * 生成UUID无中横线
         */
        UUID,
        /**
         * 生成数字
         */
        IDWORK
    }

}
