package io.geekidea.boot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Schema(description = "LoginAppDto")
public class AppLoginDto {

    @Schema(description = "小程序code")
    @NotBlank(message = "小程序code不能为空")
    private String code;

}
