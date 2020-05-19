package com.dubboss.sk.service.impl;

import com.dubboss.sk.dao.GoodsMapper;
import com.dubboss.sk.entity.*;
import com.dubboss.sk.redis.SkKey;
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

    @Autowired
    private RedisService redisService;

    @Transactional
    @Override
    public OrderInfo sk(SkUser skUser, GoodsVo goodsVo) {
        SkGoods skGoods = new SkGoods();
        skGoods.setGoodsId(goodsVo.getId());
        // 秒杀 减库存，下订单，写入秒杀订单
        boolean success = goodsService.reduceStock(skGoods);
        if (success) {
            return orderService.createOrder(skUser, goodsVo);
        } else {
            //如果库存不存在则内存标记为true
            setGoodsOver(skGoods.getId());
            return null;
        }
    }



    @Override
    public long getSkResult(Long uId, long goodsId) {
        SkOrder skOrder = orderService.getSkOrderByUIdGId(uId, goodsId);
        if (skOrder != null) {
            return skOrder.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver){
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(long goodsId) {
        redisService.set(SkKey.isGoodsOver,""+goodsId,true);
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SkKey.isGoodsOver,""+goodsId);
    }
}
