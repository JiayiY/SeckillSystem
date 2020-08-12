package com.dubboss.sk.service;


import com.dubboss.sk.entity.GoodsVoOrder;
import com.dubboss.sk.utils.ResultSk;


import java.util.List;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 0:01
 * @Vertion 1.0
 **/
public interface GoodsService {
    ResultSk<List<GoodsVoOrder>> getSkGoods();

    ResultSk<GoodsVoOrder> getGoodsVoByGoodsId(long goodsId);

    boolean reduceStock(GoodsVoOrder skGoods);
}
