package io.geekidea.boot.config;

import io.geekidea.boot.auth.interceptor.*;
import io.geekidea.boot.config.properties.*;
import io.geekidea.boot.framework.filter.JsonRequestBodyFilter;
import io.geekidea.boot.framework.filter.TraceIdLogFilter;
import io.geekidea.boot.framework.interceptor.PageHelperClearInterceptor;
import io.geekidea.boot.framework.xss.XssFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/3/15
 **/
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private LoginAdminProperties loginAdminProperties;

    @Autowired
    private LoginAppProperties loginAppProperties;

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private XssProperties xssProperties;

    @Bean
    public ExcludePathInterceptor excludePathInterceptor() {
        return new ExcludePathInterceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public LoginAppInterceptor loginAppInterceptor() {
        return new LoginAppInterceptor();
    }

    @Bean
    public RefreshTokenInterceptor refreshTokenInterceptor() {
        return new RefreshTokenInterceptor();
    }

    @Bean
    public PageHelperClearInterceptor pageHelperClearInterceptor() {
        return new PageHelperClearInterceptor();
    }


    @Bean
    public FilterRegistrationBean traceIdLogFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TraceIdLogFilter traceIdLogFilter = new TraceIdLogFilter();
        filterRegistrationBean.setFilter(traceIdLogFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jsonRequestBodyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JsonRequestBodyFilter jsonRequestBodyFilter = new JsonRequestBodyFilter();
        filterRegistrationBean.setFilter(jsonRequestBodyFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    /**
     * XssFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setEnabled(xssProperties.isEnable());
        filterRegistrationBean.addUrlPatterns(xssProperties.getUrlPatterns());
        filterRegistrationBean.setOrder(xssProperties.getOrder());
        filterRegistrationBean.setAsyncSupported(xssProperties.isAsync());
        return filterRegistrationBean;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 虚拟目录文件映射
        registry.addResourceHandler(fileProperties.getAccessPath())
                .addResourceLocations("file:" + fileProperties.getUploadPath());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加入的顺序就是拦截器执行的顺序
        registry.addInterceptor(excludePathInterceptor());
        // token拦截器
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns("/admin/login", "/app/login");
        // 管理后台登录拦截器配置
        boolean enableAdminInterceptor = loginAdminProperties.isEnable();
        if (enableAdminInterceptor) {
            List<String> excludePaths = loginProperties.getExcludePaths();
            List<String> adminExcludePaths = loginAdminProperties.getExcludePaths();
            adminExcludePaths.addAll(excludePaths);
            registry.addInterceptor(loginInterceptor())
                    .addPathPatterns(loginAdminProperties.getIncludePaths())
                    .excludePathPatterns(adminExcludePaths);
        }
        // 移动端端登录拦截器配置
        boolean enableAppInterceptor = loginAppProperties.isEnable();
        if (enableAppInterceptor) {
            List<String> appIncludePaths = loginAppProperties.getIncludePaths();
            registry.addInterceptor(loginAppInterceptor()).addPathPatterns(appIncludePaths);
        }
        // 刷新token拦截器
        registry.addInterceptor(refreshTokenInterceptor());

        // TODO 公共请求拦截器，排除/admin和/app开头的所有请求
        // 分页缓存清除拦截器
        registry.addInterceptor(pageHelperClearInterceptor());
    }
}
