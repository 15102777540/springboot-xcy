package com.wljy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LogHandlerInterceptor logHandlerInterceptor() {
        return new LogHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //必须加上@Configuration注解，Spring才能统一管理当前的拦截器实例。
        //addPathPatterns("/api/**")配置拦截路径，其中/**表示当前目录以及所有子目录（递归），/*表示当前目录，不包括子目录。
        registry.addInterceptor(logHandlerInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/html/");
    }
}
