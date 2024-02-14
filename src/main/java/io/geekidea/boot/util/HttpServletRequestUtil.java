package io.geekidea.boot.util;

import io.geekidea.boot.common.constant.CommonConstant;
import io.geekidea.boot.common.constant.RequestHeadConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前请求的HttpServletRequest对象
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class HttpServletRequestUtil {

    /**
     * 获取HttpServletRequest对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 判断是否是Knife4j或是Swagger接口文档访问的请求
     *
     * @return
     */
    public static boolean isDocRequest() {
        return isDocRequest(getRequest());
    }

    /**
     * 判断是否是Knife4j或是Swagger接口文档访问的请求
     *
     * @param request
     * @return
     */
    public static boolean isDocRequest(HttpServletRequest request) {
        String requestOrigion = request.getHeader(RequestHeadConstant.REQUEST_ORIGION);
        String referer = request.getHeader(RequestHeadConstant.REFERER);
        boolean docRequest = false;
        if (CommonConstant.KNIFE4J.equals(requestOrigion)) {
            docRequest = true;
        } else if (StringUtils.isNotBlank(referer) && referer.contains(CommonConstant.SWAGGER_UI_PATH)) {
            docRequest = true;
        }
        return docRequest;
    }

}
