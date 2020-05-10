package com.dubboss.seckillapi.service;

import com.dubboss.seckillapi.entity.SkUser;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:08
 * @Vertion 1.0
 **/
public interface UserService {

    /**
     * @param skUser
     * @return void
     * @Author yangjiayi
     * @Description //注册用户
     * @Date 17:13 2020/5/10
     */
    boolean register(SkUser skUser);

    /**
     * @param username
     * @return com.dubboss.seckillapi.entity.SkUser
     * @Author yangjiayi
     * @Description //获取用户信息
     * @Date 17:12 2020/5/10
     */
    SkUser findByUsername(String username);
}
