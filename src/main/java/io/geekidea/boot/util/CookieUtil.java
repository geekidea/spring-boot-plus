package io.geekidea.boot.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2023/12/7
 **/
public class CookieUtil {

    /**
     * 输出token到cookie
     *
     * @param name
     * @param value
     * @param request
     * @param response
     */
    public static void addCookie(String name, String value, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     *
     * @param name
     * @param request
     * @param response
     */
    public static void deleteCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    /**
     * 通过cookieName从请求中获取cookieValue
     *
     * @param request
     * @param cookieName
     * @return
     * @throws Exception
     */
    public static String getCookieValueByName(HttpServletRequest request, String cookieName) throws Exception {
        Cookie[] cookies = request.getCookies();
        return getCookieValueByName(cookies, cookieName);
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

}
