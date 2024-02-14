package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典数据
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@TableName("sys_dict")
@Schema(description = "字典数据")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "字典名称")
    private String label;

    @Schema(description = "字典类型code")
    private String dictCode;

    @Schema(description = "状态 1：启用，0：禁用")
    private Boolean status;

    @Schema(description = "排序值")
    private Integer sort;

    @Schema(description = "备注")
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

