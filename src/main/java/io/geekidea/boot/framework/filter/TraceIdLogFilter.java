package io.geekidea.boot.framework.filter;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.geekidea.boot.framework.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author geekidea
 * @date 2023/6/22
 **/
@Slf4j
public class TraceIdLogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 设置日志链路ID
            String traceId = IdWorker.getIdStr();
            try {
                MDC.put(CommonConstant.TRACE_ID, traceId);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            // 执行
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        } finally {
            // 移除日志链路ID
            try {
                MDC.remove(CommonConstant.TRACE_ID);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
