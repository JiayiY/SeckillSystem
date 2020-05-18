package com.dubboss.sk.service.impl;

import com.dubboss.sk.dao.GoodsMapper;
import com.dubboss.sk.entity.Goods;
import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkGoods;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.service.SkService;
import com.dubboss.sk.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SkServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:26
 * @Vertion 1.0
 **/
@Service
public class SkServiceImpl implements SkService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional
    @Override
    public OrderInfo sk(SkUser skUser, GoodsVo goodsVo) {
        SkGoods skGoods = new SkGoods();
        skGoods.setGoodsId(goodsVo.getId());
        // 秒杀 减库存，下订单，写入秒杀订单
        goodsService.reduceStock(skGoods);
        OrderInfo orderInfo  = orderService.createOrder(skUser,goodsVo);
        return orderInfo;
    }
}
