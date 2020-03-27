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

package io.geekidea.springbootplus.framework.log.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.geekidea.springbootplus.framework.ip.entity.IpAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * HTTP请求信息对象
 *
 * @author geekidea
 * @date 2020/3/18
 **/
@Data
@Accessors(chain = true)
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = 1421424612944015973L;

    /**
     * 请求路径
     * /api/foobar/add
     */
    private String path;

    /**
     * 请求ID
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String requestId;

    /**
     * 请求实际路径
     * /foobar/add
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String realPath;

    /**
     * 请求IP地址
     */
    private String ip;

    /**
     * 请求IP对象
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private IpAddress ipAddress;

    /**
     * 请求方式，GET/POST
     */
    private String requestMethod;

    /**
     * 请求内容类型
     */
    private String contentType;

    /**
     * 判断控制器方法参数中是否有RequestBody注解
     */
    private Boolean requestBody;

    /**
     * 请求参数对象
     */
    private Object param;

    /**
     * 请求时间字符串
     */
    private String time;

    /**
     * 请求token
     */
    private String token;

    /**
     * 请求token MD5值
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String tokenMd5;

    /**
     * 用户代理字符串
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String userAgent;

    /**
     * requiresRoles值
     */
    private String requiresRoles;

    /**
     * requiresPermissions值
     */
    private String requiresPermissions;

    /**
     * requiresAuthentication
     */
    private Boolean requiresAuthentication;

    /**
     * requiresUser
     */
    private Boolean requiresUser;

    /**
     * requiresGuest
     */
    private Boolean requiresGuest;

}
