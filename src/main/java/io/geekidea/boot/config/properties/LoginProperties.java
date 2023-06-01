package io.geekidea.boot.config.properties;

import io.geekidea.boot.auth.constant.LoginConstant;
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
 * @date 2022/6/26
 **/
@Data
@ConfigurationProperties(prefix = "spring-boot-plus.login")
public class LoginProperties {

    /**
     * 是否启用登录校验
     */
    private boolean enable;

    /**
     * 是否单次登录
     * true: 用户最后一次登录有效，之前的登录会话下线
     * false: 用户可多次登录，多次登录的会话都生效
     */
    private boolean singleLogin;

    /**
     * token过期分钟数
     */
    private Integer tokenExpireMinutes = LoginConstant.DEFAULT_TOKEN_EXPIRE_MINUTES;

    /**
     * 方法是否鉴权
     */
    private boolean loginPermission = true;

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
        System.out.println(this.excludePaths);

    }

    public Integer getTokenExpireMinutes() {
        if (tokenExpireMinutes == null) {
            this.tokenExpireMinutes = LoginConstant.DEFAULT_TOKEN_EXPIRE_MINUTES;
        }
        return tokenExpireMinutes;
    }
}
