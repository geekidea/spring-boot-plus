package io.geekidea.boot.generator.handler;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.CaseFormat;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.generator.config.GeneratorConfig;
import io.geekidea.boot.generator.constant.GenerateConstant;
import io.geekidea.boot.generator.engine.EnhanceFileTemplateEngine;
import io.geekidea.boot.generator.enums.DefaultOrderType;
import io.geekidea.boot.generator.enums.GeneratorType;
import io.geekidea.boot.generator.enums.RequestMappingType;
import io.geekidea.boot.util.PagingUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;

import java.util.*;

/**
 * Spring Boot Plus 代码生成处理器
 *
 * @author geekidea
 * @date 2023-06-01
 */
@Slf4j
@Data
@Accessors(chain = true)
public class GenerateHandler {

    /**
     * 生成代码
     */
    public void generator(GeneratorConfig config) {
        // 文件是否覆盖
        boolean fileOverride = config.isFileOverride();
        if (!fileOverride) {
            throw new RuntimeException("文件覆盖为false，将不会生成文件");
        }
        // 初始化
        config.init();
        // 循环生成
        Set<String> tableNames = config.getTableNames();
        for (String tableName : tableNames) {
            // 生成代码
            handle(config, tableName);
            log.info(tableName + " 代码生成成功");
        }
    }

    public void handle(GeneratorConfig config, String tableName) {
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(config.getUrl(), config.getUsername(), config.getPassword())
                .schema(config.getSchema())
                .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    // 兼容旧版本转换成Integer
                    if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                });
        DbType dbType = dataSourceConfigBuilder.build().getDbType();
        // 当前项目目录
        String projectPath = System.getProperty("user.dir");
        String projectPackagePath = config.getProjectPackagePath();
        String moduleName = config.getModuleName();
        // 如果是仅仅覆盖实体类，则只更新实体类
        boolean onlyOverrideEntity = config.isOnlyOverrideEntity();
        // 代码生成
        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    builder.author(config.getAuthor())
                            .enableSwagger()
                            .disableOpenDir()
                            .dateType(DateType.ONLY_DATE)
                            .outputDir(projectPath + GenerateConstant.SRC_MAIN_JAVA);
                })
                .packageConfig(builder -> {
                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.xml, projectPath + GenerateConstant.SRC_MAIN_RESOURCES_MAPPER + config.getModuleName());
                    builder.parent(config.getParentPackage())
                            .moduleName(config.getModuleName())
                            .controller(config.getControllerPackage())
                            .pathInfo(pathInfo);
                })
                .strategyConfig(builder -> {
                    builder.enableSchema()
                            .addInclude(tableName)
                            .addTablePrefix(config.getTablePrefix())
                            .entityBuilder().enableFileOverride().enableLombok().idType(config.getIdType())
                            .formatFileName(config.getFormatEntityFileName())
                            .controllerBuilder().enableFileOverride().enableRestStyle()
                            .formatFileName(config.getFormatControllerFileName())
                            .serviceBuilder().enableFileOverride().superServiceClass(config.getSuperService()).superServiceImplClass(config.getSuperServiceImpl())
                            .formatServiceFileName(config.getFormatServiceFileName())
                            .formatServiceImplFileName(config.getFormatServiceImplFileName())
                            .mapperBuilder()
                            .enableFileOverride()
                            .formatMapperFileName(config.getFormatMapperFileName())
                            .formatXmlFileName(config.getFormatXmlFileName());

                })
                .templateConfig(builder -> {
                    if (!config.isGeneratorEntity()) {
                        builder.entity(null);
                        builder.disable(TemplateType.ENTITY);
                    }
                    // 是否生成controller
                    if (!config.isGeneratorController()) {
                        builder.controller(null);
                        builder.disable(TemplateType.CONTROLLER);
                    }
                    // 是否生成service
                    if (!config.isGeneratorService()) {
                        builder.service(null);
                        builder.disable(TemplateType.SERVICE);
                    }
                    // 是否生成serviceImpl
                    if (!config.isGeneratorServiceImpl()) {
                        builder.serviceImpl(null);
                        builder.disable(TemplateType.SERVICE_IMPL);
                    }
                    // 是否生成mapper
                    if (!config.isGeneratorMapper()) {
                        builder.mapper(null);
                        builder.disable(TemplateType.MAPPER);
                    }
                    // 是否生成mapper xml
                    if (!config.isGeneratorMapperXml()) {
                        builder.xml(null);
                        builder.disable(TemplateType.XML);
                    }
                })
                .injectionConfig(builder -> {
                    builder.beforeOutputFile(((tableInfo, objectMap) -> {
                        addCustomConfigMap(config, tableInfo, objectMap, dbType);
                    }));
                    String packageModulePath = projectPackagePath + "/" + moduleName + "/";
                    String dtoPackage = packageModulePath + config.getDtoPackage();
                    String queryPackage = packageModulePath + config.getQueryPackage();
                    String voPackage = packageModulePath + config.getVoPackage();
                    String userQueryPackage = packageModulePath + config.getQueryPackage();
                    String userVoPackage = packageModulePath + config.getVoPackage();
                    String userControllerPackage = packageModulePath + config.getControllerPackage();

                    // 添加自定义文件
                    InjectionConfig.Builder injectionConfigBuilder = builder.customMap(Collections.singletonMap("projectName", "spring-boot-plus"));
                    // 如果不是仅仅覆盖实体类且生成类型为FULL，则更新自定义文件，否则，则不更新
                    if (!onlyOverrideEntity && GeneratorType.FULL == config.getGeneratorType()) {
                        // 自定义dto
                        CustomFile dtoCustomFile = new CustomFile.Builder()
                                .fileName(config.getDtoFileName() + GenerateConstant.DOT_JAVA)
                                .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                .templatePath("/templates/dto.java.vm")
                                .packageName(dtoPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义query
                        CustomFile queryCustomFile = new CustomFile.Builder()
                                .fileName(config.getQueryFileName() + GenerateConstant.DOT_JAVA)
                                .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                .templatePath("/templates/query.java.vm")
                                .packageName(queryPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义vo
                        CustomFile voCustomFile = new CustomFile.Builder()
                                .fileName(config.getVoFileName() + GenerateConstant.DOT_JAVA)
                                .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                .templatePath("/templates/vo.java.vm")
                                .packageName(voPackage)
                                .enableFileOverride()
                                .build();

                        injectionConfigBuilder
                                .customFile(dtoCustomFile)
                                .customFile(queryCustomFile)
                                .customFile(voCustomFile);

                        if (config.isGeneratorApp()) {
                            // 自定义APP query
                            CustomFile userQueryCustomFile = new CustomFile.Builder()
                                    .fileName(config.getFormatAppQueryFileName() + GenerateConstant.DOT_JAVA)
                                    .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                    .templatePath("/templates/appQuery.java.vm")
                                    .packageName(userQueryPackage)
                                    .enableFileOverride()
                                    .build();
                            // 自定义APP vo
                            CustomFile userVoCustomFile = new CustomFile.Builder()
                                    .fileName(config.getFormatAppVoFileName() + GenerateConstant.DOT_JAVA)
                                    .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                    .templatePath("/templates/appVo.java.vm")
                                    .packageName(userVoPackage)
                                    .enableFileOverride()
                                    .build();
                            injectionConfigBuilder
                                    .customFile(userQueryCustomFile)
                                    .customFile(userVoCustomFile);
                        }

                    }
                    // 如果不是仅仅覆盖实体类且生成API时，则更新APIcontroller文件，否则，则不更新
                    if (!onlyOverrideEntity && config.isGeneratorApp()) {
                        // 自定义API controller
                        CustomFile userControllerCustomFile = new CustomFile.Builder()
                                .fileName(config.getFormatAppControllerFileName() + GenerateConstant.DOT_JAVA)
                                .filePath(projectPath + GenerateConstant.SRC_MAIN_JAVA)
                                .templatePath("/templates/appController.java.vm")
                                .packageName(userControllerPackage)
                                .enableFileOverride()
                                .build();
                        builder.customFile(userControllerCustomFile);
                    }
                })
                .templateEngine(new EnhanceFileTemplateEngine())
                .execute();
    }

    /**
     * 添加自定义配置map
     *
     * @param config
     * @param tableInfo
     * @param objectMap
     * @param dbType
     */
    public void addCustomConfigMap(GeneratorConfig config, TableInfo tableInfo, Map<String, Object> objectMap, DbType dbType) {
        try {
            Map<String, Object> cfgMap = new HashMap<>();
            String tableName = tableInfo.getName();
            String entityName = tableInfo.getEntityName();
            String appMapping = config.getAppMapping();
            String colonTableName = underlineToColon(tableName);
            String entityObjectName = pascalToCamel(entityName);
            String appName = config.getAppName();
            String uniqueEntityName;
            String uniqueEntityObjectName;
            if (entityName.startsWith(appName)) {
                int length = appName.length();
                uniqueEntityName = entityName.substring(length);
                uniqueEntityObjectName = pascalToCamel(entityObjectName.substring(length));
                ;
            } else {
                uniqueEntityName = entityName;
                uniqueEntityObjectName = entityObjectName;
            }
            String serviceName = tableInfo.getServiceName();
            String mapperName = tableInfo.getMapperName();
            String serviceObjectName = pascalToCamel(serviceName);
            String mapperObjectName = pascalToCamel(mapperName);

            // 默认的分页排序类型
            DefaultOrderType defaultOrderType = config.getDefaultOrderType();
            // 创建时间列名称
            final String createTimeColumn = config.getCreateTimeColumn();
            // 修改时间列名称
            final String updateTimeColumn = config.getUpdateTimeColumn();
            // 是否生成权限注解
            final boolean permission = config.isPermission();
            // 是否生成简单模式
            final boolean simple = config.isSimple();

            List<TableField> tableFields = tableInfo.getFields();
            String pkIdColumnName = null;
            boolean existsCreateTimeColumn = false;
            boolean existsUpdateTimeColumn = false;
            String updateTimeColumnName = null;
            boolean existsDateType = false;
            boolean existsBigDecimalType = false;
            boolean existsTimeType = false;
            for (TableField tableField : tableFields) {
                String columnName = tableField.getColumnName();
                String propertyName = tableField.getPropertyName();
                boolean isKeyFlag = tableField.isKeyFlag();
                if (isKeyFlag) {
                    pkIdColumnName = handlePkId(cfgMap, columnName, propertyName);
                } else {
                    if (StringUtils.isNotBlank(createTimeColumn) && createTimeColumn.equalsIgnoreCase(columnName)) {
                        existsCreateTimeColumn = true;
                    } else if (StringUtils.isNotBlank(updateTimeColumn) && updateTimeColumn.equalsIgnoreCase(columnName)) {
                        existsUpdateTimeColumn = true;
                        updateTimeColumnName = columnName;
                    }
                }
                String propertyType = tableField.getPropertyType();
                if (propertyType.equals("Date")) {
                    existsDateType = true;
                } else if (propertyType.equals("BigDecimal")) {
                    existsBigDecimalType = true;
                } else if (propertyType.equals("Time")) {
                    existsTimeType = true;
                }
            }

            if (StringUtils.isBlank(pkIdColumnName)) {
                pkIdColumnName = handleDefaultPkId(cfgMap);
            }

            String defaultOrderColumnName = null;
            if (DefaultOrderType.PK_ID == defaultOrderType) {
                defaultOrderColumnName = pkIdColumnName;
            } else if (DefaultOrderType.CREATE_TIME == defaultOrderType) {
                if (existsCreateTimeColumn) {
                    defaultOrderColumnName = createTimeColumn;
                }
            }
            cfgMap.put("defaultOrderType", defaultOrderType);
            cfgMap.put("defaultOrderColumnName", defaultOrderColumnName);

            // 修改列名称
            if (existsUpdateTimeColumn) {
                String pascalUpdateTimeColumnName = underlineToPascal(updateTimeColumnName);
                cfgMap.put("pascalUpdateTimeColumnName", pascalUpdateTimeColumnName);
            }

            // 是否存在日期类型
            cfgMap.put("existsDateType", existsDateType);
            // 是否存在BigDecimal
            cfgMap.put("existsBigDecimalType", existsBigDecimalType);
            // 是否存在Time类型
            cfgMap.put("existsTimeType", existsTimeType);

            // 文件名称格式化
            String dtoFileName = entityName + config.getDtoFileName();
            String queryFileName = entityName + config.getQueryFileName();
            String voFileName = entityName + config.getVoFileName();

            String dtoObjectName = pascalToCamel(dtoFileName);
            String queryObjectName = pascalToCamel(queryFileName);
            String voObjectName = pascalToCamel(voFileName);


            // 包路径
            // dto
            String dtoPackagePath = config.getDtoPackagePath();
            cfgMap.put("dtoPackagePath", dtoPackagePath);
            cfgMap.put("dtoPath", dtoPackagePath + "." + dtoFileName);
            // vo
            String voPackagePath = config.getVoPackagePath();
            cfgMap.put("voPackagePath", voPackagePath);
            cfgMap.put("voPath", voPackagePath + "." + voFileName);
            // query
            String queryPackagePath = config.getQueryPackagePath();
            cfgMap.put("queryPackagePath", queryPackagePath);
            cfgMap.put("queryPath", queryPackagePath + "." + queryFileName);
            // 导入排序查询参数类
            cfgMap.put("superQueryPath", config.getSuperQuery());
            cfgMap.put("commonFields", config.getCommonFields());
            cfgMap.put("voExcludeFields", config.getVoExcludeFields());
            // 冒号连接的表名称
            cfgMap.put("colonTableName", colonTableName);
            // 是否生成permission注解
            cfgMap.put("permission", permission);
            cfgMap.put("permissionPath", Permission.class.getName());
            cfgMap.put("simple", simple);

            // 导入分页类
            cfgMap.put("paging", config.getCommonPaging());
            cfgMap.put("pagingUtilPath", PagingUtil.class.getName());
            // ApiResult
            cfgMap.put("apiResult", config.getCommonApiResult());
            // 分页列表查询是否排序
            cfgMap.put("pageListOrder", config.isPageListOrder());
            cfgMap.put("businessException", config.getCommonBusinessException());
            cfgMap.put("orderByItem", config.getCommonOrderByItem());

            // 代码Validation校验
            cfgMap.put("paramValidation", config.isParamValidation());

            // 对象名称
            cfgMap.put("entityObjectName", entityObjectName);
            // service对象名称
            cfgMap.put("serviceObjectName", serviceObjectName);
            // mapper对象名称
            cfgMap.put("mapperObjectName", mapperObjectName);
            // 自定义文件和对象名称
            cfgMap.put("dtoName", dtoFileName);
            cfgMap.put("queryName", queryFileName);
            cfgMap.put("voName", voFileName);
            cfgMap.put("dtoObjectName", dtoObjectName);
            cfgMap.put("queryObjectName", queryObjectName);
            cfgMap.put("voObjectName", voObjectName);

            // 请求路径
            RequestMappingType requestMappingType = config.getRequestMappingType();
            // 格式化请求路径
            String controllerRequestMapping = String.format(config.getControllerRequestMapping(), entityObjectName);
            String addRequestMapping = String.format(config.getAddRequestMapping(), entityName);
            String updateRequestMapping = String.format(config.getUpdateRequestMapping(), entityName);
            String deleteRequestMapping = String.format(config.getDeleteRequestMapping(), entityName);
            String infoRequestMapping = String.format(config.getInfoRequestMapping(), entityName);
            String pageRequestMapping = String.format(config.getPageRequestMapping(), entityName);

            String apiInfoRequestMapping = String.format(config.getInfoRequestMapping(), appName + entityName);
            String apiPageRequestMapping = String.format(config.getPageRequestMapping(), appName + entityName);

            // 判断是否需要加上模块名称
            String moduleName = config.getModuleName();
            boolean requestMappingModule = config.isRequestMappingModule();
            if (requestMappingModule) {
                controllerRequestMapping = moduleName + "/" + controllerRequestMapping;
            }
            if (RequestMappingType.CAMEL == requestMappingType) {
            } else if (RequestMappingType.HYPHEN == requestMappingType) {
                controllerRequestMapping = toHyphen(controllerRequestMapping);
                addRequestMapping = toHyphen(addRequestMapping);
                updateRequestMapping = toHyphen(updateRequestMapping);
                deleteRequestMapping = toHyphen(deleteRequestMapping);
                infoRequestMapping = toHyphen(infoRequestMapping);
                pageRequestMapping = toHyphen(pageRequestMapping);
                apiInfoRequestMapping = toHyphen(apiInfoRequestMapping);
                apiPageRequestMapping = toHyphen(apiPageRequestMapping);
            } else if (RequestMappingType.UNDERLINE == requestMappingType) {
                controllerRequestMapping = toUnderline(controllerRequestMapping);
                addRequestMapping = toUnderline(addRequestMapping);
                updateRequestMapping = toUnderline(updateRequestMapping);
                deleteRequestMapping = toUnderline(deleteRequestMapping);
                infoRequestMapping = toUnderline(infoRequestMapping);
                pageRequestMapping = toUnderline(pageRequestMapping);
                apiInfoRequestMapping = toUnderline(apiInfoRequestMapping);
                apiPageRequestMapping = toUnderline(apiPageRequestMapping);
            } else if (RequestMappingType.BACKSLASH == requestMappingType) {
                controllerRequestMapping = toBackslash(controllerRequestMapping);
                addRequestMapping = toBackslash(addRequestMapping);
                updateRequestMapping = toBackslash(updateRequestMapping);
                deleteRequestMapping = toBackslash(deleteRequestMapping);
                infoRequestMapping = toBackslash(infoRequestMapping);
                pageRequestMapping = toBackslash(pageRequestMapping);
                apiInfoRequestMapping = toBackslash(apiInfoRequestMapping);
                apiPageRequestMapping = toBackslash(apiPageRequestMapping);
            } else if (RequestMappingType.LOWER == requestMappingType) {
                controllerRequestMapping = toLower(controllerRequestMapping);
                addRequestMapping = toLower(addRequestMapping);
                updateRequestMapping = toLower(updateRequestMapping);
                deleteRequestMapping = toLower(deleteRequestMapping);
                infoRequestMapping = toLower(infoRequestMapping);
                pageRequestMapping = toLower(pageRequestMapping);
                apiInfoRequestMapping = toLower(apiInfoRequestMapping);
                apiPageRequestMapping = toLower(apiPageRequestMapping);
            }

            cfgMap.put("controllerRequestMapping", controllerRequestMapping);
            cfgMap.put("addRequestMapping", addRequestMapping);
            cfgMap.put("updateRequestMapping", updateRequestMapping);
            cfgMap.put("deleteRequestMapping", deleteRequestMapping);
            cfgMap.put("infoRequestMapping", infoRequestMapping);
            cfgMap.put("pageRequestMapping", pageRequestMapping);
            cfgMap.put("apiInfoRequestMapping", apiInfoRequestMapping);
            cfgMap.put("apiPageRequestMapping", apiPageRequestMapping);

            // 自定义APP文件和对象名称
            String appEntityName = appName + entityName;
            String appEntityObjectName = pascalToCamel(appName) + entityName;
            cfgMap.put("appName", appName);
            cfgMap.put("appMapping", config.getAppMapping());
            cfgMap.put("adminMapping", config.getAdminMapping());
            cfgMap.put("appEntityName", appEntityName);
            cfgMap.put("appEntityObjectName", appEntityObjectName);
            String appTableComment = appName + tableInfo.getComment();
            cfgMap.put("appTableComment", appTableComment);
            String appQueryFileName = String.format(config.getFormatAppQueryFileName(), entityName);
            String appVoFileName = String.format(config.getFormatAppVoFileName(), entityName);
            String appQueryObjectName = pascalToCamel(appQueryFileName);
            String appVoObjectName = pascalToCamel(appVoFileName);
            cfgMap.put("appQueryName", appQueryFileName);
            cfgMap.put("appVoName", appVoFileName);
            cfgMap.put("appQueryObjectName", appQueryObjectName);
            cfgMap.put("appVoObjectName", appVoObjectName);
            cfgMap.put("appQueryPath", queryPackagePath + "." + appQueryFileName);
            cfgMap.put("appVoPath", voPackagePath + "." + appVoFileName);

            cfgMap.put("uniqueEntityName", uniqueEntityName);
            cfgMap.put("uniqueEntityObjectName", uniqueEntityObjectName);

            cfgMap.put("logPackagePath", config.getLogPackagePath());
            cfgMap.put("sysLog", config.isSysLog());
            cfgMap.put("sysLogTypePackagePath", config.getSysLogTypePackagePath());

            cfgMap.put("generatorApi", config.isGeneratorApp());
            cfgMap.put("generatorType", config.getGeneratorType());

            String appControllerName = String.format(config.getFormatAppControllerFileName(), entityName);
            cfgMap.put("appControllerName", appControllerName);

            objectMap.put("cfg", cfgMap);
        } catch (
                Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private String handleDefaultPkId(Map<String, Object> cfgMap) {
        return handlePkId(cfgMap, "id", "id");
    }

    private String handlePkId(Map<String, Object> cfgMap, String columnName, String propertyName) {
        String pkIdColumnName;
        pkIdColumnName = columnName;
        cfgMap.put("pkIdColumnName", pkIdColumnName);
        String pkIdName = propertyName;
        if (StringUtils.isNotBlank(pkIdName)) {
            String pascalPkIdName = camelToPascal(pkIdName);
            cfgMap.put("pascalPkIdName", pascalPkIdName);
            cfgMap.put("pkIdName", pkIdName);
        }
        return pkIdColumnName;
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

    public static void main(String[] args) throws Exception {
        System.out.println(toHyphen("sys_user"));
        System.out.println(toHyphen("sysUser"));
        System.out.println(toHyphen("SysUser"));
        GeneratorConfig config = new GeneratorConfig();
        System.out.println(String.format(config.getAddRequestMapping(), "Hello"));
    }

}
