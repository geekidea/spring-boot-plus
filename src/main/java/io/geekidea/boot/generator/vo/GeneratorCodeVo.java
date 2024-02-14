package io.geekidea.boot.generator.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 生成代码Vo
 *
 * @author geekidea
 * @date 2023/12/30
 **/
@Data
@Schema(description = "生成代码Vo")
public class GeneratorCodeVo {

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "自定义文件路径")
    private String customFilePath;

    @Schema(description = "zip文件路径")
    private String zipFilePath;

    @Schema(description = "文件内容")
    private String fileContent;

    @Schema(description = "生成代码的模板类型 1：后台代码，2：前端代码，3：菜单SQL")
    private Integer templateType;

}
