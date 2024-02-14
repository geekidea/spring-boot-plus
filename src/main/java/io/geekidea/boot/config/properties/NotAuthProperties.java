package io.geekidea.boot.config.properties;

import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 没有权限属性配置
 *
 * @author geekidea
 * @date 2023/06/18
 **/
@Data
@Component
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
