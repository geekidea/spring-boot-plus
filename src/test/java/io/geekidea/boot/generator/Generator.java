package io.geekidea.boot.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import io.geekidea.boot.common.constant.SystemConstant;
import io.geekidea.boot.config.properties.GeneratorProperties;
import io.geekidea.boot.generator.enums.GeneratorFormLayout;
import io.geekidea.boot.generator.enums.RequestMappingStyle;
import io.geekidea.boot.generator.handler.GeneratorHandler;

import java.util.Arrays;
import java.util.List;

/**
 * 本地代码生成，与可视化生成共享代码模板
 * 与可视化代码生成的相同点
 * 1. 使用相同的代码生成模板
 * 2. 使用相同的代码生成器
 * 3. 生成的效果一样
 * 与可视化代码生成的不同点
 * 1. 直接读取数据库表和列的最新信息
 * 2. 与可视化生成的generator_table、generator_column表没有关系
 * 3. 可视化代码使用的mybatis访问数据库表和列信息
 * 4. 本地代码生成使用的jdbc直接读取数据库表和列信息
 *
 * @author geekidea
 * @date 2024/1/15
 **/
public class Generator {

    public static void main(String[] args) throws Exception {
        GeneratorProperties config = new GeneratorProperties();
        // 包名称
        config.setPackageName("io.geekidea.boot");
        // 模块名称
        config.setModuleName("test");
        // 作者
        config.setAuthor("geekidea");
        // 生成ID类型枚举类型
        config.setIdType(IdType.ASSIGN_ID);
        // 生成的表名称
        List<String> tableNames = Arrays.asList("");
        // 排除的表前缀
        config.setTablePrefixes(Arrays.asList("tb_", "b_", "c_", "test_"));
        // 是否生成后台代码
        config.setGeneratorBackend(true);
        // 是否生成App后端代码
        config.setGeneratorAppBackend(true);
        // 是否生成前端代码
        config.setGeneratorFrontend(true);
        // 是否校验字段
        config.setValidateField(true);
        // 是否启用日志注解
        config.setEnableLog(true);
        // 是否生成权限编码
        config.setEnablePermission(true);
        // 是否显示默认查询条件
        config.setShowDefaultQuery(true);
        config.setKeywordFields(Arrays.asList("name"));
        // 请求映射风格 DEFAULT、RESTFUL、SMALL_LETTER、HYPHEN、UNDERLINE
        config.setRequestMappingStyle(RequestMappingStyle.DEFAULT);
        // 设置默认降序排列名称，如id、create_time，如果为空，则不指定默认排序
        config.setDefaultOrderColumnName("id");
        // 设置父菜单ID
        config.setParentMenuId(SystemConstant.ROOT_MENU_ID);
        // 设置前端表单布局方式 1：一行一列，2：一行两列
        config.setFormLayout(GeneratorFormLayout.TWO);
        // 前端自定义代码生成绝对路径，默认为当前项目下的vue目录，可配置如：/Users/mrliu/geekidea/spring-boot-plus-vue3
        config.setCustomFrontendPath("/");
        // 设置是否只生成或覆盖实体类
        config.setOnlyGeneratorEntity(false);
        // 生成代码
        GeneratorHandler.generatorCode(config, tableNames);
    }

}
