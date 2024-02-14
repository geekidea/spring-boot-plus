package io.geekidea.boot.generator.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author geekidea
 * @date 2023/12/31
 **/
public interface GeneratorConstant {

    String DB_TINYINT = "tinyint";
    String DB_SMALLINT = "smallint";
    String DB_MEDIUMINT = "mediumint";
    String DB_INT = "int";
    String DB_INTEGER = "integer";
    String DB_BIGINT = "bigint";
    String DB_FLOAT = "float";
    String DB_DOUBLE = "double";
    String DB_DECIMAL = "decimal";
    String DB_BIT = "bit";
    String DB_CHAR = "char";
    String DB_VARCHAR = "varchar";
    String DB_TINYTEXT = "tinytext";
    String DB_TEXT = "text";
    String DB_MEDIUMTEXT = "mediumtext";
    String DB_LONGTEXT = "longtext";
    String DB_DATE = "date";
    String DB_DATETIME = "datetime";
    String DB_TIMESTAMP = "timestamp";
    String DB_TIME = "time";
    String DB_JSON = "json";
    List<String> DB_INT_LIST = Arrays.asList(DB_TINYINT, DB_SMALLINT, DB_MEDIUMINT, DB_INT, DB_INTEGER);

    String JAVA_INTEGER = "Integer";
    String JAVA_LONG = "Long";
    String JAVA_FLOAT = "Float";
    String JAVA_DOUBLE = "Double";
    String JAVA_BIG_DECIMAL = "BigDecimal";
    String JAVA_BOOLEAN = "Boolean";
    String JAVA_STRING = "String";
    String JAVA_DATE = "Date";
    String JAVA_TIME = "Time";
    String JAVA_SERIALIZABLE = "Serializable";

    /**
     * 公共排除字段
     */
    List<String> COMMON_EXCLUDE_FIELDS = Arrays.asList("version", "deleted", "createId", "updateId", "createTime", "updateTime");
    /**
     * 填充自动插入的列
     */
    List<String> FILL_INSERT_FIELDS = Arrays.asList("createId", "createTime");
    /**
     * 填充自动修改的列
     */
    List<String> FILL_UPDATE_FIELDS = Arrays.asList("updateId", "updateTime");
    /**
     * 默认关键词默认查询列
     */
    List<String> KEYWORD_QUERY_FIELDS = Arrays.asList("name");
    /**
     * 默认APP端关键词默认查询列
     */
    List<String> APP_KEYWORD_QUERY_FIELDS = Arrays.asList("name");
    /**
     * 默认时间筛选查询列
     */
    String CREATE_TIME_FIELD = "createTime";
    /**
     * 移除表名称后缀
     */
    List<String> REMOVE_TABLE_NAME_SUFFIXES = Arrays.asList("表");

    /**
     * addmin名称
     */
    String ADMIN = "admin";

    /**
     * app前缀
     */
    String APP_PREFIX = "App";

    /**
     * app名称
     */
    String APP = "app";

    /**
     * 管理后台请求路径映射
     */
    String ADMIN_MAPPING = "/" + ADMIN;

    /**
     * APP请求路径映射
     */
    String APP_MAPPING = "/" + APP;

    String SRC_MAIN = "/src/main/";
    String SRC_MAIN_JAVA = "/src/main/java/";
    String SRC_TEST_JAVA = "/src/test/java/";
    String SRC_MAIN_RESOURCES = "/src/main/resources/";
    String SRC_MAIN_RESOURCES_MAPPER = "/src/main/resources/mapper/";
    String JAVA_DIR = "/java";
    String VUE_DIR = "/vue";
    String VUE_SRC = "/vue/src/";
    String VUE_SRC_API = "/src/api/";
    String VUE_SRC_VIEWS = "/src/views/";
    String VUE_API_PATH = "/api/%s";
    String VUE_LIST_PATH = "/index";
    String MENU_SQL_PATH = "/menu/";


    String DOT_JAVA = ".java";
    String DOT_XML = ".xml";
    String DOT_SQL = ".sql";
    String DOT_JS = ".js";
    String DOT_VUE = ".vue";
    String POM_XML = "pom.xml";
    String APPLICATION_YML = SRC_MAIN_RESOURCES + "application.yml";
    String UTF8 = "UTF-8";
    String DEFAULT_PROJECT_NAME = "spring-boot-plus";

    String ENTITY_PACKAGE = "entity";
    String DTO_PACKAGE = "dto";
    String QUERY_PACKAGE = "query";
    String VO_PACKAGE = "vo";
    String ADMIN_CONTROLLER_PACKAGE = "controller";
    String ADMIN_CONTROLLER_PACKAGE_FILE_PATH = ADMIN_CONTROLLER_PACKAGE.replaceAll("\\.", "/");
    String APP_CONTROLLER_PACKAGE = "controller";
    String APP_CONTROLLER_PACKAGE_FILE_PATH = APP_CONTROLLER_PACKAGE.replaceAll("\\.", "/");
    String SERVICE_PACKAGE = "service";
    String SERVICE_IMPL_PACKAGE = "service.impl";
    String SERVICE_IMPL_PACKAGE_FILE_PATH = SERVICE_IMPL_PACKAGE.replaceAll("\\.", "/");
    String MAPPER_PACKAGE = "mapper";

    String DTO = "Dto";
    String LOWER_DTO = "dto";
    String QUERY = "Query";
    String LOWER_QUERY = "query";
    String VO = "Vo";
    String CONTROLLER = "Controller";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String MAPPER = "Mapper";

    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String INFO = "info";
    String PAGE = "Page";
    String LOWER_PAGE = "page";
    String GET = "get";
    String ADD_CN = "添加";
    String UPDATE_CN = "修改";
    String DELETE_CN = "删除";
    String GET_CN = "获取";
    String INFO_CN = "详情";
    String PAGE_CN = "分页列表";

    String REQUEST_MAPPING = "@RequestMapping";
    String GET_MAPPING = "@GetMapping";
    String POST_MAPPING = "@PostMapping";
    String PUT_MAPPING = "@PutMapping";
    String DELETE_MAPPING = "@DeleteMapping";

    String REQUEST_BODY = "@RequestBody";
    String PARAMETER_OBJECT = "@ParameterObject";

    String REQUEST_MAPPING_FORMAT = REQUEST_MAPPING + "(\"%s\")";
    String GET_MAPPING_FORMAT = GET_MAPPING + "(\"%s\")";
    String POST_MAPPING_FORMAT = POST_MAPPING + "(\"%s\")";
    String PUT_MAPPING_FORMAT = PUT_MAPPING + "(\"%s\")";
    String DELETE_MAPPING_FORMAT = DELETE_MAPPING + "(\"%s\")";

    /**
     * 是否启用系统日志注解
     */
    boolean ENABLE_SYS_LOG = true;
    /**
     * 是否启用权限编码注解
     */
    boolean ENABLE_PERMISSION = true;

    String ID = "id";

    String APP_CONTROLLER_TEMPLATE_NAME = "appController";
    String APP_QUERY_TEMPLATE_NAME = "appQuery";
    String APP_VO_TEMPLATE_NAME = "appVo";
    String CONTROLLER_TEMPLATE_NAME = "controller";
    String DTO_TEMPLATE_NAME = "dto";
    String ENTITY_TEMPLATE_NAME = "entity";
    String MAPPER_TEMPLATE_NAME = "mapper";
    String MAPPER_XML_TEMPLATE_NAME = "mapperXml";
    String QUERY_TEMPLATE_NAME = "query";
    String SERVICE_TEMPLATE_NAME = "service";
    String SERVICE_IMPL_TEMPLATE_NAME = "serviceImpl";
    String VO_TEMPLATE_NAME = "vo";
    String MENU_SQL_TEMPLATE_NAME = "menuSql";
    String API_TS_TEMPLATE_NAME = "apiTs";
    String VUE_INDEX_TEMPLATE_NAME = "vueIndex";
    String VUE_FORM_TEMPLATE_NAME = "vueForm";

    String TEMPLATES_PATH = "templates/";

    String APP_CONTROLLER_TEMPLATE_PATH = TEMPLATES_PATH + "appController.java.vm";
    String APP_QUERY_TEMPLATE_PATH = TEMPLATES_PATH + "appQuery.java.vm";
    String APP_VO_TEMPLATE_PATH = TEMPLATES_PATH + "appVo.java.vm";
    String CONTROLLER_TEMPLATE_PATH = TEMPLATES_PATH + "controller.java.vm";
    String DTO_TEMPLATE_PATH = TEMPLATES_PATH + "dto.java.vm";
    String ENTITY_TEMPLATE_PATH = TEMPLATES_PATH + "entity.java.vm";
    String MAPPER_TEMPLATE_PATH = TEMPLATES_PATH + "mapper.java.vm";
    String MAPPER_XML_TEMPLATE_PATH = TEMPLATES_PATH + "mapper.xml.vm";
    String QUERY_TEMPLATE_PATH = TEMPLATES_PATH + "query.java.vm";
    String SERVICE_TEMPLATE_PATH = TEMPLATES_PATH + "service.java.vm";
    String SERVICE_IMPL_TEMPLATE_PATH = TEMPLATES_PATH + "serviceImpl.java.vm";
    String VO_TEMPLATE_PATH = TEMPLATES_PATH + "vo.java.vm";
    String MENU_SQL_TEMPLATE_PATH = TEMPLATES_PATH + "menu.sql.vm";
    String API_TS_TEMPLATE_PATH = TEMPLATES_PATH + "api.ts.vm";
    String VUE_INDEX_TEMPLATE_PATH = TEMPLATES_PATH + "vue.index.vm";
    String VUE_FORM_TEMPLATE_PATH = TEMPLATES_PATH + "vue.form.vm";

    String MENU_SQL_FORMAT_FILE_NAME = "%s-menu.sql";
    String API_TS_FORMAT_FILE_NAME = "%s.ts";
    String VUE_INDEX_FILE_NAME = "index.vue";
    String VUE_FORM_FILE_NAME = "form.vue";

    /**
     * 当前项目目录
     */
    String USER_DIR = System.getProperty("user.dir");

    String MANAGER_NAME = "管理";
    String LIST_NAME = "列表";

    String Y = "y";
    String YES = "yes";

}
