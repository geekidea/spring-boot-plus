package io.geekidea.boot.auth.util;

import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.util.SystemTypeUtil;
import io.geekidea.boot.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2023/11/24
 **/
@Component
public class LoginCommonUtil {

    /**
     * 根据token获取用户ID
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Long getUserId(String token) throws Exception {
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
     * @throws Exception
     */
    public static Long getUserId() throws Exception {
        String token = TokenUtil.getToken();
        return getUserId(token);
    }

    /**
     * 根据系统类型获取用户ID
     *
     * @param systemType
     * @return
     * @throws Exception
     */
    public static Long getUserId(SystemType systemType) throws Exception {
        if (SystemType.ADMIN == systemType) {
            return LoginUtil.getUserId();
        } else if (SystemType.APP == systemType) {
            return LoginAppUtil.getUserId();
        }
        return null;
    }

}
