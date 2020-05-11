package com.dubboss.seckillprovider.redis;

/**
 * @ClassName UserKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 22:03
 * @Vertion 1.0
 **/
public class UserKey extends BasePrefix {

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("Name");

    private UserKey(String prefix) {
        super(prefix);
    }
}
