package io.geekidea.boot.config.properties;

import io.geekidea.boot.common.constant.CommonConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

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

    public void setUploadPath(String uploadPath) {
        if (StringUtils.isBlank(uploadPath)) {
            // 默认为当前项目路径下的upload目录
            String userDir = CommonConstant.USER_DIR;
            uploadPath = userDir + File.separator + CommonConstant.UPLOAD + File.separator;
        } else {
            // 如果不是以/或\\结尾，则根据系统环境加上对应的斜杠结尾
            if (!(uploadPath.endsWith(CommonConstant.SLASH) || uploadPath.endsWith(CommonConstant.DOUBLE_BACKSLASH))) {
                uploadPath = uploadPath + File.separator;
            }
        }
        this.uploadPath = uploadPath;
    }

}
