package io.geekidea.boot.config;

import io.geekidea.boot.config.properties.XssProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author geekidea
 * @date 2023/7/9
 **/
@Configuration
@EnableConfigurationProperties(XssProperties.class)
public class PropertiesConfig {

}
