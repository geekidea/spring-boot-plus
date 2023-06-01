package io.geekidea.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author geekidea
 * @date 2022/6/18
 **/
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.log-aop")
public class LogAopProperties {

    /**
     * 是否启用
     */
    private boolean enable = true;

    /**
     * 是否打印日志
     */
    private boolean printLog = true;

    /**
     * 登录路径
     */
    private String loginUrl = "/login";

    /**
     * 是否打印请求头日志
     */
    private boolean printHeadLog = false;


}
