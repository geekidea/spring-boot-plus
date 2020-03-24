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

import io.geekidea.springbootplus.config.constant.CommonConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT属性配置
 *
 * @author geekidea
 * @date 2019-05-22
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.jwt")
public class JwtProperties {

    /**
     * token名称,默认名称为：token，可自定义
     */
    private String tokenName = CommonConstant.JWT_DEFAULT_TOKEN_NAME;

    /**
     * 密码
     */
    private String secret = CommonConstant.JWT_DEFAULT_SECRET;

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 主题
     */
    private String subject;

    /**
     * 签发的目标
     */
    private String audience;

    /**
     * token失效时间,默认1小时，60*60=3600
     */
    private Long expireSecond = CommonConstant.JWT_DEFAULT_EXPIRE_SECOND;

    /**
     * 是否刷新token，默认为true
     */
    private boolean refreshToken = true;

    /**
     * 刷新token倒计时，默认10分钟，10*60=600
     */
    private Integer refreshTokenCountdown;

    /**
     * redis校验jwt token是否存在
     */
    private boolean redisCheck;

    /**
     * 单用户登录，一个用户只能又一个有效的token
     */
    private boolean singleLogin;

    /**
     * 是否进行盐值校验
     */
    private boolean saltCheck;

}
