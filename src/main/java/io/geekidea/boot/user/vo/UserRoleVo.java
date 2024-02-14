package io.geekidea.boot.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色查询结果
 *
 * @author geekidea
 * @since 2024-01-06
 */
@Data
@Schema(description = "用户角色查询结果")
public class UserRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户角色唯一编码")
    private String code;

    @Schema(description = "用户角色名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人ID")
    private Long createId;

    @Schema(description = "修改人ID")
    private Long updateId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

