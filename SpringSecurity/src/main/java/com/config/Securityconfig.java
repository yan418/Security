package com.config;

import com.config.component.LoginFailureHandler;
import com.modules.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * security的配置
 */
@Configuration
@EnableWebSecurity
// 使用Security内部的注解方式
@EnableGlobalMethodSecurity(securedEnabled=true)
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

//    @Autowired
//    private LoginFailureHandler loginFailureHandler;

    /**
     * 定义请求授权规则
     *    denyAll()   无条件拒绝所有请求
     *    permitAll() 无条件允许请求
     *    access()    表达式计算结果为true ,就允许访问
     *    anonymous() 匿名可以访问
     *    authenticated()  认证后访问
     *    hasIpAddress()    请求来自指定IP下，可以访问
     *    hasRole()   定义角色
     *    rememberMe()通过rememberMe认证，可以访问
     *    hasAnyAuthority() 具备某个权限，可以访问
     *    hasAuthority()    定义权限
     *
     *    anyRequest() 其它资源
     *    ...
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 首页所有人可以访问
        http.authorizeRequests()
                .antMatchers("/login","/css/*","/js/*","/img/*").permitAll()
                .antMatchers("/","/home","/doLogin","/logine","/success").permitAll()
                .antMatchers("/level1/**").hasRole("manager")
                .antMatchers("/level2/**").hasRole("emp")
                .antMatchers("/level3/**").hasRole("boss")
                .anyRequest().authenticated()
                ;

        //关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        http.csrf().disable();

        // 登录   loginPage 设置登录页  loginProcessingUrl 设置表单请求   // 登录认证成功后默认转跳的路径
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successForwardUrl("/success")
                .failureUrl("/logine")
//  成功        .defaultSuccessUrl("/home")
//  失败        .failureUrl("/login")
//              .successHandler(loginSuccessHandler)
//              .failureHandler(loginFailureHandler)
                ;

        // 注销  logoutSuccessUrl 注销成功跳转首页
        http.logout().logoutSuccessUrl("/home")
                      // 是否清空 session
                     .invalidateHttpSession(true)
                     .logoutSuccessUrl("/");

        // 记住我  默认记录14天
        http.rememberMe()
                .rememberMeParameter("remember")
                 // 有效时间：单位s
                .tokenValiditySeconds(60);

    }

    /**
     * 认证数据的来源
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证  从数据库中获取
        //auth.authenticationProvider(loginValidateAuthenticationProvider);
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());


        //在内存中认证  jdbc中去数据库拿数据
//        auth.inMemoryAuthentication()
//                //加密  官方推荐的是使用bcrypt加密方式
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("yan").password(new BCryptPasswordEncoder().encode("123")).roles("manager")
//                .and()
//                .withUser("q").password(new BCryptPasswordEncoder().encode("123")).roles("emp")
//                .and()
//                .withUser("w").password(new BCryptPasswordEncoder().encode("123")).roles("boss","manager")
//                .and()
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("manager","emp","boss");

    }

    /**
     * BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
