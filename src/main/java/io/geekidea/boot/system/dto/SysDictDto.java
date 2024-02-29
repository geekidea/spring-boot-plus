package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 修改字典数据参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "修改字典数据参数")
public class SysDictDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "字典值")
    @NotBlank(message = "字典值不能为空")
    @Length(max = 200, message = "字典值长度超过限制")
    private String value;

    @Schema(description = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Length(max = 100, message = "字典名称长度超过限制")
    private String label;

    @Schema(description = "字典类型code")
    @NotBlank(message = "字典类型code不能为空")
    @Length(max = 100, message = "字典类型code长度超过限制")
    private String dictCode;

    @Schema(description = "状态 1：启用，0：禁用")
    private Boolean status;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;

}


