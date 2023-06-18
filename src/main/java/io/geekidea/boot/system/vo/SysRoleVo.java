package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色VO
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Data
@Schema(description = "系统角色查询结果")
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色唯一编码")
    private String code;

    @Schema(description = "角色状态，0：禁用，1：启用")
    private Boolean status;

    @Schema(description = "角色备注")
    private String remark;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

