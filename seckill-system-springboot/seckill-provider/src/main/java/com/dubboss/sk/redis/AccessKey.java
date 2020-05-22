package com.dubboss.sk.redis;

/**
 * @ClassName AccessKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/22 21:26
 * @Vertion 1.0
 **/
public class AccessKey extends BasePrefix{
    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds){
        return new AccessKey(expireSeconds,"access");
    }
}
