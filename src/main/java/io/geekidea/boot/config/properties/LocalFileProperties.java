package io.geekidea.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地文件属性配置
 *
 * @author geekidea
 * @date 2023/06/18
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file.local")
public class LocalFileProperties {

    /**
     * 上传文件访问路径
     */
    private String accessPath;

    /**
     * 上传文件保存路径
     */
    private String uploadPath;

    /**
     * 上传文件访问URL前缀
     */
    private String accessUrl;

}
