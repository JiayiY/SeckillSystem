package com.dubboss.sk;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkApplication.class, args);
    }

}
