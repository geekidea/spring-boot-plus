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

package io.geekidea.springbootplus.generator.properties;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import io.geekidea.springbootplus.generator.config.GeneratorConfig;
import io.geekidea.springbootplus.generator.config.MybatisPlusGeneratorConfig;
import io.geekidea.springbootplus.generator.config.ProjectConfig;
import io.geekidea.springbootplus.generator.config.TableConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-boot-plus代码生成配置属性
 *
 * @author geekidea
 * @date 2020/3/11
 **/
@Data
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.generator")
public class GeneratorProperties {

    /**
     * Maven模块名称
     */
    private String mavenModuleName;

    /**
     * 业务模块名称
     */
    private String moduleName;

    /**
     * 生成的父包全路径名称
     */
    private String parentPackage;

    /**
     * 生成的类文件路径
     */
    private String parentPackagePath;

    /**
     * 开发人员名称
     */
    private String author;

    /**
     * 生成文件的输出目录,root: 表示当前项目根目录
     */
    private String outputDir;

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;

    /**
     * 表前缀
     */
    private List<String> tablePrefix;

    /**
     * 表信息配置
     */
    @NestedConfigurationProperty
    private List<TableConfig> tableConfig = new ArrayList<>();

    /**
     * 数据库连接信息配置
     */
    @NestedConfigurationProperty
    public DataSourceConfig dataSourceConfig = new DataSourceConfig();

    /**
     * 代码生成配置
     */
    @NestedConfigurationProperty
    private GeneratorConfig generatorConfig = new GeneratorConfig();

    /**
     * mybatisplus相关配置
     */
    @NestedConfigurationProperty
    private MybatisPlusGeneratorConfig mybatisPlusGeneratorConfig = new MybatisPlusGeneratorConfig();

    /**
     * 项目信息配置
     */
    @NestedConfigurationProperty
    private ProjectConfig projectConfig = new ProjectConfig();


    public GeneratorProperties addTable(String tableName) {
        this.addTable(tableName, null);
        return this;
    }

    public GeneratorProperties addTable(String tableName, String pkIdName) {
        this.getTableConfig().add(new TableConfig().setTableName(tableName).setPkIdName(pkIdName));
        return this;
    }

    public GeneratorProperties setTables(String... tableNames) {
        if (tableNames == null) {
            return this;
        }
        for (String tableName : tableNames) {
            this.addTable(tableName);
        }
        return this;
    }
}
