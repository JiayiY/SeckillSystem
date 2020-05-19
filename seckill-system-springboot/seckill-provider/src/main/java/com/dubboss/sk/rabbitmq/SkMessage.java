package com.dubboss.sk.rabbitmq;

import com.dubboss.sk.entity.SkUser;

/**
 * @ClassName SkMessage
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/19 22:25
 * @Vertion 1.0
 **/
public class SkMessage {
    private SkUser skUser;
    private Long goodsId;

    public SkUser getSkUser() {
        return skUser;
    }

    public void setSkUser(SkUser skUser) {
        this.skUser = skUser;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
