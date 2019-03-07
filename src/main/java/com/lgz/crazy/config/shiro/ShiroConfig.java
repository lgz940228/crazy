package com.lgz.crazy.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lgz on 2019/3/5.
 */
@Configuration
public class ShiroConfig {

    /*@Autowired
    private CustomerUrlRewriteFilter customerUrlRewriteFilter;*/
   /* @Autowired
    private UrlPermissionsAuthorizationFilter urlPermissionsAuthorizationFilter;*/
    @Bean
    public ShiroFilterFactoryBean shirFilter() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String,Filter> customFilterMap = new LinkedHashMap<>();
        /*customFilterMap.put("customerUrlRewriteFilter",customerUrlRewriteFilter);*/
        customFilterMap.put("urlperms",new UrlPermissionsAuthorizationFilter());
        //customFilterMap.put("urlperms",urlPermissionsAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(customFilterMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 静态资源匿名访问
        /*filterChainDefinitionMap.put("/static*//**", "anon");*/
        // Swagger
        //filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        /*filterChainDefinitionMap.put("/webjars*//**", "anon");*/
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        //filterChainDefinitionMap.put("/v2/api-docs", "anon");
        // 登录匿名访问
        filterChainDefinitionMap.put("/api/user/login.do", "anon");
        filterChainDefinitionMap.put("/api/user/toLogin.do", "anon");
        filterChainDefinitionMap.put("/api/shior/login.do", "anon");
        filterChainDefinitionMap.put("/api/shior/403.do", "anon");
        filterChainDefinitionMap.put("/api/user/logout.do", "logout");
        // 其他路径均需要身份认证，一般位于最下面，优先级最低
        filterChainDefinitionMap.put("/api/**", "authc,urlperms");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 登录的路径
        shiroFilterFactoryBean.setLoginUrl("/api/user/toLogin.html");
        // 登录成功后跳转的路径
        //shiroFilterFactoryBean.setSuccessUrl("/api/index/index.do");
        // 验证失败后跳转的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/shior/403.html");
        /*Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        JsonFilter jsonFilter = new JsonFilter();
        filters.put("login", jsonFilter);
        shiroFilterFactoryBean.setFilters(filters);*/
        return shiroFilterFactoryBean;
    }
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /*@Bean
    public UrlPermissionsAuthorizationFilter urlPermissionsAuthorizationFilter(){
        return new UrlPermissionsAuthorizationFilter();
    }*/

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
       /* myShiroRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        myShiroRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        myShiroRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        myShiroRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        myShiroRealm.setAuthorizationCacheName("authorizationCache");*/
        return myShiroRealm;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //securityManager.setCacheManager(ehCacheManager());
        //配置记住我
        //securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param //securityManager
     * @return
     */
    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/

    /*@Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","/user/403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("exception");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }*/
}
