package io.geekidea.boot.util;

import io.geekidea.boot.auth.util.TokenUtil;
import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.common.enums.SystemType;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author geekidea
 * @date 2023/11/22
 **/
public class SystemTypeUtil {

    /**
     * 获取系统类型枚举
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static SystemType getSystemTypeByPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return getSystemTypeByPath(servletPath);
    }

    /**
     * 根据请求路径获取系统类型枚举
     *
     * @param servletPath
     * @return
     * @throws Exception
     */
    public static SystemType getSystemTypeByPath(String servletPath) {
        if (StringUtils.isBlank(servletPath)) {
            return null;
        }
        if (servletPath.startsWith(LoginConstant.ADMIN_URL_PREFIX)) {
            return SystemType.ADMIN;
        } else if (servletPath.startsWith(LoginConstant.APP_URL_PREFIX)) {
            return SystemType.APP;
        }
        return null;
    }

    /**
     * 判断token是那个端
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static SystemType getSystemTypeByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (token.startsWith(LoginConstant.ADMIN_TOKEN_PREFIX)) {
            return SystemType.ADMIN;
        } else if (token.startsWith(LoginConstant.APP_TOKEN_PREFIX)) {
            return SystemType.APP;
        }
        return null;
    }

    /**
     * 判断token是那个端
     *
     * @return
     * @throws Exception
     */
    public static SystemType getSystemTypeByToken() {
        String token = TokenUtil.getToken();
        return getSystemTypeByToken(token);
    }

    /**
     * 判断token是那个端
     *
     * @return
     * @throws Exception
     */
    public static Integer getSystemTypeCodeByToken() {
        SystemType systemType = getSystemTypeByToken();
        if (systemType != null) {
            return systemType.getCode();
        }
        return null;
    }

    /**
     * 判断是否是管理后台系统
     *
     * @return
     * @throws Exception
     */
    public static boolean isAdminSystem() {
        SystemType systemType = getSystemTypeByToken();
        if (SystemType.ADMIN == systemType) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是APP移动端系统
     *
     * @return
     * @throws Exception
     */
    public static boolean isAppSystem() {
        SystemType systemType = getSystemTypeByToken();
        if (SystemType.APP == systemType) {
            return true;
        }
        return false;
    }

}
