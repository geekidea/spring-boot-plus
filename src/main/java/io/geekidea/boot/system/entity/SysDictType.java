package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@TableName("sys_dict_type")
@Schema(description = "字典类型")
public class SysDictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "字典类型编码")
    private String code;

    @Schema(description = "字典类型名称")
    private String name;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "会员等级")
    private String remark;

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

