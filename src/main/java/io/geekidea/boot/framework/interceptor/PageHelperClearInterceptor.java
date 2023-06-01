package io.geekidea.boot.framework.interceptor;

import com.github.pagehelper.PageHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekidea
 * @date 2022/10/12
 **/
public class PageHelperClearInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 如果访问的不是控制器,则跳出
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
            PageHelper.clearPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
