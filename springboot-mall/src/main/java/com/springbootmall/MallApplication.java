package com.springbootmall;

import com.springbootmall.util.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@MapperScan("com.springbootmall.mapper")
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class,args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @GetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }
}
