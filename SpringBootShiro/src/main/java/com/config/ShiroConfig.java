package com.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.config.component.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Shiro 配置类
 */
@Configuration
public class ShiroConfig {

   /**
    * 第三步 shiro工厂bean配置 ShiroFilterFactoryBean
    * 添加shiro的内置过滤器
    * anon：无须认证就可以访问
    * authc：必须认证了才可以访问
    * perms：拥有对某个资源的权限才能访问
    * roles:拥有某个角色才可以访问
    * /admins/**=auth               # 表示该 uri 需要认证才能访问
    * /admins/**=authcBasic         # 表示该 uri 需要 httpBasic 认证
    * /admins/**=perms[user:add:*]  # 表示该 uri 需要认证用户拥有 user:add:* 权限才能访问
    * /admins/**=port[8081]         # 表示该 uri 需要使用 8081 端口
    * /admins/**=rest[user]         # 相当于 /admins/**=perms[user:method]，其中，method 表示  get、post、delete 等
    * /admins/**=roles[admin]       # 表示该 uri 需要认证用户拥有 admin 角色才能访问
    * /admins/**=ssl                # 表示该 uri 需要使用 https 协议
    * /admins/**=user               # 表示该 uri 需要认证或通过记住我认证才能访问
    * /logout=logout                # 表示注销,可以当作固定配置
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/css/*","anon");
        filterMap.put("/js/*","anon");
        filterMap.put("/img/*","anon");
        filterMap.put("/","anon");
        filterMap.put("/home","anon");
        filterMap.put("/doLogin","anon");
        //授权
        filterMap.put("/level1/*","roles[ROLE_manager]");
        filterMap.put("/level2/*","roles[ROLE_emp]");
        //filterMap.put("/level2/*","perms[emp:delete,emp:edit]");
        filterMap.put("/level3/*","roles[ROLE_boss]");
        filterMap.put("/logout","logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置到未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unverified");

        return shiroFilterFactoryBean;
    }


    // 第二步 配置shiro安全管理器 DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    // 第一步  自定义Realm，需要自定义
    // 参数 name名字，userRealm是需要传入 第二部的 当作参数传入
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(@Qualifier("matcher") HashedCredentialsMatcher matcher,@Qualifier("ehCache") EhCacheManager ehCache){
        //加入密码管理
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(matcher);
        realm.setCacheManager(ehCache);
        realm.setCachingEnabled(true);
        return realm;
    }


    // Shiro 自带加密器
    @Bean(name = "matcher")
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法使用md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，2表示md5加密两次
        credentialsMatcher.setHashIterations(2);
        //credentialsMatcher.setStoredCredentialsHexEncoded(false);
        return credentialsMatcher;
    }

    //shiro 整合thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


    /**
     * 缓存管理器
     * @return ehCacheManager
     */
    @Bean(name = "ehCache")
    public EhCacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }




}
