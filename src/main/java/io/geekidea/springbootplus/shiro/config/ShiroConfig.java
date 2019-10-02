/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.shiro.config;

import com.alibaba.fastjson.JSON;
import io.geekidea.springbootplus.shiro.cache.LoginRedisService;
import io.geekidea.springbootplus.shiro.exception.ShiroConfigException;
import io.geekidea.springbootplus.shiro.jwt.JwtCredentialsMatcher;
import io.geekidea.springbootplus.shiro.jwt.JwtFilter;
import io.geekidea.springbootplus.shiro.jwt.JwtProperties;
import io.geekidea.springbootplus.shiro.jwt.JwtRealm;
import io.geekidea.springbootplus.shiro.service.LoginService;
import io.geekidea.springbootplus.util.IniUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置
 * https://shiro.apache.org/spring.html
 * https://shiro.apache.org/spring-boot.html
 *
 * @author geekidea
 * @date 2019-09-27
 * @since 1.3.0.RELEASE
 **/
@Slf4j
@Configuration
public class ShiroConfig {

    /**
     * JWT过滤器名称
     */
    private static final String JWT_FILTER_NAME = "jwtFilter";
    /**
     * Shiro过滤器名称
     */
    private static final String SHIRO_FILTER_NAME = "shiroFilter";


    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new JwtCredentialsMatcher();
    }

    /**
     * JWT数据源验证
     *
     * @return
     */
    @Bean
    public JwtRealm jwtRealm(LoginRedisService loginRedisService) {
        JwtRealm jwtRealm = new JwtRealm(loginRedisService);
        jwtRealm.setCachingEnabled(false);
        jwtRealm.setCredentialsMatcher(credentialsMatcher());
        return jwtRealm;
    }

    /**
     * 禁用session
     *
     * @return
     */
    @Bean
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }

    @Bean
    public SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    @Bean
    public DefaultSubjectDAO subjectDAO() {
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        defaultSubjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        return defaultSubjectDAO;
    }

    /**
     * 安全管理器配置
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(LoginRedisService loginRedisService) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm(loginRedisService));
        securityManager.setSubjectDAO(subjectDAO());
        securityManager.setSessionManager(sessionManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean配置
     *
     * @param securityManager
     * @param loginRedisService
     * @param shiroProperties
     * @param jwtProperties
     * @return
     */
    @Bean(SHIRO_FILTER_NAME)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         LoginService loginService,
                                                         LoginRedisService loginRedisService,
                                                         ShiroProperties shiroProperties,
                                                         JwtProperties jwtProperties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashedMap();
        filterMap.put(JWT_FILTER_NAME, new JwtFilter(loginService, loginRedisService, jwtProperties));
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String, String> filterChainMap = shiroFilterChainDefinition(shiroProperties).getFilterChainMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Shiro路径权限配置
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(ShiroProperties shiroProperties) {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 获取ini格式配置
        String definitions = shiroProperties.getFilterChainDefinitions();
        if (StringUtils.isNotBlank(definitions)) {
            Map<String, String> section = IniUtil.parseIni(definitions);
            log.debug("definitions:{}", JSON.toJSONString(section));
            for (Map.Entry<String, String> entry : section.entrySet()) {
                chainDefinition.addPathDefinition(entry.getKey(), entry.getValue());
            }
        }

        // 获取自定义权限路径配置集合
        List<ShiroPermissionConfig> permissionConfigs = shiroProperties.getPermissionConfig();
        log.debug("permissionConfigs:{}", JSON.toJSONString(permissionConfigs));
        if (CollectionUtils.isNotEmpty(permissionConfigs)) {
            for (ShiroPermissionConfig permissionConfig : permissionConfigs) {
                String url = permissionConfig.getUrl();
                String[] urls = permissionConfig.getUrls();
                String permission = permissionConfig.getPermission();
                if (StringUtils.isBlank(url) && ArrayUtils.isEmpty(urls)) {
                    throw new ShiroConfigException("shiro permission config 路径配置不能为空");
                }
                if (StringUtils.isBlank(permission)) {
                    throw new ShiroConfigException("shiro permission config permission不能为空");
                }

                if (StringUtils.isNotBlank(url)) {
                    chainDefinition.addPathDefinition(url, permission);
                }
                if (ArrayUtils.isNotEmpty(urls)) {
                    for (String string : urls) {
                        chainDefinition.addPathDefinition(string, permission);
                    }
                }
            }
        }
        // 最后一个设置为JWTFilter
        chainDefinition.addPathDefinition("/**", JWT_FILTER_NAME);

        Map<String, String> filterChainMap = chainDefinition.getFilterChainMap();
        log.debug("filterChainMap:{}", JSON.toJSONString(filterChainMap));

        return chainDefinition;
    }


    /**
     * ShiroFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER_NAME);
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        return filterRegistrationBean;
    }

    @Bean
    public Authenticator authenticator(LoginRedisService loginRedisService) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Arrays.asList(jwtRealm(loginRedisService)));
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }


    /**
     * Enabling Shiro Annotations
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * depends-on lifecycleBeanPostProcessor
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
