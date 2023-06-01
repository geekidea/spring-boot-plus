package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加角色菜单关系表参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "添加角色菜单关系表参数")
public class SysRoleMenuAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "菜单id")
    private Long menuId;

}


