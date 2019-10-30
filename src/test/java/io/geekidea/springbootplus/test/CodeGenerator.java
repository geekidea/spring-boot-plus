/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import io.geekidea.springbootplus.generator.config.SpringBootPlusMySqlQuery;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring-boot-plus代码生成器
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 驱动URL
     */
    private String driverUrl;

    /**
     * 生成的类路径
     */
    private String projectPackagePath;

    /**
     * 项目主包路径
     */
    private String parentPackage;

    /**
     * 包名称
     */
    private String packageController = "controller";

    // ############################ 自定义配置部分 start ############################
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成的表名称
     */
    private String tableName;
    /**
     * 主键数据库列名称
     */
    private String pkIdColumnName = "id";
    /**
     * 代码生成策略 true：All/false:SIMPLE
     * 0. SIMPLE 生成最基本的代码
     * 1. NORMAL 生成普通代码
     * 2. ALL 生成所有的代码
     */
    private GeneratorStrategy generatorStrategy = GeneratorStrategy.ALL;

    /**
     * 生成策略
     */
    public enum GeneratorStrategy {
        SIMPLE, NORMAL, ALL
    }

    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder = false;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation = true;

    /**
     * 是否生成实体类
     */
    private boolean generatorEntity;
    /**
     * 是否生成控制器
     */
    private boolean generatorController;
    /**
     * 是否生成service接口
     */
    private boolean generatorService;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml;
    /**
     * 是否生成查询参数
     */
    private boolean generatorQueryParam;
    /**
     * 是否生成查询VO
     */
    private boolean generatorQueryVo;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions;
    // ############################ 自定义配置部分 end ############################

    /**
     * 公共父包
     */
    private String commonParentPackage;

    /**
     * 实体父类
     */
    private String superEntity;
    /**
     * 控制器父类
     */
    private String superController;
    /**
     * service父接口
     */
    private String superService;
    /**
     * service实现父类
     */
    private String superServiceImpl;
    /**
     * 查询参数父类
     */
    private String superQueryParam;
    /**
     * 实体父类实体列表
     */
    private String[] superEntityCommonColumns;

    // 公共类包路径
    /**
     * 公共id参数路径
     */
    private String commonIdParam;
    /**
     * 公共结果集路径
     */
    private String commonApiResult;
    /**
     * 公共排序枚举
     */
    private String commonOrderEnum;
    /**
     * 公共排序查询参数
     */
    private String commonOrderQueryParam;
    /**
     * 公共分页对象
     */
    private String commonPaging;

    /**
     * 是否文件覆盖
     */
    private boolean fileOverride;

    /**
     * 初始化变量
     */
    public void init() {
        this.commonParentPackage = this.parentPackage + ".common";
        // 父类包路径
        this.superEntity = this.commonParentPackage + ".entity.BaseEntity";
        this.superController = this.commonParentPackage + ".controller.BaseController";
        this.superService = this.commonParentPackage + ".service.BaseService";
        this.superServiceImpl = this.commonParentPackage + ".service.impl.BaseServiceImpl";
        this.superQueryParam = this.commonParentPackage + ".param.QueryParam";
        this.superEntityCommonColumns = new String[]{};

        // 公共类包路径
        this.commonIdParam = this.commonParentPackage + ".param.IdParam";
        this.commonApiResult = this.commonParentPackage + ".api.ApiResult";
        this.commonOrderEnum = this.commonParentPackage + ".enums.OrderEnum";
        this.commonOrderQueryParam = this.commonParentPackage + ".param.OrderQueryParam";
        this.commonPaging = this.commonParentPackage + ".vo.Paging";
    }

    /**
     * 生成代码
     */
    public void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setSwagger2(true);               // 启用swagger注解
        gc.setIdType(IdType.ID_WORKER);     // 主键类型:ID_WORKER
        gc.setServiceName("%sService");     // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setFileOverride(fileOverride);   // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(driverUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        // 设置自定义查询
        dsc.setDbQuery(new SpringBootPlusMySqlQuery());

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parentPackage);
        pc.setController(packageController);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

                String camelTableName = underlineToCamel(tableName);
                String pascalTableName = underlineToPascal(tableName);
                String colonTableName = underlineToColon(tableName);

                Map<String, Object> map = new HashMap<>();
                map.put("customField", "Hello " + this.getConfig().getGlobalConfig().getAuthor());
                // 查询参数包路径
                String queryParamPackage = parentPackage + StringPool.DOT + pc.getModuleName() + ".param";
                map.put("queryParamPackage", queryParamPackage);
                // 查询参数类路径
                map.put("queryParamPath", queryParamPackage + StringPool.DOT + pascalTableName + "QueryParam");
                // 查询参数共公包路径
                map.put("queryParamCommonPath", superQueryParam);
                // 查询参数共公包路径
                map.put("idParamPath", commonIdParam);
                // 响应结果包路径
                String queryVoPackage = parentPackage + StringPool.DOT + pc.getModuleName() + ".vo";
                map.put("queryVoPackage", queryVoPackage);
                // 响应结果类路径
                map.put("queryVoPath", queryVoPackage + StringPool.DOT + pascalTableName + "QueryVo");
                // 实体对象名称
                map.put("entityObjectName", camelTableName);
                // service对象名称
                map.put("serviceObjectName", camelTableName + "Service");
                // mapper对象名称
                map.put("mapperObjectName", camelTableName + "Mapper");
                // 主键ID列名
                map.put("pkIdColumnName", pkIdColumnName);
                // 主键ID驼峰名称
                map.put("pkIdCamelName", underlineToCamel(pkIdColumnName));
                // 导入分页类
                map.put("paging", commonPaging);
                // 导入排序枚举
                map.put("orderEnum", commonOrderEnum);
                // ApiResult
                map.put("apiResult", commonApiResult);
                // 分页列表查询是否排序
                map.put("pageListOrder", pageListOrder);
                // 导入排序查询参数类
                map.put("orderQueryParamPath", commonOrderQueryParam);
                // 代码生成策略
                map.put("generatorStrategy", generatorStrategy);
                // 代码Validation校验
                map.put("paramValidation", paramValidation);
                // 冒号连接的表名称
                map.put("colonTableName", colonTableName);
                // 是否生成Shiro RequiresPermissions注解
                map.put("requiresPermissions", requiresPermissions);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();

        // 生成mapper xml
        if (generatorMapperXml) {
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }

        // 自定义queryParam模板
        if (generatorQueryParam) {
            focList.add(new FileOutConfig("/templates/queryParam.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/java/" + projectPackagePath + "/" + pc.getModuleName() + "/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义queryVo模板
        if (generatorQueryVo) {
            focList.add(new FileOutConfig("/templates/queryVo.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/java/" + projectPackagePath + "/" + pc.getModuleName() + "/vo/" + tableInfo.getEntityName() + "QueryVo" + StringPool.DOT_JAVA;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();
        // xml使用自定义输出
        templateConfig.setXml(null);
        // 是否生成entity
        if (!generatorEntity) {
            templateConfig.setEntity(null);
        }
        // 是否生成controller
        if (!generatorController) {
            templateConfig.setController(null);
        }
        // 是否生成service
        if (!generatorService) {
            templateConfig.setService(null);
        }
        // 是否生成serviceImpl
        if (!generatorServiceImpl) {
            templateConfig.setServiceImpl(null);
        }
        // 是否生成mapper
        if (!generatorMapper) {
            templateConfig.setMapper(null);
        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(superEntity);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(superController);
        strategy.setSuperServiceClass(superService);
        strategy.setSuperServiceImplClass(superServiceImpl);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns(superEntityCommonColumns);
        strategy.setControllerMappingHyphenStyle(true);
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
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

    public static void main(String[] args) {
        System.out.println(underlineToColon("sys_user"));
    }


}
