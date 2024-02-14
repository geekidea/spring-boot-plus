package io.geekidea.boot.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * App用户信息VO
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App用户信息查询结果")
public class AppUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "用户角色ID")
    private Long userRoleId;

    @Schema(description = "用户角色名称")
    private String userRoleName;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

    @Schema(description = "备注")
    private String remark;

}

