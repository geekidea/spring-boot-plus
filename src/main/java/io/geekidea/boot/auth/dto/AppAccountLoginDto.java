package io.geekidea.boot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Schema(description = "AccountLoginAppDto")
public class AppAccountLoginDto {

    @Schema(description = "用户名", example = "boot")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "密码", example = "e10adc3949ba59abbe56e057f20f883e")
    @NotBlank(message = "密码不能为空")
    private String password;

}
