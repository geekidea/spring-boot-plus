package io.geekidea.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS配置
 *
 * @author geekidea
 * @date 2023/11/26
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file.oss")
public class OssProperties {

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 访问OSS的域名
     */
    private String endpoint;

    /**
     * bucketName
     */
    private String bucketName;

    /**
     * 根目录
     */
    private String rootDir;

    /**
     * 访问域名
     */
    private String accessDomain;

}
