package io.geekidea.boot.config.properties;

import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/18
 **/
@Data
@ConfigurationProperties(prefix = "log-aop")
public class LogAopProperties {

    /**
     * 是否启用
     */
    private boolean enable = true;

    /**
     * 是否打印日志
     */
    private boolean printLog = true;

    /**
     * 是否打印请求头日志
     */
    private boolean printHeadLog = false;

    /**
     * 排除的路径
     */
    private List<String> excludePaths;


    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

}
