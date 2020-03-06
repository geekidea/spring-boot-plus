package io.geekidea.springbootplus.system.param.sysrole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author geekidea
 * @date 2020/3/2
 **/
@Data
@ApiModel("修改系统角色权限参数")
public class UpdateSysRolePermissionParam implements Serializable {

    private static final long serialVersionUID = -672108684986772098L;

    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @ApiModelProperty("权限ID集合")
    private List<Long> permissionIds;
}
