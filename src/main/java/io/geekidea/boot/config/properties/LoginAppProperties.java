package io.geekidea.boot.config.properties;

import io.geekidea.boot.auth.enums.LoginInterceptStrategy;
import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@Component
@ConfigurationProperties(prefix = "login.app")
public class LoginAppProperties {

    /**
     * 是否启用登录校验
     */
    private boolean enable;

    /**
     * 默认拦截包含路径开头的所有请求(/app/**)，在不需要验证登录的controller方法加上@IgnoreLogin，表示此方法不需要登录就能调用
     * 默认不拦截包含路径开头的所有请求(/app/**)，在需要登录的controller方法上加上注解@Login，表示此方法必须登录才能调用
     */
    private LoginInterceptStrategy loginInterceptStrategy;

    /**
     * 是否单次登录
     * true: 用户最后一次登录有效，之前的登录会话下线
     * false: 用户可多次登录，多次登录的会话都生效
     */
    private boolean singleLogin;

    /**
     * token过期天数
     */
    private Integer tokenExpireDays;

    /**
     * 方法是否鉴权
     */
    private boolean loginPermission;

    /**
     * 包含的路径
     */
    private List<String> includePaths;

    /**
     * 排除的路径
     */
    private List<String> excludePaths;

    /**
     * 需要登录校验的请求路径，与@Login效果一样
     */
    private List<String> checkLoginPaths;

    /**
     * 忽略登录校验的请求路径，与@IgnoreLogin效果一样
     */
    private List<String> ignoreLoginPaths;

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

    public Integer getTokenExpireDays() {
        if (tokenExpireDays == null) {
            this.tokenExpireDays = LoginConstant.APP_TOKEN_EXPIRE_DAYS;
        }
        return tokenExpireDays;
    }

    public void setCheckLoginPaths(List<String> checkLoginPaths) {
        this.checkLoginPaths = YamlUtil.parseListArray(checkLoginPaths);
    }

    public void setIgnoreLoginPaths(List<String> ignoreLoginPaths) {
        this.ignoreLoginPaths = YamlUtil.parseListArray(ignoreLoginPaths);
    }

}
