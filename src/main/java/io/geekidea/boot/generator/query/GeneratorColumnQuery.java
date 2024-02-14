package io.geekidea.boot.generator.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 生成代码列查询参数
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "生成代码列查询参数")
public class GeneratorColumnQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "表名称")
    private String tableName;

}

