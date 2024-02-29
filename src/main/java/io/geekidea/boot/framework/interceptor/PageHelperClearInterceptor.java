package io.geekidea.boot.framework.interceptor;

import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;

/**
 * @author geekidea
 * @date 2022/10/12
 **/
public class PageHelperClearInterceptor extends BaseMethodInterceptor {

    @Override
    protected boolean preHandleMethod(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        try {
            PageHelper.clearPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
