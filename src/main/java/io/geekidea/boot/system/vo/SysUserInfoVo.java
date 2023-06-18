package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户详情VO
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统用户详情VO")
public class SysUserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "电子邮件")
    private String email;

    @Schema(description = "性别，0：未知，1：男，2：女，默认0")
    private Integer gender;

    @Schema(description = "头像")
    private String head;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "是否是超管 0：否，1：是")
    private Boolean isAdmin;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

