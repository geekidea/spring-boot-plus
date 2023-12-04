package io.geekidea.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信小程序属性配置
 *
 * @author geekidea
 * @date 2023/11/26
 **/
@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {

    /**
     * appid
     */
    private String appid;

    /**
     * 密钥
     */
    private String secret;

}
