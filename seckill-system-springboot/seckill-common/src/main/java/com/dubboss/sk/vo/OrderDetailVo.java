package com.dubboss.sk.vo;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkUser;

/**
 * @ClassName OrderDetailVo
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/17 18:15
 * @Vertion 1.0
 **/
public class OrderDetailVo {
    private GoodsVo goodsVo;
    private OrderInfo orderInfo;

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
