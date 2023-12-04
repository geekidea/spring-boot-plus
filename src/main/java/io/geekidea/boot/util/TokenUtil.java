package io.geekidea.boot.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.common.constant.LoginConstant;
import io.geekidea.boot.common.enums.SystemType;
import io.geekidea.boot.framework.exception.LoginTokenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Token工具类
 *
 * @author geekidea
 * @date 2022-1-05
 **/
@Slf4j
@Component
public class TokenUtil {

    /**
     * 生成Admin后台Token
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public static String generateAdminToken(Long userId) throws Exception {
        String userMd5 = DigestUtils.md5Hex(userId.toString());
        String adminToken = LoginConstant.ADMIN_TOKEN_PREFIX + userMd5 + "." + IdWorker.getIdStr();
        return adminToken;
    }

    /**
     * 生成用户端Token
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public static String generateAppToken(Long userId) throws Exception {
        String userMd5 = DigestUtils.md5Hex(userId.toString());
        String appToken = LoginConstant.APP_TOKEN_PREFIX + userMd5 + "." + IdWorker.getIdStr();
        return appToken;
    }

    /**
     * 获取短的ID
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public static String getShortId(Long userId) throws Exception {
        // 将数字转换成数字加字母变为更短的字符串
        // 36 表示基数(10 位数字 + 26 个字符)
        String string = Long.toString(userId, 36);
        return string;
    }

    /**
     * 解析短的ID
     *
     * @param shorUserId
     * @return
     * @throws Exception
     */
    public static Long parseShortId(String shorUserId) throws Exception {
        long userId = Long.parseLong(shorUserId, 36);
        return userId;
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() throws Exception {
        // 从请求对象中获取
        HttpServletRequest request = HttpRequestUtil.getRequest();
        String token = (String) request.getAttribute(CommonConstant.REQUEST_PARAM_TOKEN);
        return token;
    }

    /**
     * 从请求头或者请求参数中获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(LoginConstant.TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // 从请求参数中获取token
            token = request.getParameter(LoginConstant.TOKEN_NAME);
        }
        String servletPath = request.getServletPath();
        SystemType systemType = SystemTypeUtil.getSystemTypeByPath(servletPath);
        if (StringUtils.isBlank(token)) {
            // 从cookie中获取token
            token = getTokenByCookie(request, systemType);
            if (StringUtils.isNotBlank(token) && !token.startsWith(LoginConstant.TOKEN_PREFIX)) {
                token = null;
            }
        }
        if (StringUtils.isBlank(token)) {
            return null;
        }
        // 校验token
        if (!token.startsWith(LoginConstant.TOKEN_PREFIX)) {
            log.error("token错误:" + token);
            throw new LoginTokenException("token错误");
        }
        if (SystemType.ADMIN == systemType) {
            checkAdminToken(token);
        } else if (SystemType.APP == systemType) {
            checkAppToken(token);
        }
        return token;
    }

    /**
     * 从Cookie中获取token
     *
     * @param request
     * @return
     */
    public static String getTokenByCookie(HttpServletRequest request, SystemType systemType) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return null;
        }
        if (SystemType.ADMIN == systemType) {
            return getCookieValueByName(cookies, LoginConstant.ADMIN_COOKIE_TOKEN_NAME);
        } else if (SystemType.APP == systemType) {
            return getCookieValueByName(cookies, LoginConstant.APP_COOKIE_TOKEN_NAME);
        } else {
            String cookieValue = getCookieValueByName(cookies, LoginConstant.ADMIN_COOKIE_TOKEN_NAME);
            if (StringUtils.isBlank(cookieValue)) {
                cookieValue = getCookieValueByName(cookies, LoginConstant.APP_COOKIE_TOKEN_NAME);
            }
            return cookieValue;
        }
    }

    /**
     * 通过cookie名称获取cookie值
     *
     * @param cookies
     * @param cookieName
     * @return
     * @throws Exception
     */
    public static String getCookieValueByName(Cookie[] cookies, String cookieName) throws Exception {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals(cookieName)) {
                String value = cookie.getValue();
                return value;
            }
        }
        return null;
    }

    /**
     * 校验是否是admin的token
     *
     * @param token
     * @throws Exception
     */
    public static void checkAdminToken(String token) throws Exception {
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken(token);
        if (SystemType.ADMIN != systemType) {
            throw new LoginTokenException("非管理后台token");
        }
    }

    /**
     * 校验是否是app的token
     *
     * @param token
     * @throws Exception
     */
    public static void checkAppToken(String token) throws Exception {
        SystemType systemType = SystemTypeUtil.getSystemTypeByToken(token);
        if (SystemType.APP != systemType) {
            throw new LoginTokenException("非移动端token");
        }
    }

}
