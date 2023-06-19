package io.geekidea.boot.config;

import io.geekidea.boot.auth.interceptor.LoginInterceptor;
import io.geekidea.boot.auth.interceptor.RefreshTokenInterceptor;
import io.geekidea.boot.config.properties.FileProperties;
import io.geekidea.boot.config.properties.LoginProperties;
import io.geekidea.boot.framework.filter.JsonRequestBodyFilter;
import io.geekidea.boot.framework.interceptor.PageHelperClearInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekidea
 * @date 2022/3/15
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private FileProperties fileProperties;

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
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
    public FilterRegistrationBean jsonRequestBodyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JsonRequestBodyFilter jsonRequestBodyFilter = new JsonRequestBodyFilter();
        filterRegistrationBean.setFilter(jsonRequestBodyFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
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
        boolean enableLoginInterceptor = loginProperties.isEnable();
        if (enableLoginInterceptor) {
            List<String> excludePaths = loginProperties.getExcludePaths();
            // 登录验证拦截器
            registry.addInterceptor(loginInterceptor()).excludePathPatterns(excludePaths);
            // 刷新token拦截器
            registry.addInterceptor(refreshTokenInterceptor());

        }
        // 分页缓存清除拦截器
        registry.addInterceptor(pageHelperClearInterceptor());
    }
}
