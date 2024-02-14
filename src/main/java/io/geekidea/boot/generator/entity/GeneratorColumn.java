package io.geekidea.boot.generator.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.geekidea.boot.generator.vo.GeneratorOptionVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 生成代码列
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@TableName("generator_column")
@Schema(description = "生成代码列")
public class GeneratorColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "表ID")
    private String tableName;

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "字段注释")
    private String columnComment;

    @Schema(description = "自定义注释")
    private String columnCustomComment;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "字段类型")
    private String columnType;

    @Schema(description = "列长度")
    private Integer columnLength;

    @Schema(description = "是否存在长度")
    private Boolean existsLength;

    @Schema(description = "列顺序")
    private Integer columnSort;

    @Schema(description = "字段名称")
    private String propertyName;

    @Schema(description = "属性类型")
    private String propertyType;

    @Schema(description = "是否主键")
    private Boolean isPk;

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "是否有默认值")
    private Boolean isDefaultValue;

    @Schema(description = "是否校验 0：不校验，1：校验")
    private Boolean isValidate;

    @Schema(description = "是否表单字段")
    private Boolean isForm;

    @Schema(description = "是否列表字段")
    private Boolean isList;

    @Schema(description = "是否条件查询")
    private Boolean isQuery;

    @Schema(description = "查询方式 1：等于，2：不等于，3：大于，4：大于等于，5：小于，6：小于等于，7：like，8：日期范围，9：日期时间范围")
    private Integer queryType;

    @Schema(description = "表单类型 1：单行文本，2：多行文本，3：数字框，4：单选框，5：复选框，6：下拉框，7：日期，8：日期时间，9：时间，10：文件上传，11：富文本")
    private Integer formType;

    @Schema(description = "字典类型编码")
    private String dictType;

    @Schema(description = "枚举类型json")
    private String optionJson;

    @Schema(description = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    @Schema(description = "修改人ID")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateId;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @Schema(description = "option集合")
    @TableField(exist = false)
    private List<GeneratorOptionVo> options;

}

