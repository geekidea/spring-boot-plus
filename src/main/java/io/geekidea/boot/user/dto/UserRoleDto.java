package io.geekidea.boot.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 用户角色参数
 *
 * @author geekidea
 * @since 2024-01-06
 */
@Data
@Schema(description = "用户角色参数")
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户角色唯一编码")
    @Length(max = 100, message = "用户角色唯一编码长度超过限制")
    private String code;

    @Schema(description = "用户角色名称")
    @NotBlank(message = "用户角色名称不能为空")
    @Length(max = 32, message = "用户角色名称长度超过限制")
    private String name;

    @Schema(description = "备注")
    @Length(max = 200, message = "备注长度超过限制")
    private String remark;

}


