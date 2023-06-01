package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色菜单关系表详情VO
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "角色菜单关系表详情VO")
public class SysRoleMenuInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "菜单id")
    private Long menuId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

