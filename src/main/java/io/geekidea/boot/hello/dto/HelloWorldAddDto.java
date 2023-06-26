package io.geekidea.boot.hello.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加你好世界参数
 *
 * @author geekidea
 * @since 2023-06-26
 */
@Data
@Schema(description = "添加你好世界参数")
public class HelloWorldAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 20, message = "名称长度超过限制")
    private String name;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}


