package io.geekidea.boot.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 修改用户信息参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "用户信息参数")
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "账号")
    @Length(max = 20, message = "账号长度超过限制")
    private String username;

    @Schema(description = "昵称")
    @Length(max = 20, message = "昵称长度超过限制")
    private String nickname;

    @Schema(description = "密码")
    @Length(max = 64, message = "密码长度超过限制")
    private String password;

    @Schema(description = "盐值")
    @Length(max = 32, message = "盐值长度超过限制")
    private String salt;

    @Schema(description = "微信openid")
    @Length(max = 200, message = "微信openid长度超过限制")
    private String openid;

    @Schema(description = "手机号码")
    @Length(max = 11, message = "手机号码长度超过限制")
    private String phone;

    @Schema(description = "头像")
    @Length(max = 200, message = "头像长度超过限制")
    private String head;

    @Schema(description = "用户角色ID")
    private Long userRoleId;

    @Schema(description = "状态 1：正常，0：禁用")
    @NotNull(message = "状态 1：正常，0：禁用不能为空")
    private Boolean status;

    @Schema(description = "注册时间")
    @NotNull(message = "注册时间不能为空")
    private Date registerTime;

    @Schema(description = "注册IP")
    @NotBlank(message = "注册IP不能为空")
    @Length(max = 20, message = "注册IP长度超过限制")
    private String registerIp;

    @Schema(description = "最后登录时间")
    @NotNull(message = "最后登录时间不能为空")
    private Date lastLoginTime;

    @Schema(description = "最后登录IP")
    @NotBlank(message = "最后登录IP不能为空")
    @Length(max = 20, message = "最后登录IP长度超过限制")
    private String lastLoginIp;

}


