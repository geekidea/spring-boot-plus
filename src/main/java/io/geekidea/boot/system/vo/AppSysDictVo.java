package io.geekidea.boot.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * App字典数据VO
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App字典数据查询结果")
public class AppSysDictVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "字典名称")
    private String label;

    @Schema(description = "字典类型code")
    private String dictCode;

}

