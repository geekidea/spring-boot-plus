package io.geekidea.boot.generator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库中的表Vo
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "数据库中的表Vo" )
public class GeneratorTableDbVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "表名称" )
    private String tableName;

    @Schema(description = "表注释" )
    private String tableComment;

    @Schema(description = "创建时间" )
    private Date createTime;

    @Schema(description = "类名称")
    private String className;

    @Schema(description = "最近生成的时间")
    private Date updateTime;

    @Schema(description = "生成方式 1：zip压缩包，2：自定义路径")
    private Integer generatorType;

}

