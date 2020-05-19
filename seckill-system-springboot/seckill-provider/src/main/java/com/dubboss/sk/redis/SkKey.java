package com.dubboss.sk.redis;

/**
 * @ClassName SkKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/19 23:00
 * @Vertion 1.0
 **/
public class SkKey extends BasePrefix{
    public SkKey(String prefix) {
        super(prefix);
    }

    public static SkKey isGoodsOver = new SkKey("go");

}
