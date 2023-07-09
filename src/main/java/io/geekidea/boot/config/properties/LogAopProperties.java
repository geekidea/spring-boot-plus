package io.geekidea.boot.config.properties;

import io.geekidea.boot.framework.constant.CommonConstant;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/18
 **/
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.log-aop")
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
     * 登录路径
     */
    private String loginUrl = "/login";

    /**
     * 是否打印请求头日志
     */
    private boolean printHeadLog = false;

    /**
     * 排除的路径
     */
    private List<String> excludePaths;


    public void setExcludePaths(List<String> excludePaths) {
        if (CollectionUtils.isNotEmpty(excludePaths)) {
            List<String> excludePathList = new ArrayList<>();
            for (String excludePath : excludePaths) {
                if (StringUtils.isBlank(excludePath)) {
                    continue;
                }
                if (excludePath.contains(CommonConstant.COMMA)) {
                    String[] excludeArray = excludePath.split(CommonConstant.COMMA);
                    excludePathList.addAll(Arrays.asList(excludeArray));
                } else {
                    excludePathList.add(excludePath);
                }
            }
            this.excludePaths = excludePathList;
        } else {
            this.excludePaths = excludePaths;
        }
    }

}
