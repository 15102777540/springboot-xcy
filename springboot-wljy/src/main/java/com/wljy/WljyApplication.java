package com.wljy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class WljyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WljyApplication.class,args);
    }
}
