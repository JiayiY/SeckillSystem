package com.dubboss.sk.vo;


import com.dubboss.sk.entity.SkUser;

public class GoodsDetailVo {

    private int miaoshaStatus = 0;

    private int remainSeconds = 0;

    private GoodsVo goodsVo;

    private SkUser skUser;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public SkUser getSkUser() {
        return skUser;
    }

    public void setSkUser(SkUser skUser) {
        this.skUser = skUser;
    }
}
