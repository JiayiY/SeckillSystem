package com.dubboss.sk.service;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.vo.GoodsVo;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:13
 * @Vertion 1.0
 **/
public interface OrderService {
    SkOrder getSkOrderByUIdGId(Long UId, Long GId);

    OrderInfo createOrder(SkUser skUser, GoodsVo goodsVo);

    OrderInfo getOrderById(long orderId);
}
