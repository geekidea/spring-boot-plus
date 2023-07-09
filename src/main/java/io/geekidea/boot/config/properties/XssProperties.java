package io.geekidea.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author geekidea
 * @date 2023/7/9
 **/
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.xss")
public class XssProperties {

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 过滤的路径
     */
    private String[] urlPatterns;

    /**
     * 排序
     */
    private int order;

    /**
     * 是否支持异步
     */
    private boolean async;

}
