package io.geekidea.boot.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息VO
 *
 * @author geekidea
 * @since 2023-11-30
 */
@Data
@Schema(description = "用户信息查询结果")
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "盐值")
    private String salt;

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

    @Schema(description = "注册时间")
    private Date registerTime;

    @Schema(description = "注册IP")
    private String registerIp;

    @Schema(description = "注册IP区域")
    private String registerIpArea;

    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @Schema(description = "最后一次登录IP区域")
    private String lastLoginIpArea;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

