package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统角色查询参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统角色查询参数")
public class SysRoleQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色状态，0：禁用，1：启用")
    private Boolean status;

}

