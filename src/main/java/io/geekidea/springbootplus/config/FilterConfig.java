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

import io.geekidea.springbootplus.common.web.filter.CrossDomainFilter;
import io.geekidea.springbootplus.common.web.filter.RequestPathFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;


/**
 * @author geekidea
 * @date 2018-11-08
 */
@Configuration
public class FilterConfig {


    @Bean
    public Filter crossDomainFilter(){
        return new CrossDomainFilter();
    }

    @Bean
    public Filter requestPathFilter(){
        return new RequestPathFilter();
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean requestPathFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(requestPathFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    @Order(2)
    public FilterRegistrationBean crossDomainFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(crossDomainFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}
