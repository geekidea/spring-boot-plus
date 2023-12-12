package io.geekidea.boot.config.properties;

import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 上传文件属性配置
 *
 * @author geekidea
 * @date 2023/06/18
 **/
@Data
@ConfigurationProperties(prefix = "not-auth")
public class NotAuthProperties {

    /**
     * 是否启用登录校验
     */
    private boolean enable;

    /**
     * 包含的路径
     */
    private List<String> includePaths;

    public void setExcludePaths(List<String> includePaths) {
        this.includePaths = YamlUtil.parseListArray(includePaths);
    }

}
