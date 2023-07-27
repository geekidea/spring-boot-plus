package io.geekidea.boot.generator.handler;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.generator.config.GeneratorConfig;
import io.geekidea.boot.generator.enums.DefaultOrderType;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
                .schema(config.getSchema());
        DbType dbType = dataSourceConfigBuilder.build().getDbType();
        // 当前项目目录
        String projectPath = System.getProperty("user.dir");
        // 如果是仅仅覆盖实体类，则只更新实体类
        boolean onlyOverrideEntity = config.isOnlyOverrideEntity();
        // 代码生成
        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    builder.author(config.getAuthor())
                            .enableSwagger()
                            .disableOpenDir()
                            .dateType(DateType.ONLY_DATE)
                            .outputDir(projectPath + "/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent(config.getParentPackage())
                            .moduleName(config.getModuleName())
                            .controller(config.getControllerPackage())
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper" + "/" + config.getModuleName()));
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
                    }
                    // 是否生成controller
                    if (!config.isGeneratorController()) {
                        builder.controller(null);
                    }
                    // 是否生成service
                    if (!config.isGeneratorService()) {
                        builder.service(null);
                    }
                    // 是否生成serviceImpl
                    if (!config.isGeneratorServiceImpl()) {
                        builder.serviceImpl(null);
                    }
                    // 是否生成mapper
                    if (!config.isGeneratorMapper()) {
                        builder.mapper(null);
                    }
                    if (!config.isGeneratorMapperXml()) {
                        builder.xml(null);
                    }
                })
                .injectionConfig(builder -> {
                    builder.beforeOutputFile(((tableInfo, objectMap) -> {
                        addCustomConfigMap(config, tableInfo, objectMap, dbType);
                    }));
                    String projectPackagePath = config.getProjectPackagePath();
                    String moduleName = config.getModuleName();
                    String dtoPackage = projectPackagePath + "/" + moduleName + "/" + config.getDtoPackage();
                    String queryPackage = projectPackagePath + "/" + moduleName + "/" + config.getQueryPackage();
                    String voPackage = projectPackagePath + "/" + moduleName + "/" + config.getVoPackage();
                    // 如果是仅仅覆盖实体类，则不更新dto、query、vo
                    if (!onlyOverrideEntity) {
                        // 自定义addDto
                        CustomFile addDtoCustomFile = new CustomFile.Builder()
                                .fileName(config.getAddDtoFileName() + ".java")
                                .filePath(projectPath + "/src/main/java/")
                                .templatePath("/templates/addDto.java.vm")
                                .packageName(dtoPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义updateDto
                        CustomFile updateDtoCustomFile = new CustomFile.Builder()
                                .fileName(config.getUpdateDtoFileName() + ".java")
                                .filePath(projectPath + "/src/main/java/")
                                .templatePath("/templates/updateDto.java.vm")
                                .packageName(dtoPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义query
                        CustomFile queryCustomFile = new CustomFile.Builder()
                                .fileName(config.getQueryFileName() + ".java")
                                .filePath(projectPath + "/src/main/java/")
                                .templatePath("/templates/query.java.vm")
                                .packageName(queryPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义vo
                        CustomFile voCustomFile = new CustomFile.Builder()
                                .fileName(config.getVoFileName() + ".java")
                                .filePath(projectPath + "/src/main/java/")
                                .templatePath("/templates/vo.java.vm")
                                .packageName(voPackage)
                                .enableFileOverride()
                                .build();
                        // 自定义infoVo
                        CustomFile infoVoCustomFile = new CustomFile.Builder()
                                .fileName(config.getInfoVoFileName() + ".java")
                                .filePath(projectPath + "/src/main/java/")
                                .templatePath("/templates/infoVo.java.vm")
                                .packageName(voPackage)
                                .enableFileOverride()
                                .build();
                        // 添加自定义文件
                        builder.customMap(Collections.singletonMap("projectName", "spring-boot-plus"))
                                .customFile(addDtoCustomFile)
                                .customFile(updateDtoCustomFile)
                                .customFile(queryCustomFile)
                                .customFile(voCustomFile)
                                .customFile(infoVoCustomFile);
                    }
                }).execute();
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
            String colonTableName = underlineToColon(tableName);
            String entityName = tableInfo.getEntityName();
            String entityObjectName = pascalToCamel(entityName);

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
            for (TableField tableField : tableFields) {
                String columnName = tableField.getColumnName();
                String propertyName = tableField.getPropertyName();
                boolean isKeyFlag = tableField.isKeyFlag();
                if (isKeyFlag) {
                    pkIdColumnName = columnName;
                    cfgMap.put("pkIdColumnName", pkIdColumnName);
                    String pkIdName = propertyName;
                    if (StringUtils.isNotBlank(pkIdName)) {
                        String pascalPkIdName = camelToPascal(pkIdName);
                        cfgMap.put("pascalPkIdName", pascalPkIdName);
                        cfgMap.put("pkIdName", pkIdName);
                    }
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
                }
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

            // 文件名称格式化
            String addDtoFileName = entityName + config.getAddDtoFileName();
            String updateDtoFileName = entityName + config.getUpdateDtoFileName();
            String queryFileName = entityName + config.getQueryFileName();
            String infoVoFileName = entityName + config.getInfoVoFileName();
            String voFileName = entityName + config.getVoFileName();

            String addDtoObjectName = pascalToCamel(addDtoFileName);
            String updateDtoObjectName = pascalToCamel(updateDtoFileName);
            String queryObjectName = pascalToCamel(queryFileName);
            String infoVoObjectName = pascalToCamel(infoVoFileName);
            String voObjectName = pascalToCamel(voFileName);


            // 包路径
            // dto
            String dtoPackagePath = config.getDtoPackagePath();
            cfgMap.put("dtoPackagePath", dtoPackagePath);
            cfgMap.put("addDtoPath", dtoPackagePath + "." + addDtoFileName);
            cfgMap.put("updateDtoPath", dtoPackagePath + "." + updateDtoFileName);
            // vo
            String voPackagePath = config.getVoPackagePath();
            cfgMap.put("voPackagePath", voPackagePath);
            cfgMap.put("voPath", voPackagePath + "." + voFileName);
            cfgMap.put("infoVoPath", voPackagePath + "." + infoVoFileName);
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
            cfgMap.put("addDtoName", addDtoFileName);
            cfgMap.put("updateDtoName", updateDtoFileName);
            cfgMap.put("queryName", queryFileName);
            cfgMap.put("infoVoName", infoVoFileName);
            cfgMap.put("voName", voFileName);
            cfgMap.put("addDtoObjectName", addDtoObjectName);
            cfgMap.put("updateDtoObjectName", updateDtoObjectName);
            cfgMap.put("queryObjectName", queryObjectName);
            cfgMap.put("infoVoObjectName", infoVoObjectName);
            cfgMap.put("voObjectName", voObjectName);


            objectMap.put("cfg", cfgMap);
        } catch (
                Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
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

}
