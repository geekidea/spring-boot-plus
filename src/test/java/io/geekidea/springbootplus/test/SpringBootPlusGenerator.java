package io.geekidea.springbootplus.test;

/**
 * spring-boot-plus代码生成器入口类
 *
 * @author geekidea
 * @date 2019-10-22
 **/
public class SpringBootPlusGenerator {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        // 公共配置
        // 数据库配置
        codeGenerator
                .setUserName("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver")
                .setDriverUrl("jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false");

        // 包信息
        codeGenerator
                .setProjectPackagePath("io/geekidea/springbootplus")
                .setParentPackage("io.geekidea.springbootplus");

        // 组件作者等配置
        codeGenerator
                .setModuleName("system")
                .setAuthor("geekidea")
                .setPkIdColumnName("id");

        // 生成策略
        codeGenerator
                .setGeneratorStrategy(CodeGenerator.GeneratorStrategy.ALL)
                .setPageListOrder(true)
                .setParamValidation(true);

        // 生成文件
        codeGenerator
                .setGeneratorEntity(true)
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true)
                .setGeneratorQueryParam(true)
                .setGeneratorQueryVo(true);

        // 是否覆盖已有文件
        codeGenerator.setFileOverride(true);

        // 初始化公共变量
        codeGenerator.init();

        // 需要生成的表数组
        String[] tables = {
                "sys_user",
                "sys_role",
                "sys_permission",
                "sys_role_permission",
                "sys_department"
        };

        // 循环生成
        for (String table : tables) {
            // 设置需要生成的表名称
            codeGenerator.setTableName(table);
            // 生成代码
            codeGenerator.generator();
        }

    }

}
