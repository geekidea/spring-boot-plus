package io.geekidea.boot.generator.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.CaseFormat;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.constant.SystemConstant;
import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.config.properties.GeneratorProperties;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.BasePageQuery;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.generator.bean.GeneratorColumnMapping;
import io.geekidea.boot.generator.constant.GeneratorConstant;
import io.geekidea.boot.generator.entity.GeneratorColumn;
import io.geekidea.boot.generator.entity.GeneratorTable;
import io.geekidea.boot.generator.enums.*;
import io.geekidea.boot.generator.exception.GeneratorException;
import io.geekidea.boot.generator.vo.GeneratorCodeVo;
import io.geekidea.boot.generator.vo.GeneratorColumnDbVo;
import io.geekidea.boot.generator.vo.GeneratorOptionVo;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.util.IdUtil;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author geekidea
 * @date 2023/12/30
 **/
@Slf4j
public class GeneratorUtil {

    /**
     * 列类型映射集合
     */
    private static List<GeneratorColumnMapping> COLUMN_TYPE_MAPPINGS = new ArrayList<>();
    /**
     * 列类型映射Map
     */
    private static Map<String, GeneratorColumnMapping> COLUMN_TYPE_MAPPING_MAP;
    /**
     * 后台实体类代码模板Map
     */
    private static Map<String, String> BACKEND_ENTITY_TEMPLATE_MAP = new LinkedHashMap<>();
    /**
     * 后台代码模板Map
     */
    private static Map<String, String> BACKEND_TEMPLATE_MAP = new LinkedHashMap<>();
    /**
     * APP后台代码模板Map
     */
    private static Map<String, String> APP_BACKEND_TEMPLATE_MAP = new LinkedHashMap<>();
    /**
     * 前端代码模板Map
     */
    private static Map<String, String> FRONTEND_TEMPLATE_MAP = new LinkedHashMap<>();
    /**
     * 菜单SQL模板Map
     */
    private static Map<String, String> MENU_TEMPLATE_MAP = new LinkedHashMap<>();

    static {
        // 列类型映射
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_TINYINT, GeneratorConstant.JAVA_INTEGER, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_SMALLINT, GeneratorConstant.JAVA_INTEGER, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_MEDIUMINT, GeneratorConstant.JAVA_INTEGER, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_INT, GeneratorConstant.JAVA_INTEGER, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_INTEGER, GeneratorConstant.JAVA_INTEGER, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_BIGINT, GeneratorConstant.JAVA_LONG, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_FLOAT, GeneratorConstant.JAVA_FLOAT, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_DOUBLE, GeneratorConstant.JAVA_DOUBLE, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_DECIMAL, GeneratorConstant.JAVA_BIG_DECIMAL, "java.math.BigDecimal"));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_BIT, GeneratorConstant.JAVA_BOOLEAN, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_CHAR, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_VARCHAR, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_TINYTEXT, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_TEXT, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_MEDIUMTEXT, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_LONGTEXT, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_DATE, GeneratorConstant.JAVA_DATE, "java.util.Date"));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_DATETIME, GeneratorConstant.JAVA_DATE, "java.util.Date"));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_TIMESTAMP, GeneratorConstant.JAVA_DATE, "java.util.Date"));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_TIME, GeneratorConstant.JAVA_TIME, "java.sql.Time"));
        COLUMN_TYPE_MAPPINGS.add(new GeneratorColumnMapping(GeneratorConstant.DB_JSON, GeneratorConstant.JAVA_STRING, null));
        COLUMN_TYPE_MAPPING_MAP = COLUMN_TYPE_MAPPINGS.stream().collect(Collectors.toMap(GeneratorColumnMapping::getDataType, GeneratorColumnMapping -> GeneratorColumnMapping));

        // 管理后端实体类生成模板
        BACKEND_ENTITY_TEMPLATE_MAP.put(GeneratorConstant.ENTITY_TEMPLATE_NAME, GeneratorConstant.ENTITY_TEMPLATE_PATH);

        // 管理后端生成模板
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.ENTITY_TEMPLATE_NAME, GeneratorConstant.ENTITY_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.CONTROLLER_TEMPLATE_NAME, GeneratorConstant.CONTROLLER_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.SERVICE_TEMPLATE_NAME, GeneratorConstant.SERVICE_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.SERVICE_IMPL_TEMPLATE_NAME, GeneratorConstant.SERVICE_IMPL_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.MAPPER_TEMPLATE_NAME, GeneratorConstant.MAPPER_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.MAPPER_XML_TEMPLATE_NAME, GeneratorConstant.MAPPER_XML_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.DTO_TEMPLATE_NAME, GeneratorConstant.DTO_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.QUERY_TEMPLATE_NAME, GeneratorConstant.QUERY_TEMPLATE_PATH);
        BACKEND_TEMPLATE_MAP.put(GeneratorConstant.VO_TEMPLATE_NAME, GeneratorConstant.VO_TEMPLATE_PATH);

        // App后端生成模板
        APP_BACKEND_TEMPLATE_MAP.put(GeneratorConstant.APP_CONTROLLER_TEMPLATE_NAME, GeneratorConstant.APP_CONTROLLER_TEMPLATE_PATH);
        APP_BACKEND_TEMPLATE_MAP.put(GeneratorConstant.APP_QUERY_TEMPLATE_NAME, GeneratorConstant.APP_QUERY_TEMPLATE_PATH);
        APP_BACKEND_TEMPLATE_MAP.put(GeneratorConstant.APP_VO_TEMPLATE_NAME, GeneratorConstant.APP_VO_TEMPLATE_PATH);

        // 前端生成模板
        FRONTEND_TEMPLATE_MAP.put(GeneratorConstant.API_TS_TEMPLATE_NAME, GeneratorConstant.API_TS_TEMPLATE_PATH);
        FRONTEND_TEMPLATE_MAP.put(GeneratorConstant.VUE_INDEX_TEMPLATE_NAME, GeneratorConstant.VUE_INDEX_TEMPLATE_PATH);
        FRONTEND_TEMPLATE_MAP.put(GeneratorConstant.VUE_FORM_TEMPLATE_NAME, GeneratorConstant.VUE_FORM_TEMPLATE_PATH);

        // 菜单SQL生成模板
        MENU_TEMPLATE_MAP.put(GeneratorConstant.MENU_SQL_TEMPLATE_NAME, GeneratorConstant.MENU_SQL_TEMPLATE_PATH);
    }

    /**
     * 生成代码
     *
     * @param tableName
     * @param generatorTable
     * @throws Exception
     */
    public static void generatorCode(String tableName, GeneratorTable generatorTable) throws Exception {
        List<GeneratorCodeVo> generatorCodeVos = GeneratorUtil.generatorCodeData(tableName, generatorTable);
        if (CollectionUtils.isNotEmpty(generatorCodeVos)) {
            for (GeneratorCodeVo generatorCodeVo : generatorCodeVos) {
                String fileName = generatorCodeVo.getFileName();
                String customFilePath = generatorCodeVo.getCustomFilePath();
                log.info("generatorFileName：" + fileName);
                log.info("generatorCustomFilePath：" + customFilePath);
                File file = new File(customFilePath);
                String fileContent = generatorCodeVo.getFileContent();
                // 写数据到文件
                FileUtils.writeStringToFile(file, fileContent, GeneratorConstant.UTF8);
            }
        }
    }

    /**
     * 获取生成代码的集合数据
     *
     * @param tableName
     * @param table
     * @return
     * @throws Exception
     */
    public static List<GeneratorCodeVo> generatorCodeData(String tableName, GeneratorTable table) {
        // 根据表名称获取列集合
        if (StringUtils.isBlank(tableName)) {
            throw new GeneratorException("表名称不能为空");
        }
        if (table == null) {
            throw new GeneratorException("表数据不存在");
        }
        List<GeneratorCodeVo> generatorCodeVos = new ArrayList<>();
        // 获取模板数据
        Map<String, Object> dataMap = getTemplateDataMap(tableName, table);
        Boolean onlyGeneratorEntity = table.getOnlyGeneratorEntity();
        Boolean generatorBackend = table.getGeneratorBackend();
        Boolean generatorAppBackend = table.getGeneratorAppBackend();
        Boolean generatorFrontend = table.getGeneratorFrontend();
        if (onlyGeneratorEntity) {
            // 渲染后端实体类模板数据
            Map<String, String> backendEntityTemplateMap = GeneratorUtil.getBackendEntityTemplateMap();
            renderTemplateData(table, generatorCodeVos, dataMap, backendEntityTemplateMap, GeneratorTemplateType.BACKEND);
        } else {
            if (generatorBackend) {
                // 渲染后端模板数据
                Map<String, String> backendTemplateMap = GeneratorUtil.getBackendTemplateMap();
                renderTemplateData(table, generatorCodeVos, dataMap, backendTemplateMap, GeneratorTemplateType.BACKEND);
            }
            if (generatorBackend && generatorAppBackend) {
                // 渲染App后端模板数据
                Map<String, String> appBackendTemplateMap = GeneratorUtil.getAppBackendTemplateMap();
                renderTemplateData(table, generatorCodeVos, dataMap, appBackendTemplateMap, GeneratorTemplateType.BACKEND);
            }
            if (generatorFrontend) {
                // 渲染前端模板数据
                Map<String, String> frontendTemplateMap = GeneratorUtil.getFrontendTemplateMap();
                renderTemplateData(table, generatorCodeVos, dataMap, frontendTemplateMap, GeneratorTemplateType.FRONTEND);
            }
            if (generatorBackend && generatorFrontend) {
                // 渲染菜单SQL模板数据
                Map<String, String> menuTemplateMap = GeneratorUtil.getMenuTemplateMap();
                renderTemplateData(table, generatorCodeVos, dataMap, menuTemplateMap, GeneratorTemplateType.MENU);
            }
        }
        return generatorCodeVos;
    }

    /**
     * 设置表信息
     *
     * @param config
     * @param tableName
     * @param generatorTableDbVo
     * @param table
     */
    public static void setGeneratorTable(GeneratorProperties config, String tableName, GeneratorTableDbVo generatorTableDbVo, GeneratorTable table) {
        // 复制配置的属性到table对象
        BeanUtils.copyProperties(config, table);
        // 设置表名称
        table.setTableName(generatorTableDbVo.getTableName());
        // 设置表注释
        String tableComment = generatorTableDbVo.getTableComment();
        tableComment = GeneratorUtil.getTableComment(tableComment);
        table.setTableComment(tableComment);
        // 表前缀
        List<String> tablePrefixes = config.getTablePrefixes();
        // 获取类名称
        String className = GeneratorUtil.getTableClassName(tableName, tablePrefixes);
        table.setClassName(className);
        // 设置主键ID生成策略
        table.setIdType(config.getIdType().name());
        // 设置请求路径风格
        table.setRequestMappingStyle(RequestMappingStyle.getCode(config.getRequestMappingStyle()));
        // 设置父菜单ID
        Long parentMenuId = config.getParentMenuId();
        if (parentMenuId == null) {
            parentMenuId = SystemConstant.ROOT_MENU_ID;
        }
        table.setParentMenuId(parentMenuId);
        // 设置表单布局方式
        table.setFormLayout(GeneratorFormLayout.getCode(config.getFormLayout()));
        // 设置代码生成方式
        table.setGeneratorType(GeneratorType.getCode(config.getGeneratorType()));
    }

    /**
     * 设置表的列信息
     *
     * @param table
     * @param columns
     */
    public static void setTableColumnInfo(GeneratorTable table, List<GeneratorColumn> columns) {
        if (CollectionUtils.isNotEmpty(columns)) {
            table.setColumns(columns);
            // 设置主键列
            GeneratorColumn pkColumn = GeneratorUtil.getPkColumn(columns);
            table.setPkColumn(pkColumn);
            // 设置列的options集合对象
            GeneratorUtil.setColumnOptions(columns);
        }
    }

    /**
     * 获取生成列集合
     *
     * @param tableName
     * @param columnDbVos
     * @return
     * @throws Exception
     */
    public static List<GeneratorColumn> getGeneratorColumns(String tableName, List<GeneratorColumnDbVo> columnDbVos, Boolean validateField) {
        if (StringUtils.isBlank(tableName)) {
            throw new GeneratorException("表名称不能为空");
        }
        if (CollectionUtils.isEmpty(columnDbVos)) {
            throw new GeneratorException("没有列信息");
        }
        List<GeneratorColumn> columns = new ArrayList<>();
        for (GeneratorColumnDbVo columnDbVo : columnDbVos) {
            String columnName = columnDbVo.getColumnName();
            String columnComment = columnDbVo.getColumnComment();
            String columnCustomComment = GeneratorUtil.getColumnCustomComment(columnComment);
            String dataType = columnDbVo.getDataType();
            String columnType = columnDbVo.getColumnType();
            Integer columnLength = GeneratorUtil.getColumnLength(columnType);
            Integer columnSort = columnDbVo.getColumnSort();
            GeneratorColumn column = new GeneratorColumn();
            column.setTableName(columnDbVo.getTableName());
            column.setColumnName(columnName);
            column.setColumnComment(columnComment);
            column.setDataType(dataType);
            column.setColumnType(columnType);
            column.setColumnLength(columnLength);
            column.setColumnSort(columnSort);
            // 是否长度校验
            boolean existsLength = isExistsLength(columnLength);
            column.setExistsLength(existsLength);
            // 属性名称
            String propertyName = GeneratorUtil.underlineToCamel(columnName);
            column.setPropertyName(propertyName);
            if (StringUtils.isBlank(columnCustomComment)) {
                columnCustomComment = propertyName;
            }
            column.setColumnCustomComment(columnCustomComment);
            String propertyType = getPropertyType(dataType, columnLength);
            column.setPropertyType(propertyType);
            Boolean isPk = columnDbVo.getIsPk();
            Boolean isRequired = columnDbVo.getIsRequired();
            Boolean isDefaultValue = columnDbVo.getIsDefaultValue();
            column.setIsPk(isPk);
            column.setIsRequired(isRequired);
            column.setIsDefaultValue(isDefaultValue);
            column.setIsValidate(false);
            // 是否校验字段
            if (validateField) {
                // 如果不是主键，且是必填字段，没有默认值，则设置为需要校验
                if (!isPk && isRequired && !isDefaultValue) {
                    column.setIsValidate(true);
                }
            }
            column.setQueryType(GeneratorQueryType.EQ.getCode());
            column.setIsForm(false);
            column.setIsList(false);
            column.setIsQuery(false);
            if (isPk) {
                column.setIsRequired(false);
            } else if (GeneratorConstant.KEYWORD_QUERY_FIELDS.contains(propertyName)) {
                column.setIsForm(true);
                column.setIsList(true);
            } else if (GeneratorConstant.CREATE_TIME_FIELD.equals(propertyName)) {
                column.setIsList(true);
                column.setQueryType(GeneratorQueryType.DATE_RANGE.getCode());
            } else if (GeneratorConstant.DB_DATE.equals(dataType)) {
                column.setQueryType(GeneratorQueryType.DATE_RANGE.getCode());
            } else if (GeneratorConstant.DB_DATETIME.equals(dataType)) {
                column.setQueryType(GeneratorQueryType.DATETIME_RANGE.getCode());
            } else if (!GeneratorConstant.COMMON_EXCLUDE_FIELDS.contains(propertyName)) {
                column.setIsForm(true);
                column.setIsList(true);
            }
            String optionJson = getOptionJson(columnComment, columnCustomComment, dataType);
            column.setOptionJson(optionJson);
            Integer formType = getFormType(dataType, columnLength, propertyType, optionJson);
            column.setFormType(formType);
            column.setDictType(null);
            columns.add(column);
        }
        return columns;
    }


    /**
     * 设置列的options集合对象
     *
     * @param columns
     */
    public static void setColumnOptions(List<GeneratorColumn> columns) {
        for (GeneratorColumn column : columns) {
            setColumnOptions(column);
        }
    }

    /**
     * 设置列的options集合对象
     *
     * @param column
     */
    public static void setColumnOptions(GeneratorColumn column) {
        String optionJson = column.getOptionJson();
        if (StringUtils.isBlank(optionJson)) {
            return;
        }
        List<GeneratorOptionVo> optionVos = JSON.parseArray(optionJson, GeneratorOptionVo.class);
        if (CollectionUtils.isNotEmpty(optionVos)) {
            Integer formType = column.getFormType();
            if (formType == 4) {
                for (GeneratorOptionVo option : optionVos) {
                    String value = option.getValue();
                    if ("1".equals(value)) {
                        option.setValue("true");
                    } else {
                        option.setValue("false");
                    }
                }
            }
        }
        column.setOptions(optionVos);
    }

    /**
     * 获取列表单类型
     *
     * @param dataType
     * @param columnLength
     * @param propertyType
     * @param optionJson
     * @return
     */
    public static Integer getFormType(String dataType, Integer columnLength, String propertyType, String optionJson) {
        // 表单类型 1：单行文本，2：多行文本，3：数字框，4：单选框，5：复选框，6：下拉框，7：日期，8：日期时间，9：时间，10：文件上传，11：富文本
        Integer formType = GeneratorFormType.INPUT.getCode();
        if (GeneratorConstant.JAVA_STRING.equals(propertyType)) {
            if (GeneratorConstant.DB_TINYTEXT.equals(dataType)
                    || GeneratorConstant.DB_TEXT.equals(dataType)
                    || GeneratorConstant.DB_MEDIUMTEXT.equals(dataType)
                    || GeneratorConstant.DB_LONGTEXT.equals(dataType)) {
                // 2：多行文本
                formType = GeneratorFormType.TEXTAREA.getCode();
            } else {
                if (columnLength != null && columnLength <= 300) {
                    // 1：单行文本
                    formType = GeneratorFormType.INPUT.getCode();
                } else {
                    // 2：多行文本
                    formType = GeneratorFormType.TEXTAREA.getCode();
                }
            }
        } else if (GeneratorConstant.JAVA_BOOLEAN.equals(propertyType)) {
            // 4：单选框
            formType = GeneratorFormType.RADIO.getCode();
        } else if (GeneratorConstant.JAVA_INTEGER.equals(propertyType) && GeneratorConstant.DB_INT_LIST.contains(dataType)) {
            if (StringUtils.isBlank(optionJson)) {
                // 6：数字框
                formType = GeneratorFormType.NUMBER.getCode();
            } else {
                int length = optionJson.length();
                if (length <= 2) {
                    // 4：单选框
                    formType = GeneratorFormType.RADIO.getCode();
                } else {
                    // 6：下拉框
                    formType = GeneratorFormType.SELECT.getCode();
                }
            }
        } else if (GeneratorConstant.JAVA_INTEGER.equals(propertyType)
                || GeneratorConstant.JAVA_LONG.equals(propertyType)
                || GeneratorConstant.JAVA_FLOAT.equals(propertyType)
                || GeneratorConstant.JAVA_DOUBLE.equals(propertyType)
                || GeneratorConstant.JAVA_BIG_DECIMAL.equals(propertyType)) {
            // 3：数字框
            formType = GeneratorFormType.NUMBER.getCode();
        } else if (GeneratorConstant.JAVA_DATE.equals(propertyType)) {
            if (GeneratorConstant.DB_DATE.equals(dataType)) {
                // 7：日期
                formType = GeneratorFormType.DATE.getCode();
            } else if (GeneratorConstant.DB_DATETIME.equals(dataType) || GeneratorConstant.DB_TIMESTAMP.equals(dataType)) {
                // 8：日期时间
                formType = GeneratorFormType.DATETIME.getCode();
            } else {
                // 7：日期
                formType = GeneratorFormType.DATE.getCode();
            }
        } else if (GeneratorConstant.JAVA_TIME.equals(propertyType)) {
            // 9：时间
            formType = GeneratorFormType.TIME.getCode();
        }
        return formType;
    }

    /**
     * 是否存在长度
     *
     * @param columnLength
     * @return
     */
    public static boolean isExistsLength(Integer columnLength) {
        if (columnLength != null && columnLength > 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取主键列
     *
     * @param columns
     * @return
     */
    public static GeneratorColumn getPkColumn(List<GeneratorColumn> columns) {
        if (CollectionUtils.isNotEmpty(columns)) {
            for (GeneratorColumn column : columns) {
                if (column.getIsPk()) {
                    return column;
                }
            }
        }
        return null;
    }

    /**
     * 渲染模板数据
     *
     * @param table
     * @param generatorCodeVos
     * @param dataMap
     * @param templateMap
     * @param generatorTemplateType
     * @throws Exception
     */
    public static void renderTemplateData(GeneratorTable table, List<GeneratorCodeVo> generatorCodeVos, Map<String, Object> dataMap, Map<String, String> templateMap, GeneratorTemplateType generatorTemplateType) {
        // 获取后端模板
        for (Map.Entry<String, String> entry : templateMap.entrySet()) {
            String templateName = entry.getKey();
            // 获取生成的文件
            GeneratorCodeVo generatorCodeVo = new GeneratorCodeVo();
            // 设置生成文件信息
            setGeneratorFile(table, dataMap, templateName, generatorCodeVo);
            // 模板路径
            String templatePath = entry.getValue();
            // 渲染模板数据
            String fileContent = VelocityUtil.writer(templatePath, dataMap);
            // 组装返回数据
            generatorCodeVo.setFileContent(fileContent);
            generatorCodeVo.setTemplateType(GeneratorTemplateType.getCode(generatorTemplateType));
            generatorCodeVos.add(generatorCodeVo);
        }
    }

    /**
     * 获取模板数据Map
     *
     * @param tableName
     * @param table
     */
    public static Map<String, Object> getTemplateDataMap(String tableName, GeneratorTable table) {
        Map<String, Object> dataMap = new HashMap<>();
        // 设置表信息
        String tableComment = table.getTableComment();
        String className = table.getClassName();
        String packageName = table.getPackageName();
        String moduleName = table.getModuleName();
        String author = table.getAuthor();
        String idType = table.getIdType();
        Boolean generatorAppBackend = table.getGeneratorAppBackend();
        Boolean validateField = table.getValidateField();
        Boolean enableLog = table.getEnableLog();
        Boolean enablePermission = table.getEnablePermission();
        Integer requestMappingStyle = table.getRequestMappingStyle();
        Long parentMenuId = table.getParentMenuId();
        Integer formLayout = table.getFormLayout();
        Integer generatorType = table.getGeneratorType();
        String customBackendPath = table.getCustomBackendPath();
        String customFrontendPath = table.getCustomFrontendPath();
        Boolean showDefaultQuery = table.getShowDefaultQuery();
        GeneratorColumn pkColumn = table.getPkColumn();
        List<GeneratorColumn> columns = table.getColumns();
        dataMap.put("table", table);
        dataMap.put("tableName", tableName);
        dataMap.put("tableComment", tableComment);
        dataMap.put("packageName", packageName);
        dataMap.put("moduleName", moduleName);
        dataMap.put("author", author);
        dataMap.put("idType", idType);
        dataMap.put("generatorAppBackend", generatorAppBackend);
        dataMap.put("validateField", validateField);
        dataMap.put("enableLog", enableLog);
        dataMap.put("enablePermission", enablePermission);
        dataMap.put("defaultOrderColumnName", table.getDefaultOrderColumnName());
        dataMap.put("requestMappingStyle", requestMappingStyle);
        dataMap.put("parentMenuId", parentMenuId);
        dataMap.put("formLayout", formLayout);
        dataMap.put("generatorType", generatorType);
        dataMap.put("customBackendPath", customBackendPath);
        dataMap.put("customFrontendPath", customFrontendPath);
        dataMap.put("showDefaultQuery", showDefaultQuery);
        dataMap.put("columns", columns);

        // 前端需要生成的列集合
        if (CollectionUtils.isNotEmpty(columns)) {
            // 前端表单列集合
            List<GeneratorColumn> formColumns = new ArrayList<>();
            // 默认keyword查询列集合
            List<GeneratorColumn> keywordColumns = new ArrayList<>();
            // 默认app端keyword查询列集合
            List<GeneratorColumn> appKeywordColumns = new ArrayList<>();
            // 前端查询列集合
            List<GeneratorColumn> queryColumns = new ArrayList<>();
            // 前端表格列集合
            List<GeneratorColumn> tableColumns = new ArrayList<>();
            for (GeneratorColumn column : columns) {
                // 属性名称
                String propertyName = column.getPropertyName();
                // 前端表单列
                if (!GeneratorConstant.COMMON_EXCLUDE_FIELDS.contains(propertyName) && !column.getIsPk()) {
                    formColumns.add(column);
                }
                // 默认keyword查询列
                if (GeneratorConstant.KEYWORD_QUERY_FIELDS.contains(propertyName)) {
                    keywordColumns.add(column);
                }
                // 默认app端keyword查询列
                if (GeneratorConstant.APP_KEYWORD_QUERY_FIELDS.contains(propertyName)) {
                    appKeywordColumns.add(column);
                }
                // 前端查询列
                Boolean isQuery = column.getIsQuery();
                if (isQuery != null && isQuery) {
                    queryColumns.add(column);
                }
                // 前端表格列
                Boolean isList = column.getIsList();
                if (isList != null && isList) {
                    tableColumns.add(column);
                }
            }
            dataMap.put("formColumns", formColumns);
            dataMap.put("keywordColumns", keywordColumns);
            dataMap.put("appKeywordColumns", appKeywordColumns);
            dataMap.put("queryColumns", queryColumns);
            dataMap.put("tableColumns", tableColumns);
        }


        String appTableComment = GeneratorConstant.APP_PREFIX + tableComment;
        dataMap.put("appTableComment", appTableComment);

        // 设置主键信息
        dataMap.put("pkColumn", pkColumn);
        String pkIdPropertyType;
        String pkIdColumnName;
        String pkIdPropertyName;
        if (pkColumn != null) {
            pkIdPropertyType = pkColumn.getPropertyType();
            pkIdColumnName = pkColumn.getColumnName();
            pkIdPropertyName = pkColumn.getPropertyName();
        } else {
            pkIdPropertyType = GeneratorConstant.JAVA_SERIALIZABLE;
            pkIdColumnName = GeneratorConstant.ID;
            pkIdPropertyName = GeneratorConstant.ID;
            dataMap.put("existsSerializableType", true);
        }
        dataMap.put("pkIdPropertyType", pkIdPropertyType);
        dataMap.put("pkIdColumnName", pkIdColumnName);
        dataMap.put("pkIdPropertyName", pkIdPropertyName);
        dataMap.put("pascalPkIdPropertyName", GeneratorUtil.camelToPascal(pkIdPropertyName));

        // 设置包名称
        String modulePackageName = packageName + "." + moduleName + ".";
        String entityPackage = modulePackageName + GeneratorConstant.ENTITY_PACKAGE;
        String dtoPackage = modulePackageName + GeneratorConstant.DTO_PACKAGE;
        String voPackage = modulePackageName + GeneratorConstant.VO_PACKAGE;
        String queryPackage = modulePackageName + GeneratorConstant.QUERY_PACKAGE;
        String controllerPackage = modulePackageName + GeneratorConstant.ADMIN_CONTROLLER_PACKAGE;
        String appControllerPackage = modulePackageName + GeneratorConstant.APP_CONTROLLER_PACKAGE;
        String servicePackage = modulePackageName + GeneratorConstant.SERVICE_PACKAGE;
        String serviceImplPackage = modulePackageName + GeneratorConstant.SERVICE_IMPL_PACKAGE;
        String mapperPackage = modulePackageName + GeneratorConstant.MAPPER_PACKAGE;
        dataMap.put("entityPackage", entityPackage);
        dataMap.put("dtoPackage", dtoPackage);
        dataMap.put("voPackage", voPackage);
        dataMap.put("queryPackage", queryPackage);
        dataMap.put("controllerPackage", controllerPackage);
        dataMap.put("appControllerPackage", appControllerPackage);
        dataMap.put("servicePackage", servicePackage);
        dataMap.put("serviceImplPackage", serviceImplPackage);
        dataMap.put("mapperPackage", mapperPackage);

        // 设置类名称
        String entity = className;
        String dtoName = entity + GeneratorConstant.DTO;
        String queryName = entity + GeneratorConstant.QUERY;
        String appQueryName = GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.QUERY;
        String appVoName = GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.VO;
        String voName = entity + GeneratorConstant.VO;
        String controllerName = entity + GeneratorConstant.CONTROLLER;
        String appControllerName = GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.CONTROLLER;
        String serviceName = entity + GeneratorConstant.SERVICE;
        String serviceImplName = entity + GeneratorConstant.SERVICE_IMPL;
        String mapperName = entity + GeneratorConstant.MAPPER;
        String mapperXmlName = mapperName;
        dataMap.put("entity", entity);
        dataMap.put("dtoName", dtoName);
        dataMap.put("queryName", queryName);
        dataMap.put("appQueryName", appQueryName);
        dataMap.put("appVoName", appVoName);
        dataMap.put("voName", voName);
        dataMap.put("controllerName", controllerName);
        dataMap.put("appControllerName", appControllerName);
        dataMap.put("serviceName", serviceName);
        dataMap.put("serviceImplName", serviceImplName);
        dataMap.put("mapperName", mapperName);
        dataMap.put("mapperXmlName", mapperXmlName);
        dataMap.put("superServiceName", IService.class.getSimpleName());
        dataMap.put("superServiceImplName", ServiceImpl.class.getSimpleName());
        dataMap.put("superMapperName", BaseMapper.class.getSimpleName());
        dataMap.put("superPageQueryName", BasePageQuery.class.getSimpleName());

        // 设置对象名称
        String entityObjectName = GeneratorUtil.pascalToCamel(entity);
        String dtoObjectName = GeneratorConstant.LOWER_DTO;
        String queryObjectName = GeneratorConstant.LOWER_QUERY;
        String appQueryObjectName = GeneratorConstant.LOWER_QUERY;
        String voObjectName = GeneratorUtil.pascalToCamel(voName);
        String appVoObjectName = GeneratorUtil.pascalToCamel(appVoName);
        String serviceObjectName = GeneratorUtil.pascalToCamel(serviceName);
        String mapperObjectName = GeneratorUtil.pascalToCamel(mapperName);
        dataMap.put("entityObjectName", entityObjectName);
        dataMap.put("dtoObjectName", dtoObjectName);
        dataMap.put("queryObjectName", queryObjectName);
        dataMap.put("appQueryObjectName", appQueryObjectName);
        dataMap.put("voObjectName", voObjectName);
        dataMap.put("appVoObjectName", appVoObjectName);
        dataMap.put("serviceObjectName", serviceObjectName);
        dataMap.put("mapperObjectName", mapperObjectName);

        // 设置类的包全称
        String entityPackagePath = entityPackage + "." + entity;
        String dtoPackagePath = dtoPackage + "." + dtoName;
        String queryPackagePath = queryPackage + "." + queryName;
        String appQueryPackagePath = queryPackage + "." + appQueryName;
        String voPackagePath = voPackage + "." + voName;
        String appVoPackagePath = voPackage + "." + appVoName;
        String controllerPackagePath = controllerPackage + "." + controllerName;
        String appControllerPackagePath = appControllerPackage + "." + appControllerName;
        String servicePackagePath = servicePackage + "." + serviceName;
        String serviceImplPackagePath = serviceImplPackage + "." + serviceImplName;
        String mapperPackagePath = mapperPackage + "." + mapperName;
        dataMap.put("entityPackagePath", entityPackagePath);
        dataMap.put("dtoPackagePath", dtoPackagePath);
        dataMap.put("queryPackagePath", queryPackagePath);
        dataMap.put("appQueryPackagePath", appQueryPackagePath);
        dataMap.put("voPackagePath", voPackagePath);
        dataMap.put("appVoPackagePath", appVoPackagePath);
        dataMap.put("controllerPackagePath", controllerPackagePath);
        dataMap.put("appControllerPackagePath", appControllerPackagePath);
        dataMap.put("servicePackagePath", servicePackagePath);
        dataMap.put("serviceImplPackagePath", serviceImplPackagePath);
        dataMap.put("mapperPackagePath", mapperPackagePath);
        dataMap.put("sysLogTypePackagePath", SysLogType.class.getName());
        dataMap.put("logPackagePath", Log.class.getName());
        dataMap.put("permissionPackagePath", Permission.class.getName());
        dataMap.put("pagingPackagePath", Paging.class.getName());
        dataMap.put("apiResultPackagePath", ApiResult.class.getName());
        dataMap.put("superServicePackagePath", IService.class.getName());
        dataMap.put("superServiceImplPackagePath", ServiceImpl.class.getName());
        dataMap.put("superMapperPackagePath", BaseMapper.class.getName());
        dataMap.put("businessExceptionPackagePath", BusinessException.class.getName());
        dataMap.put("orderByItemPackagePath", OrderByItem.class.getName());
        dataMap.put("pagingUtilPackagePath", PagingUtil.class.getName());
        dataMap.put("superPageQueryPackagePath", BasePageQuery.class.getName());
        dataMap.put("parameterObjectPackagePath", ParameterObject.class.getName());
        dataMap.put("orderMappingPackagePath", OrderMapping.class.getName());

        // controller请求映射路径及请求方式
        handleRequestMapping(requestMappingStyle, dataMap, pkIdPropertyName, entity, entityObjectName);

        // CRUD方法名称
        String addMethodName = GeneratorConstant.ADD + entity;
        String updateMethodName = GeneratorConstant.UPDATE + entity;
        String deleteMethodName = GeneratorConstant.DELETE + entity;
        String infoMethodName = GeneratorConstant.GET + entity;
        String pageMethodName = GeneratorConstant.GET + entity + GeneratorConstant.PAGE;
        String appInfoMethodName = GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity;
        String appPageMethodName = GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.PAGE;
        dataMap.put("addMethodName", addMethodName);
        dataMap.put("updateMethodName", updateMethodName);
        dataMap.put("deleteMethodName", deleteMethodName);
        dataMap.put("infoMethodName", infoMethodName);
        dataMap.put("pageMethodName", pageMethodName);
        dataMap.put("appInfoMethodName", appInfoMethodName);
        dataMap.put("appPageMethodName", appPageMethodName);

        // 权限编码
        String addPermissionCode = null;
        String updatePermissionCode = null;
        String deletePermissionCode = null;
        String infoPermissionCode = null;
        String pagePermissionCode = null;
        if (enablePermission) {
            String tableColon = GeneratorUtil.underlineToColon(tableName);
            addPermissionCode = tableColon + ":" + GeneratorConstant.ADD;
            updatePermissionCode = tableColon + ":" + GeneratorConstant.UPDATE;
            deletePermissionCode = tableColon + ":" + GeneratorConstant.DELETE;
            infoPermissionCode = tableColon + ":" + GeneratorConstant.INFO;
            pagePermissionCode = tableColon + ":" + GeneratorConstant.LOWER_PAGE;
            dataMap.put("addPermissionCode", addPermissionCode);
            dataMap.put("updatePermissionCode", updatePermissionCode);
            dataMap.put("deletePermissionCode", deletePermissionCode);
            dataMap.put("infoPermissionCode", infoPermissionCode);
            dataMap.put("pagePermissionCode", pagePermissionCode);
        }

        // 方法中文注释
        String addCnComment = GeneratorConstant.ADD_CN + tableComment;
        String updateCnComment = GeneratorConstant.UPDATE_CN + tableComment;
        String deleteCnComment = GeneratorConstant.DELETE_CN + tableComment;
        String infoCnComment = GeneratorConstant.GET_CN + tableComment + GeneratorConstant.INFO_CN;
        String pageCnComment = GeneratorConstant.GET_CN + tableComment + GeneratorConstant.PAGE_CN;
        String appInfoCnComment = GeneratorConstant.GET_CN + GeneratorConstant.APP_PREFIX + tableComment + GeneratorConstant.INFO_CN;
        String appPageCnComment = GeneratorConstant.GET_CN + GeneratorConstant.APP_PREFIX + tableComment + GeneratorConstant.PAGE_CN;
        dataMap.put("addCnComment", addCnComment);
        dataMap.put("updateCnComment", updateCnComment);
        dataMap.put("deleteCnComment", deleteCnComment);
        dataMap.put("infoCnComment", infoCnComment);
        dataMap.put("pageCnComment", pageCnComment);
        dataMap.put("appInfoCnComment", appInfoCnComment);
        dataMap.put("appPageCnComment", appPageCnComment);

        // 其它
        dataMap.put("date", DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT));
        dataMap.put("commonExcludeFields", GeneratorConstant.COMMON_EXCLUDE_FIELDS);
        dataMap.put("keywordQueryFields", GeneratorConstant.KEYWORD_QUERY_FIELDS);
        dataMap.put("fillInsertFields", GeneratorConstant.FILL_INSERT_FIELDS);
        dataMap.put("fillUpdateFields", GeneratorConstant.FILL_UPDATE_FIELDS);

        // 是否存在BigDecimal、Date、Time类型
        boolean existsBigDecimalType = false;
        boolean existsDateType = false;
        boolean existsTimeType = false;
        boolean existsCreateTime = false;
        boolean existsValidate = false;
        for (GeneratorColumn column : columns) {
            String propertyType = column.getPropertyType();
            if (GeneratorConstant.JAVA_BIG_DECIMAL.equals(propertyType)) {
                existsBigDecimalType = true;
            } else if (GeneratorConstant.JAVA_DATE.equals(propertyType)) {
                existsDateType = true;
            } else if (GeneratorConstant.JAVA_TIME.equals(propertyType)) {
                existsTimeType = true;
            }
            if (GeneratorConstant.CREATE_TIME_FIELD.equals(column.getPropertyName())) {
                existsCreateTime = true;
            }
            if (column.getIsValidate()) {
                existsValidate = true;
            }
        }
        dataMap.put("existsBigDecimalType", existsBigDecimalType);
        dataMap.put("existsDateType", existsDateType);
        dataMap.put("existsTimeType", existsTimeType);
        dataMap.put("existsCreateTime", existsCreateTime);
        dataMap.put("existsValidate", existsValidate);

        // 前端相关
        String vueApiPath = String.format(GeneratorConstant.VUE_API_PATH, moduleName + "/" + entityObjectName);
        dataMap.put("vueApiPath", vueApiPath);

        // 设置菜单
        // 目录菜单
        SysMenu dirSysMenu = new SysMenu();
        dirSysMenu.setId(IdUtil.getId());
        dirSysMenu.setName(tableComment + GeneratorConstant.MANAGER_NAME);
        if (parentMenuId == null) {
            parentMenuId = 0L;
        }
        dirSysMenu.setParentId(parentMenuId);
        dirSysMenu.setType(1);
        dirSysMenu.setRouteUrl("/" + moduleName + entity);
        // 菜单
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(IdUtil.getId());
        sysMenu.setName(tableComment + GeneratorConstant.LIST_NAME);
        sysMenu.setParentId(dirSysMenu.getId());
        sysMenu.setType(2);
        sysMenu.setCode(pagePermissionCode);
        sysMenu.setRouteUrl("/" + moduleName + "/" + entityObjectName);
        sysMenu.setComponentPath(moduleName + "/" + entityObjectName + GeneratorConstant.VUE_LIST_PATH);
        // 菜单权限
        List<SysMenu> permissionSysMenus = new ArrayList<>();
        // 添加权限菜单
        SysMenu addSysMenu = new SysMenu();
        addSysMenu.setId(IdUtil.getId());
        addSysMenu.setName(addCnComment);
        addSysMenu.setParentId(sysMenu.getId());
        addSysMenu.setType(3);
        addSysMenu.setCode(addPermissionCode);
        addSysMenu.setSort(1);
        permissionSysMenus.add(addSysMenu);
        // 修改权限菜单
        SysMenu updateSysMenu = new SysMenu();
        updateSysMenu.setId(IdUtil.getId());
        updateSysMenu.setName(updateCnComment);
        updateSysMenu.setParentId(sysMenu.getId());
        updateSysMenu.setType(3);
        updateSysMenu.setCode(updatePermissionCode);
        updateSysMenu.setSort(2);
        permissionSysMenus.add(updateSysMenu);
        // 删除权限菜单
        SysMenu deleteSysMenu = new SysMenu();
        deleteSysMenu.setId(IdUtil.getId());
        deleteSysMenu.setName(deleteCnComment);
        deleteSysMenu.setParentId(sysMenu.getId());
        deleteSysMenu.setType(3);
        deleteSysMenu.setCode(deletePermissionCode);
        deleteSysMenu.setSort(3);
        permissionSysMenus.add(deleteSysMenu);
        // 详情权限菜单
        SysMenu infoSysMenu = new SysMenu();
        infoSysMenu.setId(IdUtil.getId());
        infoSysMenu.setName(infoCnComment);
        infoSysMenu.setParentId(sysMenu.getId());
        infoSysMenu.setType(3);
        infoSysMenu.setCode(infoPermissionCode);
        infoSysMenu.setSort(4);
        permissionSysMenus.add(infoSysMenu);

        dataMap.put("dirSysMenu", dirSysMenu);
        dataMap.put("sysMenu", sysMenu);
        dataMap.put("permissionSysMenus", permissionSysMenus);

        return dataMap;
    }


    /**
     * 获取生成后的文件
     *
     * @param dataMap
     * @param templateName
     */
    public static void setGeneratorFile(GeneratorTable table, Map<String, Object> dataMap, String templateName, GeneratorCodeVo generatorCodeVo) {
        String packageName = table.getPackageName();
        String moduleName = table.getModuleName();
        // 判断自定义后台路径为空或者为/，则输出到当前项目目录下，否则输出到指定项目目录下
        String customBackendPath = table.getCustomBackendPath();
        if (StringUtils.isBlank(customBackendPath) || "/".equals(customBackendPath)) {
            // 写代码到当前项目路径下
            customBackendPath = GeneratorConstant.USER_DIR;
        }
        if (customBackendPath.endsWith("/")) {
            customBackendPath = customBackendPath.substring(0, customBackendPath.length() - 1);
        }
        // 判断自定义前端路径为空或者为/，则输出到当前项目vue目录下，否则输出到指定项目目录下
        String customFrontendPath = table.getCustomFrontendPath();
        if (StringUtils.isBlank(customFrontendPath) || "/".equals(customFrontendPath)) {
            customFrontendPath = GeneratorConstant.USER_DIR + GeneratorConstant.VUE_DIR;
        }
        if (customFrontendPath.endsWith("/")) {
            customFrontendPath = customFrontendPath.substring(0, customFrontendPath.length() - 1);
        }
        String packageFilePath = packageName.replaceAll("\\.", "/");
        String javaModuleDir = GeneratorConstant.SRC_MAIN_JAVA + packageFilePath + "/" + moduleName + "/";
        // java 自定义文件路径
        String customJavaDir = customBackendPath + javaModuleDir;
        // java zip下载路径
        String zipJavaDir = GeneratorConstant.JAVA_DIR + javaModuleDir;
        // xml文件路径
        String mapperXmlDir = GeneratorConstant.SRC_MAIN_RESOURCES_MAPPER + moduleName + "/";
        String customMapperXmlDir = customBackendPath + mapperXmlDir;
        String zipMapperXmlDir = GeneratorConstant.JAVA_DIR + mapperXmlDir;
        // 菜单SQL文件路径
        String customMenuSqlDir = customBackendPath + GeneratorConstant.MENU_SQL_PATH;
        String zipMenuSqlDir = GeneratorConstant.MENU_SQL_PATH;
        // 实体对象名称
        String entityObjectName = MapUtils.getString(dataMap, "entityObjectName");
        // 前端API文件路径
        String vueApiDir = GeneratorConstant.VUE_SRC_API + moduleName + "/";
        String customVueApiDir = customFrontendPath + vueApiDir;
        String zipVueApiDir = GeneratorConstant.VUE_DIR + vueApiDir;
        // 前端页面文件路径
        String vueViewDir = GeneratorConstant.VUE_SRC_VIEWS + moduleName + "/" + entityObjectName + "/";
        String customVueViewDir = customFrontendPath + vueViewDir;
        String zipVueViewDir = GeneratorConstant.VUE_DIR + vueViewDir;
        String fileName = null;
        String filePath = null;
        String customFilePath = null;
        String zipFilePath = null;
        if (GeneratorConstant.APP_CONTROLLER_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "appControllerName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.APP_CONTROLLER_PACKAGE_FILE_PATH + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.APP_QUERY_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "appQueryName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.QUERY_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.APP_VO_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "appVoName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.VO_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.CONTROLLER_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "controllerName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.ADMIN_CONTROLLER_PACKAGE_FILE_PATH + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.DTO_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "dtoName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.DTO_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.ENTITY_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "entity") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.ENTITY_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.MAPPER_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "mapperName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.MAPPER_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.MAPPER_XML_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "mapperXmlName") + GeneratorConstant.DOT_XML;
            customFilePath = customMapperXmlDir + fileName;
            zipFilePath = zipMapperXmlDir + fileName;
        } else if (GeneratorConstant.QUERY_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "queryName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.QUERY_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.SERVICE_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "serviceName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.SERVICE_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.SERVICE_IMPL_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "serviceImplName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.SERVICE_IMPL_PACKAGE_FILE_PATH + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.VO_TEMPLATE_NAME.equals(templateName)) {
            fileName = MapUtils.getString(dataMap, "voName") + GeneratorConstant.DOT_JAVA;
            filePath = GeneratorConstant.VO_PACKAGE + "/" + fileName;
            customFilePath = customJavaDir + filePath;
            zipFilePath = zipJavaDir + filePath;
        } else if (GeneratorConstant.MENU_SQL_TEMPLATE_NAME.equals(templateName)) {
            fileName = String.format(GeneratorConstant.MENU_SQL_FORMAT_FILE_NAME, table.getTableName());
            customFilePath = customMenuSqlDir + fileName;
            zipFilePath = zipMenuSqlDir + fileName;
        } else if (GeneratorConstant.API_TS_TEMPLATE_NAME.equals(templateName)) {
            fileName = String.format(GeneratorConstant.API_TS_FORMAT_FILE_NAME, entityObjectName);
            customFilePath = customVueApiDir + fileName;
            zipFilePath = zipVueApiDir + fileName;
        } else if (GeneratorConstant.VUE_INDEX_TEMPLATE_NAME.equals(templateName)) {
            fileName = GeneratorConstant.VUE_INDEX_FILE_NAME;
            customFilePath = customVueViewDir + fileName;
            zipFilePath = zipVueViewDir + fileName;
        } else if (GeneratorConstant.VUE_FORM_TEMPLATE_NAME.equals(templateName)) {
            fileName = GeneratorConstant.VUE_FORM_FILE_NAME;
            customFilePath = customVueViewDir + fileName;
            zipFilePath = zipVueViewDir + fileName;
        }
        generatorCodeVo.setFileName(fileName);
        generatorCodeVo.setCustomFilePath(customFilePath);
        generatorCodeVo.setZipFilePath(zipFilePath);
    }

    /**
     * 处理请求映射
     *
     * @param requestMappingStyle
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void handleRequestMapping(Integer requestMappingStyle, Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        if (RequestMappingStyle.DEFAULT.getCode().equals(requestMappingStyle)) {
            // 普通方法，默认风格
            setDefaultRequestMappingStyle(dataMap, pkIdPropertyName, entity, entityObjectName);
        } else if (RequestMappingStyle.RESTFUL.getCode().equals(requestMappingStyle)) {
            // restful
            setRestfulRequestMappingStyle(dataMap, pkIdPropertyName, entity, entityObjectName);
        } else if (RequestMappingStyle.SMALL_LETTER.getCode().equals(requestMappingStyle)) {
            // 全部字母小写
            setSmallLetterRequestMappingStyle(dataMap, pkIdPropertyName, entity, entityObjectName);
        } else if (RequestMappingStyle.HYPHEN.getCode().equals(requestMappingStyle)) {
            // 中横线连接
            setHyphenRequestMappingStyle(dataMap, pkIdPropertyName, entity, entityObjectName);
        } else if (RequestMappingStyle.UNDERLINE.getCode().equals(requestMappingStyle)) {
            // 下划线连接
            setUnderlineRequestMappingStyle(dataMap, pkIdPropertyName, entity, entityObjectName);
        }
    }

    /**
     * 设置默认风格的请求路径映射方式
     *
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void setDefaultRequestMappingStyle(Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        String controllerRequestPath = GeneratorConstant.ADMIN_MAPPING + "/" + entityObjectName;
        String controllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, controllerRequestPath);
        String appControllerRequestPath = GeneratorConstant.APP_MAPPING + "/" + entityObjectName;
        String appControllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, appControllerRequestPath);
        String addRequestPath = "/" + GeneratorConstant.ADD + entity;
        String addRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, addRequestPath);
        String updateRequestPath = "/" + GeneratorConstant.UPDATE + entity;
        String updateRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, updateRequestPath);
        String deleteRequestPath = "/" + GeneratorConstant.DELETE + entity + "/";
        String deleteRequestFormatPath = deleteRequestPath + "{" + pkIdPropertyName + "}";
        String deleteRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, deleteRequestFormatPath);
        String infoRequestPath = "/" + GeneratorConstant.GET + entity + "/";
        String infoRequestFormatPath = infoRequestPath + "{" + pkIdPropertyName + "}";
        String infoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, infoRequestFormatPath);
        String pageRequestPath = "/" + GeneratorConstant.GET + entity + GeneratorConstant.PAGE;
        String pageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, pageRequestPath);
        String appInfoRequestPath = "/" + GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + "/{" + pkIdPropertyName + "}";
        String appInfoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appInfoRequestPath);
        String appPageRequestPath = "/" + GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.PAGE;
        String appPageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appPageRequestPath);
        String pageParamAnnotation = GeneratorConstant.REQUEST_BODY;
        dataMap.put("controllerRequestMapping", controllerRequestMapping);
        dataMap.put("appControllerRequestMapping", appControllerRequestMapping);
        dataMap.put("addRequestMapping", addRequestMapping);
        dataMap.put("updateRequestMapping", updateRequestMapping);
        dataMap.put("deleteRequestMapping", deleteRequestMapping);
        dataMap.put("infoRequestMapping", infoRequestMapping);
        dataMap.put("pageRequestMapping", pageRequestMapping);
        dataMap.put("appInfoRequestMapping", appInfoRequestMapping);
        dataMap.put("appPageRequestMapping", appPageRequestMapping);
        dataMap.put("pageParamAnnotation", pageParamAnnotation);

        dataMap.put("controllerRequestPath", controllerRequestPath);
        dataMap.put("appControllerRequestPath", appControllerRequestPath);
        dataMap.put("addRequestPath", addRequestPath);
        dataMap.put("updateRequestPath", updateRequestPath);
        dataMap.put("deleteRequestPath", deleteRequestPath);
        dataMap.put("infoRequestPath", infoRequestPath);
        dataMap.put("pageRequestPath", pageRequestPath);
        dataMap.put("appInfoRequestPath", appInfoRequestPath);
        dataMap.put("appPageRequestPath", appPageRequestPath);
    }

    /**
     * 设置restful风格的请求路径映射方式
     *
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void setRestfulRequestMappingStyle(Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        String entityMapping = GeneratorUtil.camelToBackslash(entityObjectName);
        String controllerRequestMapping = GeneratorConstant.ADMIN_MAPPING + "/" + entityMapping;
        controllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, controllerRequestMapping);
        String appControllerRequestMapping = GeneratorConstant.APP_MAPPING + "/" + entityMapping;
        appControllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, appControllerRequestMapping);
        String addRequestMapping = GeneratorConstant.POST_MAPPING;
        String updateRequestMapping = GeneratorConstant.PUT_MAPPING;
        String deleteRequestMapping = "/{" + pkIdPropertyName + "}";
        deleteRequestMapping = String.format(GeneratorConstant.DELETE_MAPPING_FORMAT, deleteRequestMapping);
        String infoRequestMapping = "/{" + pkIdPropertyName + "}";
        infoRequestMapping = String.format(GeneratorConstant.GET_MAPPING_FORMAT, infoRequestMapping);
        String pageRequestMapping = "/" + GeneratorConstant.LOWER_PAGE;
        pageRequestMapping = String.format(GeneratorConstant.GET_MAPPING_FORMAT, pageRequestMapping);
        String appInfoRequestMapping = "/{" + pkIdPropertyName + "}";
        appInfoRequestMapping = String.format(GeneratorConstant.GET_MAPPING_FORMAT, appInfoRequestMapping);
        String appPageRequestMapping = "/" + GeneratorConstant.LOWER_PAGE;
        appPageRequestMapping = String.format(GeneratorConstant.GET_MAPPING_FORMAT, appPageRequestMapping);
        String pageParamAnnotation = GeneratorConstant.PARAMETER_OBJECT;
        dataMap.put("controllerRequestMapping", controllerRequestMapping);
        dataMap.put("appControllerRequestMapping", appControllerRequestMapping);
        dataMap.put("addRequestMapping", addRequestMapping);
        dataMap.put("updateRequestMapping", updateRequestMapping);
        dataMap.put("deleteRequestMapping", deleteRequestMapping);
        dataMap.put("infoRequestMapping", infoRequestMapping);
        dataMap.put("pageRequestMapping", pageRequestMapping);
        dataMap.put("appInfoRequestMapping", appInfoRequestMapping);
        dataMap.put("appPageRequestMapping", appPageRequestMapping);
        dataMap.put("pageParamAnnotation", pageParamAnnotation);
    }

    /**
     * 设置全部字母小写风格的请求路径映射方式
     *
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void setSmallLetterRequestMappingStyle(Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        String controllerRequestMapping = GeneratorConstant.ADMIN_MAPPING + "/" + entityObjectName;
        controllerRequestMapping = GeneratorUtil.toLower(controllerRequestMapping);
        controllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, controllerRequestMapping);
        String appControllerRequestMapping = GeneratorConstant.APP_MAPPING + "/" + entityObjectName;
        appControllerRequestMapping = GeneratorUtil.toLower(appControllerRequestMapping);
        appControllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, appControllerRequestMapping);
        String addRequestMapping = "/" + GeneratorConstant.ADD + entity;
        addRequestMapping = GeneratorUtil.toLower(addRequestMapping);
        addRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, addRequestMapping);
        String updateRequestMapping = "/" + GeneratorConstant.UPDATE + entity;
        updateRequestMapping = GeneratorUtil.toLower(updateRequestMapping);
        updateRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, updateRequestMapping);
        String deleteRequestMapping = "/" + GeneratorConstant.DELETE + entity + "/{" + pkIdPropertyName + "}";
        deleteRequestMapping = GeneratorUtil.toLower(deleteRequestMapping);
        deleteRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, deleteRequestMapping);
        String infoRequestMapping = "/" + GeneratorUtil.toLower(GeneratorConstant.GET + entity) + "/{" + pkIdPropertyName + "}";
        infoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, infoRequestMapping);
        String pageRequestMapping = "/" + GeneratorConstant.GET + entity + GeneratorConstant.PAGE;
        pageRequestMapping = GeneratorUtil.toLower(pageRequestMapping);
        pageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, pageRequestMapping);
        String appInfoRequestMapping = "/" + GeneratorUtil.toLower(GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity) + "/{" + pkIdPropertyName + "}";
        appInfoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appInfoRequestMapping);
        String appPageRequestMapping = "/" + GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.PAGE;
        appPageRequestMapping = GeneratorUtil.toLower(appPageRequestMapping);
        appPageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appPageRequestMapping);
        String pageParamAnnotation = GeneratorConstant.REQUEST_BODY;
        dataMap.put("controllerRequestMapping", controllerRequestMapping);
        dataMap.put("appControllerRequestMapping", appControllerRequestMapping);
        dataMap.put("addRequestMapping", addRequestMapping);
        dataMap.put("updateRequestMapping", updateRequestMapping);
        dataMap.put("deleteRequestMapping", deleteRequestMapping);
        dataMap.put("infoRequestMapping", infoRequestMapping);
        dataMap.put("pageRequestMapping", pageRequestMapping);
        dataMap.put("appInfoRequestMapping", appInfoRequestMapping);
        dataMap.put("appPageRequestMapping", appPageRequestMapping);
        dataMap.put("pageParamAnnotation", pageParamAnnotation);
    }

    /**
     * 设置中横线风格的请求路径映射方式
     *
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void setHyphenRequestMappingStyle(Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        String controllerRequestMapping = GeneratorConstant.ADMIN_MAPPING + "/" + entityObjectName;
        controllerRequestMapping = GeneratorUtil.toHyphen(controllerRequestMapping);
        controllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, controllerRequestMapping);
        String appControllerRequestMapping = GeneratorConstant.APP_MAPPING + "/" + entityObjectName;
        appControllerRequestMapping = GeneratorUtil.toHyphen(appControllerRequestMapping);
        appControllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, appControllerRequestMapping);
        String addRequestMapping = "/" + GeneratorConstant.ADD + entity;
        addRequestMapping = GeneratorUtil.toHyphen(addRequestMapping);
        addRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, addRequestMapping);
        String updateRequestMapping = "/" + GeneratorConstant.UPDATE + entity;
        updateRequestMapping = GeneratorUtil.toHyphen(updateRequestMapping);
        updateRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, updateRequestMapping);
        String deleteRequestMapping = "/" + GeneratorConstant.DELETE + entity + "/{" + pkIdPropertyName + "}";
        deleteRequestMapping = GeneratorUtil.toHyphen(deleteRequestMapping);
        deleteRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, deleteRequestMapping);
        String infoRequestMapping = "/" + GeneratorUtil.toHyphen(GeneratorConstant.GET + entity) + "/{" + pkIdPropertyName + "}";
        infoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, infoRequestMapping);
        String pageRequestMapping = "/" + GeneratorConstant.GET + entity + GeneratorConstant.PAGE;
        pageRequestMapping = GeneratorUtil.toHyphen(pageRequestMapping);
        pageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, pageRequestMapping);
        String appInfoRequestMapping = "/" + GeneratorUtil.toHyphen(GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity) + "/{" + pkIdPropertyName + "}";
        appInfoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appInfoRequestMapping);
        String appPageRequestMapping = "/" + GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.PAGE;
        appPageRequestMapping = GeneratorUtil.toHyphen(appPageRequestMapping);
        appPageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appPageRequestMapping);
        String pageParamAnnotation = GeneratorConstant.REQUEST_BODY;
        dataMap.put("controllerRequestMapping", controllerRequestMapping);
        dataMap.put("appControllerRequestMapping", appControllerRequestMapping);
        dataMap.put("addRequestMapping", addRequestMapping);
        dataMap.put("updateRequestMapping", updateRequestMapping);
        dataMap.put("deleteRequestMapping", deleteRequestMapping);
        dataMap.put("infoRequestMapping", infoRequestMapping);
        dataMap.put("pageRequestMapping", pageRequestMapping);
        dataMap.put("appInfoRequestMapping", appInfoRequestMapping);
        dataMap.put("appPageRequestMapping", appPageRequestMapping);
        dataMap.put("pageParamAnnotation", pageParamAnnotation);
    }

    /**
     * 设置下划线风格的请求路径映射方式
     *
     * @param dataMap
     * @param pkIdPropertyName
     * @param entity
     * @param entityObjectName
     */
    public static void setUnderlineRequestMappingStyle(Map<String, Object> dataMap, String pkIdPropertyName, String entity, String entityObjectName) {
        String controllerRequestMapping = GeneratorConstant.ADMIN_MAPPING + "/" + entityObjectName;
        controllerRequestMapping = GeneratorUtil.toUnderline(controllerRequestMapping);
        controllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, controllerRequestMapping);
        String appControllerRequestMapping = GeneratorConstant.APP_MAPPING + "/" + entityObjectName;
        appControllerRequestMapping = GeneratorUtil.toUnderline(appControllerRequestMapping);
        appControllerRequestMapping = String.format(GeneratorConstant.REQUEST_MAPPING_FORMAT, appControllerRequestMapping);
        String addRequestMapping = "/" + GeneratorConstant.ADD + entity;
        addRequestMapping = GeneratorUtil.toUnderline(addRequestMapping);
        addRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, addRequestMapping);
        String updateRequestMapping = "/" + GeneratorConstant.UPDATE + entity;
        updateRequestMapping = GeneratorUtil.toUnderline(updateRequestMapping);
        updateRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, updateRequestMapping);
        String deleteRequestMapping = "/" + GeneratorConstant.DELETE + entity + "/{" + pkIdPropertyName + "}";
        deleteRequestMapping = GeneratorUtil.toUnderline(deleteRequestMapping);
        deleteRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, deleteRequestMapping);
        String infoRequestMapping = "/" + GeneratorUtil.toUnderline(GeneratorConstant.GET + entity) + "/{" + pkIdPropertyName + "}";
        infoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, infoRequestMapping);
        String pageRequestMapping = "/" + GeneratorConstant.GET + entity + GeneratorConstant.PAGE;
        pageRequestMapping = GeneratorUtil.toUnderline(pageRequestMapping);
        pageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, pageRequestMapping);
        String appInfoRequestMapping = "/" + GeneratorUtil.toUnderline(GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity) + "/{" + pkIdPropertyName + "}";
        appInfoRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appInfoRequestMapping);
        String appPageRequestMapping = "/" + GeneratorConstant.GET + GeneratorConstant.APP_PREFIX + entity + GeneratorConstant.PAGE;
        appPageRequestMapping = GeneratorUtil.toUnderline(appPageRequestMapping);
        appPageRequestMapping = String.format(GeneratorConstant.POST_MAPPING_FORMAT, appPageRequestMapping);
        String pageParamAnnotation = GeneratorConstant.REQUEST_BODY;
        dataMap.put("controllerRequestMapping", controllerRequestMapping);
        dataMap.put("appControllerRequestMapping", appControllerRequestMapping);
        dataMap.put("addRequestMapping", addRequestMapping);
        dataMap.put("updateRequestMapping", updateRequestMapping);
        dataMap.put("deleteRequestMapping", deleteRequestMapping);
        dataMap.put("infoRequestMapping", infoRequestMapping);
        dataMap.put("pageRequestMapping", pageRequestMapping);
        dataMap.put("appInfoRequestMapping", appInfoRequestMapping);
        dataMap.put("appPageRequestMapping", appPageRequestMapping);
        dataMap.put("pageParamAnnotation", pageParamAnnotation);
    }

    /**
     * 获取属性类型
     *
     * @param dataType
     * @param columnLength
     * @return
     */
    public static String getPropertyType(String dataType, Integer columnLength) {
        // 转换类型
        columnLength = columnLength == null ? 0 : columnLength;
        if (GeneratorConstant.DB_TINYINT.equals(dataType) && columnLength == 1) {
            // tinyint(1)设置为Boolean类型
            return GeneratorConstant.JAVA_BOOLEAN;
        } else {
            return getPropertyType(dataType);
        }
    }

    /**
     * 获取属性类型
     *
     * @param dataType
     * @return
     */
    public static String getPropertyType(String dataType) {
        return COLUMN_TYPE_MAPPING_MAP.get(dataType).getPropertyType();
    }

    /**
     * 获取OptionJson
     *
     * @param columnComment
     * @param columnCustomComment
     * @param dataType
     * @return
     */
    public static String getOptionJson(String columnComment, String columnCustomComment, String dataType) {
        if (GeneratorConstant.DB_INT_LIST.contains(dataType)) {
            // 枚举类型json
            List<GeneratorOptionVo> autoColumnOptions = GeneratorUtil.getAutoColumnOptions(columnComment, columnCustomComment);
            if (CollectionUtils.isNotEmpty(autoColumnOptions)) {
                String optionJson = JSON.toJSONString(autoColumnOptions);
                return optionJson;
            }
        }
        return null;
    }

    /**
     * 下划线专程驼峰命名
     * sys_user --> sysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    /**
     * 下划线转换成帕斯卡命名
     * sys_user --> SysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

    /**
     * 骆驼转换成帕斯卡命名
     * roleId --> RoleId
     *
     * @param camel
     * @return
     */
    public static String camelToPascal(String camel) {
        if (StringUtils.isNotBlank(camel)) {
            return NamingStrategy.capitalFirst(camel);
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", ":");
        }
        return null;
    }

    /**
     * 帕斯卡转换成驼峰命名法
     * SysUser --> sysUser
     *
     * @param pascal
     * @return
     */
    public static String pascalToCamel(String pascal) {
        if (StringUtils.isNotBlank(pascal)) {
            return pascal.substring(0, 1).toLowerCase() + pascal.substring(1);
        }
        return pascal;
    }

    /**
     * 下划线转换成横线连接命名
     * sys_user --> sys-user
     *
     * @param underline
     * @return
     */
    public static String underlineToHyphen(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            String string = underline.toLowerCase();
            return string.replaceAll("_", "-");
        }
        return null;
    }

    /**
     * 转换成中横线连接
     * sys-user
     *
     * @param value
     * @return
     */
    public static String toHyphen(String value) {
        if (StringUtils.isNotBlank(value)) {
            String result = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);
            return result.replaceAll("_", "-");
        }
        return null;
    }

    /**
     * 转换成下划线
     *
     * @param value
     * @return
     */
    public static String toUnderline(String value) {
        if (StringUtils.isNotBlank(value)) {
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value);
        }
        return null;
    }

    /**
     * 转换成反斜杠
     *
     * @param value
     * @return
     */
    public static String toBackslash(String value) {
        if (StringUtils.isNotBlank(value)) {
            String result = toHyphen(value);
            return result.replaceAll("-", "/");
        }
        return null;
    }

    /**
     * 驼峰转换成反斜杠
     * sysUser --> sys/user
     *
     * @param value
     * @return
     */
    public static String camelToBackslash(String value) {
        if (StringUtils.isNotBlank(value)) {
            String result = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, value);
            return result.replaceAll("-", "/");
        }
        return null;
    }

    /**
     * 转换成小写
     *
     * @param value
     * @return
     */
    public static String toLower(String value) {
        if (StringUtils.isNotBlank(value)) {
            return value.toLowerCase();
        }
        return null;
    }

    /**
     * 获取没有前缀的表对应的类名称
     *
     * @param tableName
     * @return
     */
    public static String getTableClassName(String tableName, List<String> tablePrefixes) {
        String notPrefixTableName = tableName;
        if (CollectionUtils.isNotEmpty(tablePrefixes)) {
            for (String tablePrefix : tablePrefixes) {
                if (tableName.startsWith(tablePrefix)) {
                    notPrefixTableName = tableName.substring(tablePrefix.length());
                    break;
                }
            }
        }
        // 类名称 默认将表名称转换成帕斯卡命名发
        String className = underlineToPascal(notPrefixTableName);
        return className;
    }

    /**
     * 获取列长度
     *
     * @param columnType
     * @return
     */
    public static Integer getColumnLength(String columnType) {
        int index = columnType.indexOf("(");
        int lastIndex = columnType.lastIndexOf(")");
        if (index != -1) {
            String substring = columnType.substring(index + 1, lastIndex);
            int i = substring.indexOf(",");
            if (i != -1) {
                substring = substring.substring(0, i);
            }
            return Integer.parseInt(substring);
        }
        return null;
    }

    /**
     * 获取处理后的表注释
     *
     * @param tableComment
     * @return
     */
    public static String getTableComment(String tableComment) {
        if (StringUtils.isBlank(tableComment)) {
            return null;
        }
        for (String removeTableNameSuffix : GeneratorConstant.REMOVE_TABLE_NAME_SUFFIXES) {
            if (tableComment.endsWith(removeTableNameSuffix)) {
                return tableComment.substring(0, tableComment.length() - 1);
            }
        }
        return tableComment;
    }

    /**
     * 获取注释前半部分
     *
     * @param columnComment
     * @return
     */
    public static String getColumnCustomComment(String columnComment) {
        if (StringUtils.isBlank(columnComment)) {
            return null;
        }
        int index = columnComment.indexOf(" ");
        if (index != -1) {
            return columnComment.substring(0, index);
        }
        index = columnComment.indexOf("，");
        if (index != -1) {
            return columnComment.substring(0, index);
        }
        index = columnComment.indexOf(",");
        if (index != -1) {
            return columnComment.substring(0, index);
        }
        return columnComment;
    }

    /**
     * 获取注释后半部分
     *
     * @param columnComment
     * @param columnCustomComment
     * @return
     */
    public static List<GeneratorOptionVo> getAutoColumnOptions(String columnComment, String columnCustomComment) {
        try {
            if (StringUtils.isBlank(columnComment)) {
                return null;
            }
            String commentOption = null;
            if (StringUtils.isNotBlank(columnCustomComment)) {
                int columnCommentLength = columnComment.length();
                int columnCustomCommentLength = columnCustomComment.length();
                if (columnCommentLength > columnCustomCommentLength + 1) {
                    commentOption = columnComment.substring(columnCustomComment.length() + 1, columnCommentLength);
                }
            }
            if (StringUtils.isBlank(commentOption)) {
                return null;
            }
            // 按逗号拆分
            commentOption = commentOption.replaceAll("，", ",");
            commentOption = commentOption.replaceAll("，", ",");
            commentOption = commentOption.replaceAll(";", ",");
            commentOption = commentOption.replaceAll("；", ",");
            commentOption = commentOption.replaceAll("\\(", "");
            commentOption = commentOption.replaceAll("\\（", "");
            commentOption = commentOption.replaceAll("\\)", "");
            commentOption = commentOption.replaceAll("\\）", "");
            List<GeneratorOptionVo> optionVos = new ArrayList<>();
            int index = commentOption.indexOf(",");
            if (index != -1) {
                autoSplitOptions(",", commentOption, optionVos);
            } else {
                // 按空格拆分
                autoSplitOptions(" ", commentOption, optionVos);
            }
            return optionVos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自动拆分option
     *
     * @param splitSymbol
     * @param commentOption
     * @param optionVos
     */
    private static void autoSplitOptions(String splitSymbol, String commentOption, List<GeneratorOptionVo> optionVos) {
        int index;
        String[] strings = commentOption.split(splitSymbol);
        if (ArrayUtils.isNotEmpty(strings) && strings.length > 1) {
            for (String string : strings) {
                string = string.replaceAll("：", ":");
                string = string.replaceAll("-", ":");
                string = string.replaceAll(" ", ":");
                index = string.indexOf(":");
                if (index != -1) {
                    String[] optionArray = string.split(":");
                    if (ArrayUtils.isNotEmpty(optionArray) && optionArray.length == 2) {
                        String value = optionArray[0].trim();
                        String label = optionArray[1].trim();
                        GeneratorOptionVo optionVo = new GeneratorOptionVo();
                        optionVo.setValue(value);
                        optionVo.setLabel(label);
                        optionVos.add(optionVo);
                    }
                }
            }
        }
    }

    /**
     * 获取后端模板列表
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getBackendEntityTemplateMap() {
        return BACKEND_ENTITY_TEMPLATE_MAP;
    }

    /**
     * 获取后端模板列表
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getBackendTemplateMap() {
        return BACKEND_TEMPLATE_MAP;
    }

    /**
     * 获取App后端模板列表
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getAppBackendTemplateMap() {
        return APP_BACKEND_TEMPLATE_MAP;
    }

    /**
     * 获取前端模板列表
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getFrontendTemplateMap() {
        return FRONTEND_TEMPLATE_MAP;
    }


    /**
     * 获取菜单SQL模板列表
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getMenuTemplateMap() {
        return MENU_TEMPLATE_MAP;
    }


}
