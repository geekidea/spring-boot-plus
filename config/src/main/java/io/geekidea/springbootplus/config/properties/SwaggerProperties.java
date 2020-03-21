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

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Swagger配置属性
 *
 * @author geekidea
 * @date 2020/3/21
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.swagger")
public class SwaggerProperties {

    /**
     * 扫描的基本包
     */
    @Value("${spring-boot-plus.swagger.base.package}")
    private String basePackage;

    /**
     * 联系人邮箱
     */
    @Value("${spring-boot-plus.swagger.contact.email}")
    private String contactEmail;

    /**
     * 联系人名称
     */
    @Value("${spring-boot-plus.swagger.contact.name}")
    private String contactName;

    /**
     * 联系人网址
     */
    @Value("${spring-boot-plus.swagger.contact.url}")
    private String contactUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 标题
     */
    private String title;

    /**
     * 网址
     */
    private String url;

    /**
     * 版本
     */
    private String version;

    /**
     * 测试接口时，自动填充token的值
     * 注意，仅用于开发和测试环境，生成环境请不要设置值
     */
    private String tokenDefaultValue;

}
