package com.springbootmall.config.Security;

import com.springbootmall.config.Security.bean.LoginProperties;
import com.springbootmall.config.Security.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix="login")
    public LoginProperties getLoginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties getSecurityProperties(){
        return  new SecurityProperties();
    }
}
