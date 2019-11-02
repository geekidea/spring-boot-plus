package io.geekidea.springbootplus.foobar.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * FooBar 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-11-01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "FooBarQueryVo对象", description = "FooBar查询参数")
public class FooBarQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "Name")
    private String name;

    @ApiModelProperty(value = "Foo")
    private String foo;

    @ApiModelProperty(value = "Bar")
    private String bar;

    @ApiModelProperty(value = "Remark")
    private String remark;

    @ApiModelProperty(value = "State，0：Disable，1：Enable")
    private Integer state;

    @ApiModelProperty(value = "Version")
    private Integer version;

    @ApiModelProperty(value = "Create Time")
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    private Date updateTime;

}