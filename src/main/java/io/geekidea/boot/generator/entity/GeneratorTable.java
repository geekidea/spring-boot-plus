package io.geekidea.boot.generator.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 生成代码表
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Data
@TableName("generator_table")
@Schema(description = "生成代码表")
public class GeneratorTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表注释")
    private String tableComment;

    @Schema(description = "类名称")
    private String className;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "包名称")
    private String packageName;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "生成ID类型枚举")
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
    private String customBackendPath;

    @Schema(description = "自定义生成前端路径")
    private String customFrontendPath;

    @Schema(description = "是否显示默认查询条件 1：是，0：否")
    private Boolean showDefaultQuery;

    @Schema(description = "是否只生成实体类 1：是，0：否")
    private Boolean onlyGeneratorEntity;

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

    @Schema(description = "主键列信息")
    @TableField(exist = false)
    private GeneratorColumn pkColumn;

    @Schema(description = "列信息集合")
    @TableField(exist = false)
    private List<GeneratorColumn> columns;

}

