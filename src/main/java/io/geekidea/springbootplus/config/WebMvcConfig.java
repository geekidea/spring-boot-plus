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

package io.geekidea.springbootplus.config;

import io.geekidea.springbootplus.common.web.interceptor.PermissionInterceptor;
import io.geekidea.springbootplus.common.web.interceptor.TokenTimeoutInterceptor;
import io.geekidea.springbootplus.config.core.SpringBootPlusProperties;
import io.geekidea.springbootplus.security.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author geekidea
 * @date 2018-11-08
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Autowired
    private TokenTimeoutInterceptor tokenTimeoutInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        // JWT拦截器
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(springBootPlusProperties.getInterceptorConfig().getJwtConfig().getExcludePath());
//
//        // TOKEN超时拦截器
//        registry.addInterceptor(tokenTimeoutInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(springBootPlusProperties.getInterceptorConfig().getTokenTimeoutConfig().getExcludePath());
//
//        // 权限拦截器
//        registry.addInterceptor(permissionInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(springBootPlusProperties.getInterceptorConfig().getPermissionConfig().getExcludePath());

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
