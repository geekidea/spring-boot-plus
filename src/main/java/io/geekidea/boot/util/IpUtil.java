package io.geekidea.boot.util;

/**
 * @author geekidea
 * @date 2022/7/31
 **/

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;


@Slf4j
public class IpUtil {
    private static final String UNKNOWN = "unknown";
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";
    private static final String COMMA = ",";
    private static final String FE = "fe";
    private static final String[] IP_HEADS = new String[]{"x-forwarded-for", "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP"};

    /**
     * 获取请求用户的IP地址
     *
     * @return
     */
    public static String getRequestIp() {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
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
        String ip = null;
        for (String ipHead : IP_HEADS) {
            ip = request.getHeader(ipHead);
            if (StringUtils.isNotBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
                break;
            }
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
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
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getLocalhostIpList() {
        List<String> list = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (ObjectUtils.isEmpty(networkInterfaces)) {
                return null;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!isValidateNetworkInterface(networkInterface)) {
                    continue;
                }
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!isValidateAddress(inetAddress)) {
                        continue;
                    }
                    list.add(inetAddress.getHostAddress());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 是否是有效的网络
     *
     * @param networkInterface
     * @return
     */
    private static boolean isValidateNetworkInterface(NetworkInterface networkInterface) {
        try {
            return !networkInterface.isLoopback() && !networkInterface.isPointToPoint() && networkInterface.isUp() && !networkInterface.isVirtual();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否是有效的IP
     *
     * @param inetAddress
     * @return
     */
    private static boolean isValidateAddress(InetAddress inetAddress) {
        try {
            String hostAddress = inetAddress.getHostAddress();
            // 排除IPV6地址
            if (hostAddress.startsWith(FE)) {
                return false;
            }
            if (!isValidIpV4(hostAddress)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 校验是否是有效的IPV4
     *
     * @param ipAddress
     * @return
     */
    public static boolean isValidIpV4(String ipAddress) {
        return Pattern.matches("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$", ipAddress);
    }

}