package com.code.classsystem.shiro.config;


import com.code.classsystem.shiro.SessionManager.MySessionManager;
import com.code.classsystem.shiro.filter.LoginFilter;
import com.code.classsystem.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Autowired
    private AuthorUrlConfig authorUrlConfig;
    @Autowired
    private LoginFilter loginFilter;


//    @Bean(name = "userRealm")
//    public UserRealm userRealm() {
//        UserRealm realm = new UserRealm();
//        return realm;
//    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public FilterRegistrationBean registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(loginFilter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * @param securityManager
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录Url,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(authorUrlConfig.getLoginUrl());
//         设置登录成功要跳转的Url
//        shiroFilterFactoryBean.setSuccessUrl(authorUrlConfig.getHome());

        /**
         * 添加自定义拦截器
         */

        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("authc", loginFilter);
        //访问权限配置
//        filtersMap.put("requestURL", new FormLoginFilter(authorUrlConfig));
        shiroFilterFactoryBean.setFilters(filtersMap);

        /**
         * 拦截器
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * author：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //登录地址
        filterChainDefinitionMap.put(authorUrlConfig.getLoginUrl(), "anon");
//        登录验证地址
        filterChainDefinitionMap.put(authorUrlConfig.getDoLoginUrl(), "anon");
        //退出地址
        filterChainDefinitionMap.put(authorUrlConfig.getLogoutUrl(), "logout");
        //swaggwer UI放行
        allowSwaggerUIRequest(filterChainDefinitionMap);
        //白名单
        String[] whitelist = authorUrlConfig.getWhiteUrlArray();
        for (String url : whitelist) {
            filterChainDefinitionMap.put(url, "anon");
        }
        //拦截其他所有
        filterChainDefinitionMap.put("/**", "authc");

        // 注册
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return shiroFilterFactoryBean;
    }
    /**
     * 自定义sessionManager
     *
     * @return SessionManager
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }


    /**
     * swagger 请求放行
     * @param filterChainDefinitionMap
     */
    private void allowSwaggerUIRequest(Map<String, String> filterChainDefinitionMap) {
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
    }




    /**
     *
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     *  配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
