package io.geekidea.boot.generator;

import io.geekidea.boot.generator.config.GeneratorConfig;
import io.geekidea.boot.generator.enums.GeneratorType;
import io.geekidea.boot.generator.handler.GenerateHandler;

/**
 * Spring Boot Plus 代码生成器
 *
 * @author geekidea
 * @date 2022/3/16
 **/
public class Generator {

    public static void main(String[] args) throws Exception {
        GeneratorConfig config = new GeneratorConfig();
        // 项目信息配置
        config.setParentPackage("io.geekidea.boot")
                .setModuleName("demo")
                .setAuthor("geekidea");
        // 表名称和需要去掉的表前缀
        config.setTableNames("demo")
                .setTablePrefix("");
        // 设置生成的类型
        config.setGeneratorType(GeneratorType.FULL);
        // 是否生成APP端代码
        config.setGeneratorApp(true);
        // 是否只更新实体类
        config.setOnlyOverrideEntity(false);
        // 代码生成
        GenerateHandler handler = new GenerateHandler();
        handler.generator(config);
    }
}
