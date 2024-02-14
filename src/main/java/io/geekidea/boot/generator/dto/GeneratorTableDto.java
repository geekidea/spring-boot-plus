package io.geekidea.boot.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 修改生成代码表参数
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "生成代码表Dto参数")
public class GeneratorTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "表注释")
    @NotBlank(message = "表注释不能为空")
    @Length(max = 200, message = "表注释长度超过限制")
    private String tableComment;

    @Schema(description = "类名称")
    @NotBlank(message = "类名称不能为空")
    @Length(max = 200, message = "类名称长度超过限制")
    private String className;

    @Schema(description = "模块名称")
    @NotBlank(message = "模块名称不能为空")
    @Length(max = 100, message = "模块名称长度超过限制")
    private String moduleName;

    @Schema(description = "包名称")
    @NotBlank(message = "包名称不能为空")
    @Length(max = 200, message = "包名称长度超过限制")
    private String packageName;

    @Schema(description = "作者")
    @NotBlank(message = "作者不能为空")
    @Length(max = 100, message = "作者长度超过限制")
    private String author;

    @Schema(description = "生成ID类型枚举 AUTO、NONE、INPUT、ASSIGN_ID、ASSIGN_UUID")
    private String idType;

    @Schema(description = "是否生成后端 1：是，0：否")
    private Boolean generatorBackend;

    @Schema(description = "是否生成App后端代码 1：是，0：否")
    private Boolean generatorAppBackend;

    @Schema(description = "是否生成前端 1：是，0：否")
    private Boolean generatorFrontend;

    @Schema(description = "是否校验字段 1：是，0：否")
    private Boolean validateField;

    @Schema(description = "是否启用日志注解 1：是，0：否")
    private Boolean enableLog;

    @Schema(description = "是否启用权限编码 1：是，0：否")
    private Boolean enablePermission;

    @Schema(description = "请求映射风格 1：默认，2：restful，3：全部小写，4：中横线，5：下划线")
    private Integer requestMappingStyle;

    @Schema(description = "默认降序排列名称，如id、create_time，如果为空，则不指定默认排序")
    private String defaultOrderColumnName;

    @Schema(description = "上级菜单ID")
    private Long parentMenuId;

    @Schema(description = "表单布局方式 1：一行一列，2：一行两列")
    private Integer formLayout;

    @Schema(description = "生成方式 1：zip压缩包，2：自定义路径")
    private Integer generatorType;

    @Schema(description = "自定义生成后端路径")
    @Length(max = 300, message = "自定义生成后端路径长度超过限制")
    private String customBackendPath;

    @Schema(description = "自定义生成前端路径")
    @Length(max = 300, message = "自定义生成前端路径长度超过限制")
    private String customFrontendPath;

    @Schema(description = "列集合")
    private List<GeneratorColumnDto> columns;

}


