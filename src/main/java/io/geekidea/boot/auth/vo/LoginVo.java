package io.geekidea.boot.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Schema(description = "登录Vo")
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 3338162302996457084L;

    @Schema(description = "用户Id")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "电子邮件")
    private String email;

    @Schema(description = "性别，0：未知，1：男，2：女，默认0")
    private Integer gender;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "是否是管理员")
    private boolean admin;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "登录时间")
    private Date loginTime;

    @Schema(description = "系统类型 1：管理后台，2：用户端")
    private Integer systemType;

    @Schema(description = "创建时间")
    private Date createTime;

}
