package io.geekidea.boot.config.properties;

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
@ConfigurationProperties(prefix = "login.admin")
public class LoginAdminProperties {

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
    private Integer tokenExpireMinutes;

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

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = YamlUtil.parseListArray(excludePaths);
    }

    public void setIncludePaths(List<String> includePaths) {
        this.includePaths = YamlUtil.parseListArray(includePaths);
    }

    public Integer getTokenExpireMinutes() {
        if (tokenExpireMinutes == null) {
            this.tokenExpireMinutes = LoginConstant.ADMIN_TOKEN_EXPIRE_MINUTES;
        }
        return tokenExpireMinutes;
    }

}
