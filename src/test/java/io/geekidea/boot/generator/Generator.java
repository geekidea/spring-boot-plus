package io.geekidea.boot.generator;

import io.geekidea.boot.generator.config.GeneratorConfig;
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
        config.setParentPackage("io.geekidea.boot" )
                .setModuleName("temp" )
                .setAuthor("geekidea" );
        // 表名称和需要去掉的表前缀
        config.setTableNames("foo_bar" )
                .setTablePrefix("");
        // 是否覆盖已有文件
        config.setFileOverride(true);
        // 是否只更新实体类
        config.setOnlyOverrideEntity(false);
        // 自定义包名称
        config.setEntityPackage("model");
        config.setMapperPackage("dao");
        config.setDtoPackage("dto");
        config.setQueryPackage("req");
        config.setVoPackage("resp");
        config.setAddDtoFileName("AddDTO");
        config.setUpdateDtoFileName("UpdateDTO");
        config.setQueryFileName("Request");
        config.setInfoVoFileName("DetailVo");
        config.setVoFileName("Response");

        GenerateHandler handler = new GenerateHandler();
        handler.generator(config);
    }
}
