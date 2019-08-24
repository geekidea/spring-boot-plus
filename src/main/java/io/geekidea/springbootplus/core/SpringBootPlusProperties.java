/**
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

package io.geekidea.springbootplus.core;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * spring-boot-plus属性配置信息
 * @author geekidea
 * @date 2019-08-04
 * @since 1.2.0-RELEASE
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "spring-boot-plus")
public class SpringBootPlusProperties {

    /**
     * 是否启用ansi控制台输出有颜色的字体，local环境建议开启，服务器环境设置为false
     */
    private boolean enableAnsi;

    /**
     * 请求日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
     */
    private boolean requestLogFormat;

    /**
     * 响应日志在控制台是否格式化输出，local环境建议开启，服务器环境设置为false
     */
    private boolean responseLogFormat;

    /**
     * 登录token失效时间，单位分钟，默认60分钟失效
     */
    private Integer tokenValidTime = 60;

    /**
     * 拦截器配置
     */
    @NestedConfigurationProperty
    private SpringBootPlusInterceptorConfig interceptorConfig;

    /**
     * 上传目录
     */
    private String uploadPath;
    /**
     * 资源访问路径，前端访问
     */
    private String resourceAccessPath;
    /**
     * 资源访问路径，后段配置，资源映射/拦截器使用
     */
    private String resourceAccessPatterns;
    /**
     * 资源访问全路径
     */
    private String resourceAccessUrl;

    /**
     * 允许上传的文件后缀集合
     */
    private List<String> allowUploadFileExtensions;
    /**
     * 允许下载的文件后缀集合
     */
    private List<String> allowDownloadFileExtensions;

}
