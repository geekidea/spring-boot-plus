package io.geekidea.boot.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * App修改用户昵称参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App修改用户昵称参数")
public class AppUserNicknameDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

}


