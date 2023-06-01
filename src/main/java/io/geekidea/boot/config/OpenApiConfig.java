package io.geekidea.boot.config;

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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekidea
 * @date 2022/10/27
 **/
@Configuration
@EnableConfigurationProperties(OpenApiProperties.class)
public class OpenApiConfig {

    private static final String TOKEN = "token";

    @Autowired
    private OpenApiProperties openApiProperties;

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
    public GroupedOpenApi systemApi() {
        String[] packagedToMatch = {"io.geekidea.boot"};
        Parameter tokenParameter = new HeaderParameter().name(TOKEN).schema(new StringSchema()._default("").name(TOKEN));
        Parameter customParameter = new HeaderParameter().name("custom").schema(new StringSchema()._default("").name(""));
        return GroupedOpenApi.builder()
                .group("spring-boot-plus doc")
                .addOperationCustomizer((operation, handlerMethod) -> operation.addParametersItem(tokenParameter).addParametersItem(customParameter))
                .pathsToMatch("/**")
                .packagesToScan(packagedToMatch).build();
    }

}
