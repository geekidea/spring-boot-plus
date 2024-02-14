package io.geekidea.boot.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 生成代码参数
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "生成代码参数")
public class GeneratorCodeDto {
    private static final long serialVersionUID = 1L;

    @Schema(description = "表名称集合")
    @NotNull(message = "表名称不能为空")
    private List<String> tableNames;

}

