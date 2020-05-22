package com.dubboss.sk.access;

import com.dubboss.sk.entity.SkUser;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/22 21:51
 * @Vertion 1.0
 **/
public class UserContext {

    private static ThreadLocal<SkUser> userHolder = new ThreadLocal<SkUser>();

    public static SkUser getUser() {
        return userHolder.get();
    }

    public static void setUser(SkUser skUser) {
        userHolder.set(skUser);
    }

}
