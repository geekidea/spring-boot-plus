package io.geekidea.boot.config;

import io.geekidea.boot.config.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekidea
 * @date 2023/7/9
 **/
@Configuration
@EnableConfigurationProperties({
        BootProperties.class,
        LoginProperties.class,
        LoginAdminProperties.class,
        LoginAppProperties.class,
        FileProperties.class,
        OpenApiProperties.class,
        XssProperties.class,
        WxMpProperties.class,
        OssProperties.class,
        MerchantLineProperties.class
})
public class PropertiesConfig {

}
