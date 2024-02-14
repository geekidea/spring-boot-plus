package io.geekidea.boot.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * App修改用户头像参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App修改用户头像参数")
public class AppUserHeadDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "头像")
    @NotBlank(message = "头像不能为空")
    private String head;

}


