/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Jwt配置属性
 * </p>
 * @auth geekidea
 * @date 2019-05-22
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.jwt")
public class JwtProperties {

    /**
     * 密码
     */
//    @Value("spring-boot-plus.jwt.secret:spring-boot-plus")
    private String secret;

    /**
     * 签发人
     */
//    @Value("spring-boot-plus.jwt.issuer:spring-boot-plus")
    private String issuer;

    /**
     * 主题
     */
//    @Value("spring-boot-plus.jwt.subject:spring-boot-plus-jwt")
    private String subject;

    /**
     * 签发的目标
     */
//    @Value("spring-boot-plus.jwt.audience:web")
    private String audience;

    /**
     * token失效时间,默认30分钟
     */
//    @Value("spring-boot-plus.jwt.default.expire.minutes:30")
    private Integer expireMinutes;

}
