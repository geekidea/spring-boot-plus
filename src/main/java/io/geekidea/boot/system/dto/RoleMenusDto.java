package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author geekidea
 * @date 2023/6/18
 **/
@Data
@Schema(description = "设置角色权限Dto")
public class RoleMenusDto {

    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @Schema(description = "菜单ID集合")
    @NotNull(message = "菜单ID集合不能为空")
    private List<Long> menuIds;

}
