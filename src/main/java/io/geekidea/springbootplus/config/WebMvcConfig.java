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

import io.geekidea.springbootplus.security.interceptor.JwtInterceptor;
import io.geekidea.springbootplus.common.web.interceptor.PermissionInterceptor;
import io.geekidea.springbootplus.common.web.interceptor.TokenTimeoutInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author geekidea
 * @date 2018-11-08
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring-boot-plus.interceptor.jwt.exclude.path}")
    private String[] jwtExcludePaths;

    @Value("${spring-boot-plus.interceptor.token-timeout.exclude.path}")
    private String[] tokenTimeoutExcludePaths;

    @Value("${spring-boot-plus.interceptor.permission.exclude.path}")
    private String[] permissionExcludePaths;

    /**
     * jwt token验证拦截器
     * @return
     */
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    /**
     * 权限拦截器
     * @return
     */
    @Bean
    public PermissionInterceptor permissionInterceptor(){
        return new PermissionInterceptor();
    }

    /**
     * TOKEN超时拦截器
     * @return
     */
    @Bean
    public TokenTimeoutInterceptor tokenTimeoutInterceptor(){
        return new TokenTimeoutInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("PermissionInterceptor excludePaths : {}", Arrays.toString(permissionExcludePaths));

        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(jwtExcludePaths);

        // 1.TOKEN超时拦截器
//        registry.addInterceptor(tokenTimeoutInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(tokenTimeoutExcludePaths);
//
//        // 2.权限拦截器
//        registry.addInterceptor(permissionInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(permissionExcludePaths)
//                .excludePathPatterns(operationPlatformIncludePaths);

    }

}
