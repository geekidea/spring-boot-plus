package io.geekidea.boot.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author geekidea
 * @date 2023/11/26
 **/
@Data
@Schema(description = "登录AppVo")
public class AppLoginVo implements Serializable {

    private static final long serialVersionUID = 3338162302996457084L;

    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "微信openid")
    private String openid;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "用户角色ID")
    private Long userRoleId;

    @Schema(description = "用户角色编码")
    private String userRoleCode;

    @Schema(description = "用户角色名称")
    private String userRoleName;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "系统类型 1：管理后台，2：用户端")
    private Integer systemType;

}
