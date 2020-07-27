package com.dubboss.sk.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dubboss.sk.dao.GoodsMapper;
import com.dubboss.sk.dao.SkGoodsMapper;
import com.dubboss.sk.entity.SkGoods;
import org.springframework.stereotype.Component;
import service.GoodsService;
import vo.GoodsVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 0:02
 * @Vertion 1.0
 **/
@Service
@Component
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private SkGoodsMapper skGoodsMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> getSkGoods() {
        return goodsMapper.selectAllSkGoods();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.selectGoodsVoByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(SkGoods skGoods) {
       int res =  skGoodsMapper.reduceStock(skGoods);
       return res > 0;
    }
}
