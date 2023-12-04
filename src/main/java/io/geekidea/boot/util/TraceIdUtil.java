package io.geekidea.boot.util;

import io.geekidea.boot.common.constant.CommonConstant;
import org.slf4j.MDC;

/**
 * @author geekidea
 * @date 2023/11/26
 **/
public class TraceIdUtil {

    /**
     * 获取当前请求日志链路ID
     *
     * @return
     */
    public static String getTraceId() {
        return MDC.get(CommonConstant.TRACE_ID);
    }

}
