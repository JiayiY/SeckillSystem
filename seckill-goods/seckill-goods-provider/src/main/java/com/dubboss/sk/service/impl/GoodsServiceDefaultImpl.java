package com.dubboss.sk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboss.sk.entity.GoodsVoOrder;
import com.dubboss.sk.entity.SkGoods;
import com.dubboss.sk.mapper.GoodsMapper;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.utils.ResultSk;
import com.dubboss.sk.utils.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName GoodsServiceDefaultImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/8/12 9:57
 * @Vertion 1.0
 **/
@Service
public class GoodsServiceDefaultImpl implements GoodsService {

    private static Logger logger = LoggerFactory.getLogger(GoodsServiceDefaultImpl.class);


    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public ResultSk<List<GoodsVoOrder>> getSkGoods() {

        ResultSk<List<GoodsVoOrder>> resultGeekQ = ResultSk.build();
        try {
            resultGeekQ.setData(goodsMapper.selectAllSkGoods());
        } catch (Exception e) {
            logger.error("获取订单数据失败！",e);
            resultGeekQ.withError(ResultStatus.ORDER_GET_FAIL);
        }
        return  resultGeekQ;
    }

    @Override
    public ResultSk<GoodsVoOrder> getGoodsVoByGoodsId(long goodsId) {
        ResultSk<GoodsVoOrder> resultGeekQ = ResultSk.build();

        try {
            GoodsVoOrder goodsVoOrder =  goodsMapper.selectGoodsVoByGoodsId(goodsId);
            resultGeekQ.setData(goodsVoOrder);
        } catch (Exception e) {
            logger.error("获取单个订单失败！",e);
            resultGeekQ.withError(ResultStatus.ORDER_GET_FAIL);
        }
        return resultGeekQ;
    }

    @Override
    public boolean reduceStock(GoodsVoOrder skGoods) {
        SkGoods g = new SkGoods();
        g.setGoodsId(skGoods.getId());
        int ret = goodsMapper.reduceStock(g);
        return ret > 0;
    }


}
