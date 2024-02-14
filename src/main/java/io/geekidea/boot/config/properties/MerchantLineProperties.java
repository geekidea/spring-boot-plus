package io.geekidea.boot.config.properties;

import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 多商户行级数据权限配置
 *
 * @author geekidea
 * @date 2023/12/05
 **/
@Data
@Component
@ConfigurationProperties(prefix = "merchant-line")
public class MerchantLineProperties {

    /**
     * 商户ID列名称
     */
    private String merchantIdColumn;

    /**
     * 包含的表名称
     */
    private List<String> includeTables;

    public void setIncludeTables(List<String> includeTables) {
        this.includeTables = YamlUtil.parseListArray(includeTables);
    }

}
