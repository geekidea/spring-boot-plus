package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型VO
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "字典类型查询结果")
public class SysDictTypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "字典类型编码")
    private String code;

    @Schema(description = "字典类型名称")
    private String name;

    @Schema(description = "是否是系统字典类型")
    private Boolean isSystem;

    @Schema(description = "备注")
    private String remark;

}

