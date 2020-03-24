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

package io.geekidea.springbootplus.framework.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户客户端信息对象
 * </p>
 *
 * @author geekidea
 * @date 2019-05-23
 **/
@Data
public class ClientInfo implements Serializable {

    private static final long serialVersionUID = -5549531244606897514L;

    /**
     * ip
     */
    private String ip;

    /**
     * ip对应的地址
     */
    private String addree;

    /**
     * 浏览器名称
     */
    private String browserName;

    /**
     * 浏览器版本
     */
    private String browserversion;

    /**
     * 浏览器引擎名称
     */
    private String engineName;

    /**
     * 浏览器引擎版本
     */
    private String engineVersion;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 是否是手机
     */
    private boolean mobile;

    /**
     * 移动端设备型号
     */
    private String deviceName;

    /**
     * 移动端设备型号
     */
    private String deviceModel;

}
