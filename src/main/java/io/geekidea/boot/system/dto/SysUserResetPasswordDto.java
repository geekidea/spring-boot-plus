package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 管理员重置系统用户密码
 *
 * @author geekidea
 * @date 2023/6/18
 **/
@Data
@Schema(description = "管理员重置系统用户密码")
public class SysUserResetPasswordDto {

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "新密码")
    @NotNull(message = "新密码不能为空")
    @Length(min = 32, max = 32, message = "新密码长度不符合要求")
    private String password;

}
