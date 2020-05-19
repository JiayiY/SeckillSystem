package com.dubboss.sk.service.impl;


import com.dubboss.sk.dao.GoodsMapper;
import com.dubboss.sk.dao.SkGoodsMapper;
import com.dubboss.sk.entity.Goods;
import com.dubboss.sk.entity.SkGoods;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
