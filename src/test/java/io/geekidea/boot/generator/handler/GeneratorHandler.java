package io.geekidea.boot.generator.handler;

import com.baomidou.mybatisplus.annotation.IdType;
import io.geekidea.boot.config.properties.GeneratorProperties;
import io.geekidea.boot.generator.constant.GeneratorConstant;
import io.geekidea.boot.generator.entity.GeneratorColumn;
import io.geekidea.boot.generator.entity.GeneratorTable;
import io.geekidea.boot.generator.enums.GeneratorFormLayout;
import io.geekidea.boot.generator.enums.GeneratorType;
import io.geekidea.boot.generator.enums.RequestMappingStyle;
import io.geekidea.boot.generator.exception.GeneratorException;
import io.geekidea.boot.generator.jdbc.JdbcUtil;
import io.geekidea.boot.generator.util.GeneratorUtil;
import io.geekidea.boot.generator.vo.GeneratorColumnDbVo;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @author geekidea
 * @date 2024/1/15
 **/
@Slf4j
public class GeneratorHandler {

    /**
     * 生成代码
     *
     * @param config
     * @param tableNames
     * @throws Exception
     */
    public static void generatorCode(GeneratorProperties config, List<String> tableNames) throws Exception {
        if (config == null) {
            throw new GeneratorException("配置不能为空");
        }
        if (CollectionUtils.isEmpty(tableNames)) {
            throw new GeneratorException("表名称不能为空");
        }
        int blankLength = 0;
        for (String tableName : tableNames) {
            if (StringUtils.isBlank(tableName)) {
                blankLength++;
            }
        }
        if (blankLength == tableNames.size()) {
            throw new GeneratorException("表名称不能为空");
        }
        // 检查配置
        checkConfig(config);
        // 检查所有的表是否已存在实体类
        boolean isFileOverride = isFileOverride(config, tableNames);
        if (isFileOverride) {
            // 循环生成代码
            try {
                for (String tableName : tableNames) {
                    if (StringUtils.isBlank(tableName)) {
                        continue;
                    }
                    GeneratorTable generatorTable = getGeneratorTable(config, tableName);
                    GeneratorUtil.generatorCode(tableName, generatorTable);
                }
                log.info("代码生成成功");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("代码生成失败");
            }
        }
    }

    /**
     * 判断代码是否存在，如果存在，则提示输入y(yes)进行覆盖，输入其它则跳过，不会再生成代码
     * 如果不存在，则继续生成代码
     *
     * @param config
     * @param tableNames
     * @return
     */
    private static boolean isFileOverride(GeneratorProperties config, List<String> tableNames) {
        boolean isExists = checkExistsEntityCode(config, tableNames);
        if (isExists) {
            System.err.println("如需覆盖生成，请输入y(yes)继续覆盖生成代码，输入其它则跳过，不会再生成代码：");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (StringUtils.isNotBlank(line)) {
                line = line.toLowerCase();
                if (GeneratorConstant.Y.equals(line) || GeneratorConstant.YES.equals(line)) {
                    System.out.println("继续覆盖生成代码");
                    return true;
                } else {
                    System.out.println("跳过代码生成");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查所有的表是否已存在实体类
     *
     * @param config
     */
    private static boolean checkExistsEntityCode(GeneratorProperties config, List<String> tableNames) {
        String moduleName = config.getModuleName();
        boolean isExists = false;
        for (String tableName : tableNames) {
            String className = GeneratorUtil.getTableClassName(tableName, config.getTablePrefixes());
            // 判断代码是否存在
            String userDir = GeneratorConstant.USER_DIR;
            String packageName = config.getPackageName();
            String packagePath = packageName.replaceAll("\\.", "/");
            String entityPath = userDir + GeneratorConstant.SRC_MAIN_JAVA + packagePath + "/" + moduleName + "/" + GeneratorConstant.ENTITY_PACKAGE + "/" + className + GeneratorConstant.DOT_JAVA;
            File file = new File(entityPath);
            if (file.exists()) {
                isExists = true;
                System.err.println(tableName + "表对应的实体类" + className + "已经存在：" + entityPath);
            }
        }
        return isExists;
    }


    /**
     * 检查配置
     *
     * @param config
     */
    public static void checkConfig(GeneratorProperties config) throws Exception {
        String packageName = config.getPackageName();
        if (StringUtils.isBlank(packageName)) {
            throw new GeneratorException("包名称不能为空");
        }
        String moduleName = config.getModuleName();
        if (StringUtils.isBlank(moduleName)) {
            throw new GeneratorException("模块名称不能为空");
        }
        String author = config.getAuthor();
        if (StringUtils.isBlank(author)) {
            throw new GeneratorException("作者不能为空");
        }
        // 默认为ASSIGN_ID
        IdType idType = config.getIdType();
        if (idType == null) {
            config.setIdType(IdType.ASSIGN_ID);
        }
        boolean generatorBackend = config.isGeneratorBackend();
        boolean generatorAppBackend = config.isGeneratorAppBackend();
        boolean generatorFrontend = config.isGeneratorFrontend();
        if (!generatorBackend && !generatorAppBackend && !generatorFrontend) {
            throw new GeneratorException("前后端代码生成配置都为false，跳过代码生成");
        }
        if (generatorAppBackend && !generatorBackend) {
            throw new GeneratorException("需要设置generatorBackend为true，才能生成App后台代码");
        }
        // 默认请求映射风格
        RequestMappingStyle requestMappingStyle = config.getRequestMappingStyle();
        if (requestMappingStyle == null) {
            config.setRequestMappingStyle(RequestMappingStyle.DEFAULT);
        }
        String defaultOrderColumnName = config.getDefaultOrderColumnName();
        if (StringUtils.isBlank(defaultOrderColumnName)) {
            config.setDefaultOrderColumnName(GeneratorConstant.ID);
        }
        // 设置默认的上级菜单ID为0
        Long parentMenuId = config.getParentMenuId();
        if (parentMenuId == null) {
            config.setParentMenuId(0L);
        }
        // 设置默认表单布局方式为 2：一行两列
        GeneratorFormLayout generatorFormLayout = config.getFormLayout();
        if (generatorFormLayout == null) {
            config.setFormLayout(GeneratorFormLayout.TWO);
        }
        // 生成方式 1：zip压缩包，2：自定义路径
        config.setGeneratorType(GeneratorType.CUSTOM);
    }

    /**
     * 获取生成代码的表信息
     *
     * @param config
     * @param tableName
     * @return
     * @throws Exception
     */
    public static GeneratorTable getGeneratorTable(GeneratorProperties config, String tableName) throws Exception {
        // 获取数据库中的表信息
        GeneratorTableDbVo generatorTableDbVo = JdbcUtil.getGeneratorTableDbVo(tableName);
        if (generatorTableDbVo == null) {
            throw new GeneratorException(tableName + "表不存在或不能生成");
        }
        // 获取数据库中表的列信息集合
        List<GeneratorColumnDbVo> generatorColumnDbVos = JdbcUtil.getGeneratorColumnDbVos(tableName);
        if (CollectionUtils.isEmpty(generatorColumnDbVos)) {
            throw new GeneratorException(tableName + "列为空，跳过代码生成");
        }
        GeneratorTable table = new GeneratorTable();
        // 设置表基础信息
        GeneratorUtil.setGeneratorTable(config, tableName, generatorTableDbVo, table);
        // 设置列信息
        boolean validateField = config.isValidateField();
        List<GeneratorColumn> columns = GeneratorUtil.getGeneratorColumns(tableName, generatorColumnDbVos, validateField);
        GeneratorUtil.setTableColumnInfo(table, columns);
        return table;
    }

}
