package com.dubboss.sk;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubboConfiguration
public class SeckillConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillConsumerApplication.class, args);
    }

}
