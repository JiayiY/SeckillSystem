package com.dubboss.sk.redis;

/**
 * @ClassName SkKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/19 23:00
 * @Vertion 1.0
 **/
public class SkKey extends BasePrefix{
    public SkKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static SkKey isGoodsOver = new SkKey(0,"go");
    public static SkKey getSkPath = new SkKey(60,"skp");

    public static SkKey getMiaoshaVerifyCode = new SkKey(300, "vc");
}
