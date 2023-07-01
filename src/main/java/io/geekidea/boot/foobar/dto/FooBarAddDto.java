package io.geekidea.boot.foobar.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加FooBar参数
 *
 * @author geekidea
 * @since 2023-07-01
 */
@Data
@Schema(description = "添加FooBar参数")
public class FooBarAddDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 20, message = "名称长度超过限制")
    private String name;

    @Schema(description = "Foo")
    @Length(max = 100, message = "Foo长度超过限制")
    private String foo;

    @Schema(description = "Bar")
    @Length(max = 100, message = "Bar长度超过限制")
    private String bar;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}


