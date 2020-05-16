package com.dubboss.sk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dubboss.sk.dao")
public class SeckillProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillProviderApplication.class, args);
    }

}
