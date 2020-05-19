package com.dubboss.sk.redis;

/**
 * @ClassName GoodsKey
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/19 22:12
 * @Vertion 1.0
 **/
public class GoodsKey extends BasePrefix{

    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getSkGoodsStock = new GoodsKey(0,"gs");

}
