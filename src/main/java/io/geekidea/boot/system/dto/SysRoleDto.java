package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改系统角色参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统角色参数")
public class SysRoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 32, message = "角色名称长度超过限制")
    private String name;

    @Schema(description = "角色唯一编码")
    @Length(max = 100, message = "角色唯一编码长度超过限制")
    private String code;

    @Schema(description = "是否系统内置角色 1：是，0：否")
    private Boolean isSystem;

    @Schema(description = "角色备注")
    @Length(max = 200, message = "角色备注长度超过限制")
    private String remark;

}


