package io.geekidea.boot.generator.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 生成列类型映射
 *
 * @author geekidea
 * @date 2023/12/31
 **/
@Data
@Schema(description = "生成列类型映射")
public class GeneratorColumnMapping {

    @Schema(description = "列类型")
    private String dataType;

    @Schema(description = "属性类型")
    private String propertyType;

    @Schema(description = "属性包名")
    private String packageName;

    public GeneratorColumnMapping() {
    }

    public GeneratorColumnMapping(String dataType, String propertyType, String packageName) {
        this.dataType = dataType;
        this.propertyType = propertyType;
        this.packageName = packageName;
    }

}
