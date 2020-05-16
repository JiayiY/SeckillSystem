package com.dubboss.sk.redis;

/**
 * @ClassName SkUserKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/14 22:45
 * @Vertion 1.0
 **/
public class SkUserKey extends BasePrefix {

    // 设置过期时间为两天
    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public static SkUserKey token = new SkUserKey(TOKEN_EXPIRE,"tk");


    public SkUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
