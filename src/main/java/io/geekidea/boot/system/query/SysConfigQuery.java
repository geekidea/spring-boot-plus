package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统配置查询参数
 *
 * @author geekidea
 * @since 2023-11-27
 */
@Data
@Schema(description = "系统配置查询参数")
public class SysConfigQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

}

