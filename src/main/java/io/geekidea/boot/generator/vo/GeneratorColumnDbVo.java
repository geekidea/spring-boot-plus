package io.geekidea.boot.generator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据库中列信息Vo
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "数据库中列信息Vo")
public class GeneratorColumnDbVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "字段注释")
    private String columnComment;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "字段类型")
    private String columnType;

    @Schema(description = "列顺序" )
    private Integer columnSort;

    @Schema(description = "是否主键")
    private Boolean isPk;

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "是否有默认值")
    private Boolean isDefaultValue;

}

