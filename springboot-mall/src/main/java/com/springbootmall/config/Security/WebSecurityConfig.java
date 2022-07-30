package com.springbootmall.config.Security;

import com.springbootmall.config.Security.bean.SecurityProperties;
import com.springbootmall.filters.JwtAuthenticationTokenFilter;
import com.springbootmall.service.OnlineUserService;
import com.springbootmall.service.SelfUserDetailsService;
import com.springbootmall.service.UserCacheClean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private  TokenProvider tokenProvider;
    @Autowired
    private SecurityProperties properties;
    @Autowired
    private OnlineUserService onlineUserService;
    @Autowired
    private UserCacheClean userCacheClean;

    @Autowired
    JwtAuthenticationEntryPoint authenticationEntryPoint;//未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    JwtAuthenticationSuccessHandler authenticationSuccessHandler; //登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    JwtAuthenticationFailureHandler authenticationFailureHandler; //登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    JwtLogoutSuccessHandler logoutSuccessHandler;//注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    JwtAccessDeniedHandler accessDeniedHandler;//无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    SelfUserDetailsService userDetailsService; // 自定义user

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; // JWT 拦截器

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // 去除 ROLE_ 前缀
        return new GrantedAuthorityDefaults("");
    }

    /*
    如果密码前缀不加 {bcrypt} 就需要用到这个配置
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
//        auth.authenticationProvider(provider);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            // 去掉 CSRF
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()

                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
                // 允许跨域的OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .antMatchers("/test/register")//允许匿名注册
                .permitAll()
                // 所有请求都需要认证
                .anyRequest()
                //.access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
                .access("true")
                .and()
                .formLogin()  //开启登录, 定义当需要用户登录时候，转到的登录页面
//                .loginPage("/test/login.html")
               //.loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()
                .and()
                .logout()//默认注销行为为logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll().and()
                .apply(securityConfigurerAdapter());

        ;

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(1000);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        //http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }

    private TokenConfigurer securityConfigurerAdapter() {
        return new TokenConfigurer(tokenProvider, properties, onlineUserService, userCacheClean);
    }

}
