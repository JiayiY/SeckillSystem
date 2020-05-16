package com.dubboss.sk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@MapperScan("com.dubboss.sk.dao")
public class SeckillConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillConsumerApplication.class, args);
    }

}
