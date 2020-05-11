package com.dubboss.seckillprovider.redis;

/**
 * @ClassName KeyPrefix
 * @Description 对各个模块做区分
 * @Author yjy
 * @Date 2020/5/11 21:53
 * @Vertion 1.0
 **/
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
