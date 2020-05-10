package com.dubboss.seckillprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dubboss.seckillapi.dao")
public class SeckillProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillProviderApplication.class, args);
    }

}
