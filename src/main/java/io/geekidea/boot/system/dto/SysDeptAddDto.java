package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 添加部门参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "添加部门参数")
public class SysDeptAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 32, message = "部门名称长度超过限制")
    private String name;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

}


