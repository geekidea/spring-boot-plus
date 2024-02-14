package io.geekidea.boot.config.properties;

import io.geekidea.boot.framework.enums.ResponseLogType;
import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/18
 **/
@Data
@Component
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

    /**
     * 响应日志类型：FULL 全部，PART 部分，NONE 无
     */
    private ResponseLogType responseLogType;


    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

}
