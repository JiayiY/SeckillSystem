package com.dubboss.seckillconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.dubboss.seckillapi.entity.SkUser;
import com.dubboss.seckillapi.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:36
 * @Vertion 1.0
 **/
@RestController
public class LoginController {

    @Reference
    private UserService userService;

    @PostMapping("register")
    public String register(SkUser skUser) {
        if (userService.register(skUser)) {
            return skUser.toString();
        }
        return "false";
    }

    public static void main(String[] args) {
        SkUser skUser = new SkUser();
        skUser.setCreateTime(new Date(2020,5,10));
        skUser.setUsername("admin");
        skUser.setPassword("1021");
        System.out.println(JSON.toJSON(skUser));

    }

}
