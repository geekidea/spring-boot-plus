package io.geekidea.boot.framework.util;

/**
 * @author geekidea
 * @date 2022/7/31
 **/

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
public class IpUtil {
    private static final String UNKNOWN = "unknown";
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";
    private static final String COMMA = ",";

    /**
     * 获取请求用户的IP地址
     *
     * @return
     */
    public static String getRequestIp() {
        HttpServletRequest request = HttpRequestUtil.getRequest();
        String ip = getRequestIp(request);
        if (ip.indexOf(COMMA) != -1) {
            String[] strings = ip.split(COMMA);
            ip = strings[0];
        }
        return ip;
    }

    /**
     * 获取请求用户的IP地址
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (IPV6_LOCAL.equals(ip)) {
            ip = getLocalhostIp();
        }
        return ip;
    }

    public static String getLocalhostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return null;
    }

}