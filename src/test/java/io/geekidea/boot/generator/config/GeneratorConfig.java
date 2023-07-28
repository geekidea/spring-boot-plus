package io.geekidea.boot.generator.config;

import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.BasePageQuery;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.generator.enums.DefaultOrderType;
import io.geekidea.boot.generator.enums.RequestMappingType;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author geekidea
 * @date 2022/4/19
 **/
@Slf4j
@Data
@Accessors(chain = true)
public class GeneratorConfig {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动名称
     */
    private String driver;
    /**
     * 驱动URL
     */
    private String url;
    /**
     * 生成的类路径
     */
    private String projectPackagePath;
    /**
     * 项目主包路径
     */
    private String parentPackage;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成的表名称数组
     */
    private Set<String> tableNames;
    /**
     * 分页列表查询是否排序 true：有排序参数/false：无
     */
    private boolean pageListOrder = true;
    /**
     * 是否生成validation校验，true：生成/false：不生成
     */
    private boolean paramValidation = true;
    /**
     * 是否生成实体类
     */
    private boolean generatorEntity = true;
    /**
     * 是否生成控制器
     */
    private boolean generatorController = true;
    /**
     * 是否生成service接口
     */
    private boolean generatorService = true;
    /**
     * 是否生成service实现类
     */
    private boolean generatorServiceImpl = true;
    /**
     * 是否生成Mapper
     */
    private boolean generatorMapper = true;
    /**
     * 是否生成Mapper XML
     */
    private boolean generatorMapperXml = true;
    /**
     * 是否生成AddDto参数
     */
    private boolean generatorAddDto = true;
    /**
     * 是否生成UpdateDto参数
     */
    private boolean generatorUpdateDto = true;
    /**
     * 是否生成查询参数
     */
    private boolean generatorQuery = true;
    /**
     * 是否生成查询VO
     */
    private boolean generatorVo = true;
    /**
     * 是否生成详情VO
     */
    private boolean generatorInfoVo = true;
    /**
     * 是否生成Shiro RequiresPermissions 注解
     */
    private boolean requiresPermissions;
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
     * 实体父类实体列表
     */
    private String[] superEntityCommonColumns;
    /**
     * 公共id参数路径
     */
    private String commonIdParam;

    // 公共类包路径
    /**
     * 公共结果集路径
     */
    private String commonApiResult;
    /**
     * 公共排序查询参数
     */
    private String superQuery;
    /**
     * 公共分页对象
     */
    private String commonPaging;
    /**
     * 公共业务异常
     */
    private String commonBusinessException;
    /**
     * 公共排序
     */
    private String commonOrderByItem;
    /**
     * 是否文件覆盖
     */
    private boolean fileOverride;
    /**
     * 是否生成resultMap,默认为false
     */
    private boolean baseResultMap;
    /**
     * 公共字段
     */
    private List<String> commonFields = new ArrayList<>();
    /**
     * vo排除字段
     */
    private List<String> voExcludeFields = new ArrayList<>();

    /**
     * 主键生成类型
     */
    private IdType idType = IdType.ASSIGN_ID;
    /**
     * 是否生成查询参数
     */
    private boolean onlyOverrideEntity;
    /**
     * 表前缀
     */
    private String[] tablePrefix;
    /**
     * 默认排序类型
     */
    private DefaultOrderType defaultOrderType = DefaultOrderType.PK_ID;
    /**
     * 创建时间列名称
     */
    private String createTimeColumn = "create_time";
    /**
     * 修改时间列名称
     */
    private String updateTimeColumn = "update_time";
    /**
     * schema
     */
    private String schema;
    /**
     * 是否生成权限编码
     */
    private boolean permission = true;
    /**
     * 是否生成简单模式，只生成对应的类，不生成方法
     */
    private boolean simple = false;

    /**
     * 实体类包名称
     */
    private String entityPackage = "entity";

    /**
     * 控制器包名称
     */
    private String controllerPackage = "controller";

    /**
     * service接口包名称
     */
    private String servicePackage = "service";

    /**
     * serviceImpl实现类包名称
     */
    private String serviceImplPackage = "service.impl";

    /**
     * mapper包名称
     */
    private String mapperPackage = "mapper";
    /**
     * dto包名称
     */
    private String dtoPackage = "dto";
    /**
     * query包名称
     */
    private String queryPackage = "query";
    /**
     * vo包名称
     */
    private String voPackage = "vo";

    /**
     * 格式化entity文件名称
     */
    private String formatEntityFileName = "%s";

    /**
     * 格式化controller文件名称
     */
    private String formatControllerFileName = "%sController";

    /**
     * 格式化service文件名称
     */
    private String formatServiceFileName = "%sService";

    /**
     * 格式化serviceImpl文件名称
     */
    private String formatServiceImplFileName = "%sServiceImpl";

    /**
     * 格式化mapper文件名称
     */
    private String formatMapperFileName = "%sMapper";

    /**
     * 格式化mapperXml文件名称
     */
    private String formatXmlFileName = "%sMapper";

    /**
     * 自定义addDto文件名称
     */
    private String addDtoFileName = "AddDto";
    /**
     * 自定义updateDto文件名称
     */
    private String updateDtoFileName = "UpdateDto";
    /**
     * 自定义query文件名称
     */
    private String queryFileName = "Query";
    /**
     * 自定义infoVo文件名称
     */
    private String infoVoFileName = "InfoVo";
    /**
     * 自定义vo文件名称
     */
    private String voFileName = "Vo";


    /**
     * dto包路径
     */
    private String dtoPackagePath;
    /**
     * query包路径
     */
    private String queryPackagePath;
    /**
     * vo包路径
     */
    private String voPackagePath;

    /**
     * 请求路径是否加上模块名称
     */
    private boolean requestMappingModule = false;

    /**
     * 请求路径映射类型
     */
    private RequestMappingType requestMappingType = RequestMappingType.CAMEL;

    /**
     * 控制器请求路径
     */
    private String controllerRequestMapping = "%s";

    /**
     * 添加请求路径
     */
    private String addRequestMapping = "add%s";

    /**
     * 修改请求路径
     */
    private String updateRequestMapping = "update%s";

    /**
     * 删除请求路径
     */
    private String deleteRequestMapping = "delete%s";

    /**
     * 详情请求路径
     */
    private String infoRequestMapping = "get%sInfo";

    /**
     * 分页列表请求路径
     */
    private String pageRequestMapping = "get%sPage";


    public GeneratorConfig() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String generatorYmlPath = "/src/test/resources/generator.yml";
        String configFileFullPath = projectPath + generatorYmlPath;
        Dict dict = YamlUtil.loadByPath(configFileFullPath);
        String driver = dict.getStr("driver");
        String url = dict.getStr("url");
        String username = dict.getStr("username");
        String password = dict.getStr("password");
        log.info("生成代码JDBC配置信息：");
        log.info("配置文件路径：" + configFileFullPath);
        log.info("driver: " + driver);
        log.info("url: " + url);
        log.info("username: " + username);
        log.info("password: " + password);
        if (StringUtils.isBlank(driver) ||
                StringUtils.isBlank(url) ||
                StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)
        ) {
            throw new RuntimeException("JDBC配置异常，请检查" + generatorYmlPath + "配置文件");
        }
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 初始化变量
     */
    public void init() {
        if (StringUtils.isBlank(this.parentPackage)) {
            throw new BusinessException("parentPackage不能为空");
        }
        this.projectPackagePath = this.parentPackage.replaceAll("\\.", "/");
        this.commonParentPackage = parentPackage + ".framework";
        this.superService = BaseService.class.getName();
        this.superServiceImpl = BaseServiceImpl.class.getName();
        this.superEntityCommonColumns = new String[]{};

        this.commonApiResult = ApiResult.class.getName();
        this.superQuery = BasePageQuery.class.getName();
        this.commonPaging = Paging.class.getName();
        this.commonBusinessException = BusinessException.class.getName();
        this.commonOrderByItem = OrderByItem.class.getName();
        this.commonFields = Arrays.asList("remark", "version", "deleted", "createTime", "updateTime");
        this.voExcludeFields = Arrays.asList("password", "version", "deleted");

        this.dtoPackagePath = parentPackage + "." + moduleName + "." + dtoPackage;
        this.queryPackagePath = parentPackage + "." + moduleName + "." + queryPackage;
        this.voPackagePath = parentPackage + "." + moduleName + "." + voPackage;

        if (onlyOverrideEntity) {
            this.generatorEntity = true;
            this.generatorAddDto = false;
            this.generatorUpdateDto = false;
            this.generatorQuery = false;
            this.generatorVo = false;
            this.generatorController = false;
            this.generatorService = false;
            this.generatorServiceImpl = false;
            this.generatorMapper = false;
            this.generatorMapperXml = false;
        }
        if (simple) {
            this.generatorAddDto = false;
            this.generatorUpdateDto = false;
            this.generatorQuery = false;
            this.generatorVo = false;
        }
    }

    public GeneratorConfig setTableNames(String... tableNames) {
        if (ArrayUtils.isNotEmpty(tableNames)) {
            Set<String> set = new LinkedHashSet<>();
            for (String tableName : tableNames) {
                if (StringUtils.isNotBlank(tableName)) {
                    tableName = handlerTableName(tableName);
                    set.add(tableName);
                }
            }
            this.tableNames = set;
        }
        return this;
    }

    private String handlerTableName(String tableName) {
        return tableName;
    }

    public GeneratorConfig setTablePrefix(String... tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

}
