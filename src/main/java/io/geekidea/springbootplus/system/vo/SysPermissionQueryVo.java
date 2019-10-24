package io.geekidea.springbootplus.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * 系统权限 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysPermissionQueryVo对象", description = "系统权限查询参数")
public class SysPermissionQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "路径")
    private String url;

    @ApiModelProperty(value = "唯一编码")
    private String code;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "类型，1：菜单，2：按钮")
    private Integer type;

    @ApiModelProperty(value = "层级，1：第一级，2：第二级，N：第N级")
    private Integer level;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer state;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}