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
package io.geekidea.springbootplus.core.config;

import io.geekidea.springbootplus.common.aop.LogAop;
import io.geekidea.springbootplus.common.web.interceptor.PermissionInterceptor;
import io.geekidea.springbootplus.core.properties.*;
import io.geekidea.springbootplus.resource.web.interceptor.DownloadInterceptor;
import io.geekidea.springbootplus.resource.web.interceptor.ResourceInterceptor;
import io.geekidea.springbootplus.resource.web.interceptor.UploadInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * spring-boot-plus配置
 *
 * @author geekidea
 * @date 2019/8/4
 * @since 1.2.0-RELEASE
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        SpringBootPlusProperties.class,
        SpringBootPlusCorsProperties.class,
        SpringBootPlusFilterProperties.class,
        SpringBootPlusInterceptorProperties.class,
        SpringBootPlusAopProperties.class
})
public class SpringBootPlusConfig {

    /**
     * CORS跨域设置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter(SpringBootPlusCorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 跨域配置
        corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        corsConfiguration.setAllowedHeaders(corsProperties.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(corsProperties.getAllowedMethods());
        corsConfiguration.setAllowCredentials(corsProperties.isAllowCredentials());
        corsConfiguration.setExposedHeaders(corsProperties.getExposedHeaders());
        source.registerCorsConfiguration(corsProperties.getPath(), corsConfiguration);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setEnabled(corsProperties.isEnable());
        return bean;
    }

    /**
     * 配置日志AOP
     *
     * @param springBootPlusProperties
     * @return
     */
    @Bean
    public LogAop logAop(SpringBootPlusProperties springBootPlusProperties) {
        LogAop logAop = new LogAop();
        logAop.setRequestLogFormat(springBootPlusProperties.isRequestLogFormat());
        logAop.setResponseLogFormat(springBootPlusProperties.isResponseLogFormat());
        log.debug("init LogAop success");
        return logAop;
    }

    /**
     * 权限拦截器
     *
     * @return
     */
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        PermissionInterceptor permissionInterceptor = new PermissionInterceptor();
        return permissionInterceptor;
    }

    /**
     * 上传拦截器
     *
     * @return
     */
    @Bean
    public UploadInterceptor uploadInterceptor() {
        UploadInterceptor uploadInterceptor = new UploadInterceptor();
        return uploadInterceptor;
    }

    /**
     * 资源拦截器
     *
     * @return
     */
    @Bean
    public ResourceInterceptor resourceInterceptor() {
        ResourceInterceptor resourceInterceptor = new ResourceInterceptor();
        return resourceInterceptor;
    }

    /**
     * 下载拦截器
     *
     * @return
     */
    @Bean
    public DownloadInterceptor downloadInterceptor() {
        DownloadInterceptor downloadInterceptor = new DownloadInterceptor();
        return downloadInterceptor;
    }
}
