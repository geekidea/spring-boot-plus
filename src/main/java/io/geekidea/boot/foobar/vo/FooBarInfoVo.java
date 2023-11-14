package io.geekidea.boot.foobar.vo;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * FooBar详情VO
 *
 * @author geekidea
 * @since 2023-11-14
 */
@Data
@Schema(description = "FooBar详情VO")
public class FooBarInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "Foo")
    private String foo;

    @Schema(description = "Bar")
    private String bar;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

