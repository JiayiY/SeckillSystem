package com.dubboss.seckillprovider.redis;

/**
 * @ClassName OderKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 22:04
 * @Vertion 1.0
 **/
public class OrderKey extends BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
