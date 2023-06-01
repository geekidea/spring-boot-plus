package io.geekidea.boot.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加系统菜单参数
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "添加系统菜单参数")
public class SysMenuAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单名称")
    @Length(max = 32, message = "菜单名称长度超过限制")
    private String name;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "菜单唯一编码")
    @NotBlank(message = "菜单唯一编码不能为空")
    @Length(max = 100, message = "菜单唯一编码长度超过限制")
    private String code;

    @Schema(description = "菜单图标")
    @Length(max = 100, message = "菜单图标长度超过限制")
    private String icon;

    @Schema(description = "菜单类型，1：菜单，2：按钮")
    @NotNull(message = "菜单类型，1：菜单，2：按钮不能为空")
    private Integer type;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "前端路由地址")
    @Length(max = 200, message = "前端路由地址长度超过限制")
    private String routeUrl;

    @Schema(description = "路由名称")
    @Length(max = 20, message = "路由名称长度超过限制")
    private String routeName;

    @Schema(description = "重定向")
    @Length(max = 100, message = "重定向长度超过限制")
    private String routeRedirect;

    @Schema(description = "后端请求地址")
    @Length(max = 200, message = "后端请求地址长度超过限制")
    private String requestUrl;

    @Schema(description = "组件路径")
    @Length(max = 100, message = "组件路径长度超过限制")
    private String componentPath;

    @Schema(description = "是否显示,0：不显示，1：显示")
    private Boolean isShow;

    @Schema(description = "是否缓存，0：否 1：是")
    private Boolean isCache;

    @Schema(description = "是否外链，0：否 1：是")
    private Boolean isLink;

    @Schema(description = "链接地址")
    @Length(max = 100, message = "链接地址长度超过限制")
    private String linkUrl;

}


