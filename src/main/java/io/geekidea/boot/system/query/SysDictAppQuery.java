package io.geekidea.boot.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * App字典数据查询参数
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Data
@Schema(description = "App字典数据查询参数")
public class SysDictAppQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "字典类型编码")
    private String dictCode;

}

