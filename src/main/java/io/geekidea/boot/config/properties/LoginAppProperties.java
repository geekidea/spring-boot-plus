package io.geekidea.boot.config.properties;

import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.util.YamlUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Data
@ConfigurationProperties(prefix = "login.app")
public class LoginAppProperties {

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

    public void setExcludePaths(List<String> includePaths) {
        this.includePaths = YamlUtil.parseListArray(includePaths);
    }

    public Integer getTokenExpireDays() {
        if (tokenExpireDays == null) {
            this.tokenExpireDays = LoginConstant.APP_TOKEN_EXPIRE_DAYS;
        }
        return tokenExpireDays;
    }
}
