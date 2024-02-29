package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改系统配置参数
 *
 * @author geekidea
 * @since 2023-11-27
 */
@Data
@Schema(description = "系统配置参数")
public class SysConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "配置名称")
    @NotBlank(message = "配置名称不能为空")
    @Length(max = 100, message = "配置名称长度超过限制")
    private String configName;

    @Schema(description = "配置key")
    @NotBlank(message = "配置key不能为空")
    @Length(max = 100, message = "配置key长度超过限制")
    private String configKey;

    @Schema(description = "配置值")
    @NotBlank(message = "配置值不能为空")
    @Length(max = 200, message = "配置值长度超过限制")
    private String configValue;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "状态 1：正常，0：禁用")
    private Boolean status;

    @Schema(description = "备注")
    @Length(max = 200, message = "备注长度超过限制")
    private String remark;

}


