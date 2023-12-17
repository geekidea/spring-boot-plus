package io.geekidea.boot.config;

import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.config.properties.OpenApiProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekidea
 * @date 2022/10/27
 **/
@Configuration
public class OpenApiConfig {

    private static final String TOKEN = "token";

    @Autowired
    private OpenApiProperties openApiProperties;

    /**
     * token请求头参数
     */
    private Parameter tokenParameter = new HeaderParameter().name(TOKEN).schema(new StringSchema()._default("").name(TOKEN));


    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(openApiProperties.getTitle())
                        .description(openApiProperties.getDescription())
                        .termsOfService(openApiProperties.getTermsOfService())
                        .contact(new Contact().name(openApiProperties.getContactName()).url(openApiProperties.getContactUrl()).email(openApiProperties.getContactEmail()))
                        .version(openApiProperties.getVersion()))
                .externalDocs(new ExternalDocumentation().description(openApiProperties.getExternalDescription()).url(openApiProperties.getExternalUrl()));
    }

    @Bean
    public GroupedOpenApi authApi() {
        String[] packagedToMatch = {CommonConstant.AUTH_PACKAGE_NAME};
        return api("1.登录授权接口文档", packagedToMatch);
    }

    @Bean
    public GroupedOpenApi adminApi() {
        String[] packagedToMatch = {CommonConstant.SYSTEM_PACKAGE_NAME};
        return api("2.系统管理接口文档", packagedToMatch);
    }

    @Bean
    public GroupedOpenApi userApi() {
        String[] packagedToMatch = {CommonConstant.USER_PACKAGE_NAME};
        return api("3.App用户模块接口文档", packagedToMatch);
    }

    @Bean
    public GroupedOpenApi commonApi() {
        String[] packagedToMatch = {CommonConstant.COMMON_PACKAGE_NAME};
        return api("4.公共服务接口文档", packagedToMatch);
    }

    @Bean
    public GroupedOpenApi appApi() {
        String[] packagedToMatch = {CommonConstant.DEMO_PACKAGE_NAME};
        return api("5.Demo接口文档", packagedToMatch);
    }

    /**
     * 配置接口
     *
     * @param group
     * @param packagedToMatch
     * @return
     */
    private GroupedOpenApi api(String group, String[] packagedToMatch) {
        return GroupedOpenApi.builder()
                .group(group)
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(tokenParameter)
                )
                .pathsToMatch("/**")
                .packagesToScan(packagedToMatch).build();
    }

}
