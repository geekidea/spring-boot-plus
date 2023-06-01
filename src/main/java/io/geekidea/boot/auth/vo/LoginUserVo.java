package io.geekidea.boot.auth.vo;

import io.geekidea.boot.system.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Schema(description = "SysLoginUserVo")
public class LoginUserVo {

    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "盐")
    private String salt;

    @Schema(description = "状态，0：停用，1：启用")
    private Boolean status;

    @Schema(description = "角色集合")
    private List<SysRole> roles;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "部门名称")
    private String deptName;

}
