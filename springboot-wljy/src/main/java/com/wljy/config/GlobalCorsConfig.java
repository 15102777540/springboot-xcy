package com.wljy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局跨域配置
 * Created by macro on 2019/7/27.
 */
@Configuration
public class GlobalCorsConfig {

    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //允许所有域名进行跨域调用
        //当allowCredentials为真时，allowedorigin不能包含特殊值"*"，因为不能在"访问-控制-起源“响应头中设置该值。要允许凭证到一组起源，显示地列出它们，或者考虑使用"allowedOriginPatterns”代替。
        config.addAllowedOriginPattern("*");
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
