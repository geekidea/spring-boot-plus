package io.geekidea.boot.generator.jdbc;

import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import io.geekidea.boot.generator.exception.GeneratorException;
import io.geekidea.boot.generator.vo.GeneratorColumnDbVo;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author geekidea
 * @date 2024/1/15
 **/
@Slf4j
public class JdbcUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        // 加载jdbc配置
        loadJdbcConfig();
        try {
            // 加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载jdbc配置信息
     */
    public static void loadJdbcConfig() {
        log.info("生成代码JDBC连接信息：");
        String projectPath = System.getProperty("user.dir");
        String applicationYmlPath = "/src/main/resources/application.yml";
        String applicationYmlFilePath = projectPath + applicationYmlPath;
        Dict applicationYmlDict = YamlUtil.loadByPath(applicationYmlFilePath);
        // 从application.yml中获取spring.profiles.active的环境值，如：dev、test、prod等
        String active = applicationYmlDict.getByPath("spring.profiles.active", String.class);
        log.info("active = " + active);
        // 从application.yml获取driver-class-name
        driver = applicationYmlDict.getByPath("spring.datasource.driver-class-name", String.class);
        // 获取指定环境的配置文件
        String applicationActiveYmlPath = "/src/main/resources/application-" + active + ".yml";
        String applicationActiveYmlFilePath = projectPath + applicationActiveYmlPath;
        Dict applicationActiveYmlDict = YamlUtil.loadByPath(applicationActiveYmlFilePath);
        // 读取url、username、password
        url = applicationActiveYmlDict.getByPath("spring.datasource.url", String.class);
        username = applicationActiveYmlDict.getByPath("spring.datasource.username", String.class);
        password = applicationActiveYmlDict.getByPath("spring.datasource.password", String.class);
        log.info("activeFile：" + applicationActiveYmlFilePath);
        log.info("driver: " + driver);
        log.info("url: " + url);
        log.info("username: " + username);
        log.info("password: " + password);
        if (StringUtils.isBlank(driver) ||
                StringUtils.isBlank(url) ||
                StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)
        ) {
            throw new GeneratorException("JDBC配置异常");
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据库原始表信息
     *
     * @param tableName
     * @return
     */
    public static GeneratorTableDbVo getGeneratorTableDbVo(String tableName) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "select TABLE_NAME, TABLE_COMMENT, CREATE_TIME\n" +
                    "        from information_schema.TABLES\n" +
                    "        where TABLE_SCHEMA = (select database())\n" +
                    "          and TABLE_NAME not like 'generator_%'\n" +
                    "          and TABLE_NAME = ?";
            log.info("get tableInfo sql:" + sql);
            prepareStatement = connection.prepareStatement(sql);
            log.info("tableName:" + tableName);
            prepareStatement.setString(1, tableName);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String dbTableName = resultSet.getString("TABLE_NAME");
                String tableComment = resultSet.getString("TABLE_COMMENT");
                Date createTime = resultSet.getDate("CREATE_TIME");
                GeneratorTableDbVo generatorTableDbVo = new GeneratorTableDbVo();
                generatorTableDbVo.setTableName(dbTableName);
                generatorTableDbVo.setTableComment(tableComment);
                generatorTableDbVo.setCreateTime(createTime);
                return generatorTableDbVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, prepareStatement, resultSet);
        }
        return null;
    }

    /**
     * 获取数据库原始表信息
     *
     * @param tableName
     * @return
     */
    public static List<GeneratorColumnDbVo> getGeneratorColumnDbVos(String tableName) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "select TABLE_NAME,\n" +
                    "               COLUMN_NAME,\n" +
                    "               COLUMN_COMMENT,\n" +
                    "               DATA_TYPE,\n" +
                    "               COLUMN_TYPE,\n" +
                    "               ORDINAL_POSITION                 columnSort,\n" +
                    "               if(COLUMN_KEY = 'PRI', 1, 0)     isPk,\n" +
                    "               if(IS_NULLABLE = 'NO', 1, 0)     isRequired,\n" +
                    "               if(COLUMN_DEFAULT is null, 0, 1) isDefaultValue\n" +
                    "        from information_schema.COLUMNS\n" +
                    "        where TABLE_SCHEMA = (select database())\n" +
                    "          and TABLE_NAME = ?\n" +
                    "        order by ORDINAL_POSITION";
            log.info("get columnInfo sql:" + sql);
            prepareStatement = connection.prepareStatement(sql);
            log.info("tableName:" + tableName);
            prepareStatement.setString(1, tableName);
            resultSet = prepareStatement.executeQuery();
            List<GeneratorColumnDbVo> generatorColumnDbVos = new ArrayList<>();
            while (resultSet.next()) {
                String dbTableName = resultSet.getString("TABLE_NAME");
                String columnName = resultSet.getString("COLUMN_NAME");
                String columnComment = resultSet.getString("COLUMN_COMMENT");
                String dataType = resultSet.getString("DATA_TYPE");
                String columnType = resultSet.getString("COLUMN_TYPE");
                Integer columnSort = resultSet.getInt("columnSort");
                Boolean isPk = resultSet.getBoolean("isPk");
                Boolean isRequired = resultSet.getBoolean("isRequired");
                Boolean isDefaultValue = resultSet.getBoolean("isDefaultValue");
                GeneratorColumnDbVo generatorColumnDbVo = new GeneratorColumnDbVo();
                generatorColumnDbVo.setTableName(dbTableName);
                generatorColumnDbVo.setColumnName(columnName);
                generatorColumnDbVo.setColumnComment(columnComment);
                generatorColumnDbVo.setDataType(dataType);
                generatorColumnDbVo.setColumnType(columnType);
                generatorColumnDbVo.setColumnSort(columnSort);
                generatorColumnDbVo.setIsPk(isPk);
                generatorColumnDbVo.setIsRequired(isRequired);
                generatorColumnDbVo.setIsDefaultValue(isDefaultValue);
                generatorColumnDbVos.add(generatorColumnDbVo);
                System.out.println("generatorColumnDbVo = " + generatorColumnDbVo);
            }
            return generatorColumnDbVos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, prepareStatement, resultSet);
        }
        return null;
    }

    /**
     * 释放资源
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
