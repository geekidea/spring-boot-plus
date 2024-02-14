package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@TableName("sys_menu")
@Schema(description = "系统菜单")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "菜单类型，1：目录，2：菜单，3：权限")
    private Integer type;

    @Schema(description = "菜单编码")
    private String code;

    @Schema(description = "前端路由地址")
    private String routeUrl;

    @Schema(description = "重定向")
    private String routeRedirect;

    @Schema(description = "组件路径")
    private String componentPath;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "是否显示,0：不显示，1：显示")
    private Boolean isShow;

    @Schema(description = "是否缓存，0：否 1：是")
    private Boolean isCache;

    @Schema(description = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "修改人ID")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateId;

    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}

