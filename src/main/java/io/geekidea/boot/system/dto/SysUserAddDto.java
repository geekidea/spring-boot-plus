package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 添加系统用户参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "添加系统用户参数")
public class SysUserAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名长度超过限制")
    private String username;

    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空")
    @Length(max = 20, message = "昵称长度超过限制")
    private String nickname;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码长度超过限制")
    private String password;

    @Schema(description = "盐值")
    @NotBlank(message = "盐值不能为空")
    @Length(max = 32, message = "盐值长度超过限制")
    private String salt;

    @Schema(description = "手机号码")
    @Length(max = 20, message = "手机号码长度超过限制")
    private String phone;

    @Schema(description = "电子邮件")
    @Length(max = 255, message = "电子邮件长度超过限制")
    private String email;

    @Schema(description = "性别，0：女，1：男，默认1")
    private Integer gender;

    @Schema(description = "头像")
    @Length(max = 200, message = "头像长度超过限制")
    private String head;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "部门id")
    private Long deptId;

}


