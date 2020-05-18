package com.dubboss.sk.redis;

/**
 * @ClassName OderKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 22:04
 * @Vertion 1.0
 **/
public class OrderKey extends BasePrefix{
    public OrderKey( String prefix) {
        super( prefix);
    }
    public static OrderKey getSkOrderByUidGid = new OrderKey("skougid");
}
