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

package io.geekidea.springbootplus.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import io.geekidea.springbootplus.config.properties.SwaggerProperties;
import io.geekidea.springbootplus.framework.common.exception.SpringBootPlusConfigException;
import io.geekidea.springbootplus.framework.shiro.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger2全局配置
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
@Configuration
public class Swagger2Config {

    @Autowired
    private SwaggerProperties swaggerProperties;

    /**
     * 扫描多包时，包路径的拆分符,分号
     */
    private static final String SPLIT_COMMA = ",";

    /**
     * 扫描多包时，包路径的拆分符,逗号
     */
    private static final String SPLIT_SEMICOLON = ";";

    @Bean
    public Docket createRestApi() {
        log.debug("swaggerProperties = " + swaggerProperties);
        String basePackage = swaggerProperties.getBasePackage();
        if (StringUtils.isBlank(basePackage)) {
            throw new SpringBootPlusConfigException("Swagger basePackage不能为空");
        }
        String[] basePackages = null;
        if (basePackage.contains(SPLIT_COMMA)) {
            basePackages = basePackage.split(SPLIT_COMMA);
        } else if (basePackage.contains(SPLIT_SEMICOLON)) {
            basePackages = basePackage.split(SPLIT_SEMICOLON);
        }
        log.info("swagger scan basePackages:" + Arrays.toString(basePackages));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage(basePackages))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setHeaderToken());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getUrl())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<Parameter> setHeaderToken() {
        List<Parameter> pars = new ArrayList<>();

        // token请求头
        String testTokenValue = "";
        ParameterBuilder tokenPar = new ParameterBuilder();
        Parameter tokenParameter = tokenPar
                .name(JwtTokenUtil.getTokenName())
                .description("Token Request Header")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue(testTokenValue)
                .build();
        pars.add(tokenParameter);
        return pars;
    }


    public static Predicate<RequestHandler> basePackage(final String[] basePackages) {
        return input -> declaringClass(input).transform(handlerPackage(basePackages)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String[] basePackages) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackages) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
