package com.dubboss.sk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboss.sk.dao.OrderInfoMapper;
import com.dubboss.sk.dao.SkOrderMapper;
import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.redis.OrderKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.OrderService;
import vo.GoodsVo;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:13
 * @Vertion 1.0
 **/
@Component
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Resource
    SkOrderMapper skOrderMapper;

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Resource
    RedisService redisService;

    @Override
    public SkOrder getSkOrderByUIdGId(Long UId, Long GId) {
        //return skOrderMapper.getSkOrderByUIdGId(UId, GId);
        return redisService.get(OrderKey.getSkOrderByUidGid, "" + UId + GId, SkOrder.class);

    }


    @Transactional
    @Override
    public OrderInfo createOrder(SkUser skUser, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodsCount(1);
        orderInfo.setOrderChannel((byte) 1);
        orderInfo.setStatus((byte) 0);
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setUserId(skUser.getId());
        // mybatis 把返回值放到对象中
        orderInfoMapper.insert(orderInfo);
        SkOrder skOrder = new SkOrder();
        skOrder.setGoodsId(goodsVo.getId());
        skOrder.setOrderId(orderInfo.getId());
        skOrder.setUserId(skUser.getId());
        skOrderMapper.insert(skOrder);
        redisService.set(OrderKey.getSkOrderByUidGid, "" + skUser.getId() + goodsVo.getId(), skOrder);
        return orderInfo;
    }

    @Override
    public OrderInfo getOrderById(long orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }
}
