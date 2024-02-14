package io.geekidea.boot.config.properties;

import com.baomidou.mybatisplus.annotation.IdType;
import io.geekidea.boot.generator.enums.GeneratorFormLayout;
import io.geekidea.boot.generator.enums.GeneratorType;
import io.geekidea.boot.generator.enums.RequestMappingStyle;
import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 生成器默认配置
 *
 * @author geekidea
 * @date 2023/12/31
 **/
@Data
@Component
@PropertySource(value = {"classpath:generator.yml"})
@ConfigurationProperties(prefix = "generator")
public class GeneratorProperties {

    /**
     * 包名称
     */
    @Value("${packageName}")
    private String packageName;

    /**
     * 模块名称
     */
    @Value("${moduleName}")
    private String moduleName;

    /**
     * 作者
     */
    @Value("${author}")
    private String author;

    /**
     * 生成ID类型枚举类型
     */
    @Value("${idType}")
    private IdType idType;

    /**
     * 表前缀
     */
    @Value("${tablePrefixes}")
    private List<String> tablePrefixes;

    /**
     * 是否生成后台 1：是，0：否
     */
    @Value("${generatorBackend}")
    private boolean generatorBackend;

    /**
     * 是否生成前端 1：是，0：否
     */
    @Value("${generatorFrontend}")
    private boolean generatorFrontend;

    /**
     * 是否生成App后端代码 1：是，0：否
     */
    @Value("${generatorAppBackend}")
    private boolean generatorAppBackend;

    /**
     * 是否校验字段 1：是，0：否
     */
    @Value("${validateField}")
    private boolean validateField;

    /**
     * 是否启用日志注解 1：是，0：否
     */
    @Value("${enableLog}")
    private boolean enableLog;

    /**
     * 是否启用权限编码 1：是，0：否
     */
    @Value("${enablePermission}")
    private boolean enablePermission;

    /**
     * 请求映射风格 1：默认，2：restful，3：全部小写，4：中横线，5：下划线
     */
    @Value("${requestMappingStyle}")
    private RequestMappingStyle requestMappingStyle = RequestMappingStyle.DEFAULT;


    /**
     * 默认降序排列名称，如id、create_time，如果为空，则不指定默认排序
     */
    @Value("${defaultOrderColumnName}")
    private String defaultOrderColumnName;

    /**
     * 上级菜单ID
     */
    @Value("${parentMenuId}")
    private Long parentMenuId;

    /**
     * 表单布局方式 1：一行一列，2：一行两列
     */
    @Value("${formLayout}")
    private GeneratorFormLayout formLayout;

    /**
     * 生成方式 1：zip压缩包，2：自定义路径
     */
    @Value("${generatorType}")
    private GeneratorType generatorType;

    /**
     * 自定义生成后端路径
     */
    @Value("${customBackendPath}")
    private String customBackendPath;

    /**
     * 自定义生成前端路径
     */
    @Value("${customFrontendPath}")
    private String customFrontendPath;

    /**
     * 是否显示默认查询条件
     */
    @Value("${showDefaultQuery}")
    private boolean showDefaultQuery;

    /**
     * 搜索框模糊查询字段
     */
    @Value("${keywordFields}")
    private List<String> keywordFields;

    /**
     * 是否只生成实体类 1：是，0：否
     */
    @Value("${onlyGeneratorEntity}")
    private boolean onlyGeneratorEntity;

    public void setTablePrefixes(List<String> tablePrefixes) {
        this.tablePrefixes = YamlUtil.parseListArray(tablePrefixes);
    }

    public void setKeywordFields(List<String> keywordFields) {
        this.keywordFields = YamlUtil.parseListArray(keywordFields);
    }

}
