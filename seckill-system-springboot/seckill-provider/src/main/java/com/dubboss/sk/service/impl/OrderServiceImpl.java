package com.dubboss.sk.service.impl;

import com.dubboss.sk.dao.OrderInfoMapper;
import com.dubboss.sk.dao.SkOrderMapper;
import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:13
 * @Vertion 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    SkOrderMapper skOrderMapper;

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Override
    public SkOrder getSkOrderByUIdGId(Long UId, Long GId) {
        return skOrderMapper.getSkOrderByUIdGId(UId, GId);
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
        Long orderId = orderInfoMapper.insert(orderInfo);
        SkOrder skOrder = new SkOrder();
        skOrder.setGoodsId(goodsVo.getId());
        skOrder.setOrderId(orderId);
        skOrder.setUserId(skUser.getId());
        skOrderMapper.insert(skOrder);
        return orderInfo;
    }
}
