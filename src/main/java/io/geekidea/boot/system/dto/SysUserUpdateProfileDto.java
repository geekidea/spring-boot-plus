package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改系统用户个人信息参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "修改系统用户个人信息参数")
public class SysUserUpdateProfileDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空")
    @Length(max = 20, message = "昵称长度超过限制")
    private String nickname;

    @Schema(description = "手机号码")
    @Length(max = 11, message = "手机号码长度超过限制")
    private String phone;

    @Schema(description = "电子邮件")
    @Length(max = 255, message = "电子邮件长度超过限制")
    private String email;

    @Schema(description = "头像")
    private String head;

}


