package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

