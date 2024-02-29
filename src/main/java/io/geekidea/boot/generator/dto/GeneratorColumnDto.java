package io.geekidea.boot.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改生成代码列参数
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@Schema(description = "生成代码列Dto参数")
public class GeneratorColumnDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "自定义注释")
    @NotBlank(message = "注释不能为空")
    private String columnCustomComment;

    @Schema(description = "列顺序")
    private Integer columnSort;

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "是否表单字段")
    private Boolean isForm;

    @Schema(description = "是否列表字段")
    private Boolean isList;

    @Schema(description = "是否条件查询")
    private Boolean isQuery;

    @Schema(description = "查询方式 1：等于，2：不等于，3：大于，4：大于等于，5：小于，6：小于等于，7：like，8：日期范围，9：日期时间范围")
    private Integer queryType;

    @Schema(description = "表单类型 1：单行文本，2：多行文本，3：数字框，4：单选框，5：复选框，6：下拉框，7：日期，8：日期时间，9：时间，10：文件上传，11：富文本")
    @NotNull(message = "表单类型不能为空")
    private Integer formType;

    @Schema(description = "字典类型编码")
    private String dictType;

}


