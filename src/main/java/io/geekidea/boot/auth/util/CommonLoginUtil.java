package io.geekidea.boot.auth.util;

import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.util.SystemTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2023/11/24
 **/
@Component
public class CommonLoginUtil {

    /**
     * 根据token获取用户ID
     *
     * @param token
     * @return
     * @
     */
    public static Long getUserId(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken(token);
        return getUserId(systemType);
    }

    /**
     * 获取用户ID
     *
     * @return
     * @
     */
    public static Long getUserId() {
        String token = TokenUtil.getToken();
        return getUserId(token);
    }

    /**
     * 获取登录用户ID字符串
     *
     * @return
     * @
     */
    public static String getUserIdString() {
        Long userId = getUserId();
        if (userId == null) {
            return null;
        }
        return userId.toString();
    }

    /**
     * 根据系统类型获取用户ID
     *
     * @param systemType
     * @return
     * @
     */
    public static Long getUserId(SystemType systemType) {
        try {
            if (SystemType.ADMIN == systemType) {
                return LoginUtil.getUserId();
            } else if (SystemType.APP == systemType) {
                return AppLoginUtil.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
