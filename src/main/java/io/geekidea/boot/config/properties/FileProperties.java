package io.geekidea.boot.config.properties;

import io.geekidea.boot.common.enums.FileServerType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 上传文件属性配置
 *
 * @author geekidea
 * @date 2023/06/18
 **/
@Data
@ConfigurationProperties(prefix = "file.local")
public class FileProperties {

    /**
     * 上传文件访问路径
     */
    private String accessPath;

    /**
     * 上传文件保存路径
     */
    private String uploadPath;

    /**
     * 文件服务类型
     */
    private FileServerType fileServerType = FileServerType.LOCAL;

    /**
     * 上传文件访问URL前缀
     */
    private String accessUrl;

}
