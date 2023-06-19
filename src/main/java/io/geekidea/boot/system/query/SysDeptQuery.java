package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.query.DataRangeQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门查询参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "部门查询参数")
public class SysDeptQuery extends DataRangeQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}

