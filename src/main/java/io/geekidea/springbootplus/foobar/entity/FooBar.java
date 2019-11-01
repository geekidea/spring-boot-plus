package io.geekidea.springbootplus.foobar.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.geekidea.springbootplus.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * FooBar
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FooBar对象", description = "FooBar")
public class FooBar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "Name")
    @NotBlank(message = "Name不能为空")
    private String name;

    @ApiModelProperty(value = "Foo")
    private String foo;

    @ApiModelProperty(value = "Bar")
    @NotBlank(message = "Bar不能为空")
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
