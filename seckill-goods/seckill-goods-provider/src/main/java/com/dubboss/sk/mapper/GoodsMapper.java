package com.dubboss.sk.mapper;

import com.dubboss.sk.entity.GoodsVoOrder;
import com.dubboss.sk.entity.SkGoods;

import java.util.List;

/**
 * @ClassName GoodsMapper
 * @Description TODO
 * @Author yjy
 * @Date 2020/8/12 9:59
 * @Vertion 1.0
 **/
public interface GoodsMapper {
    List<GoodsVoOrder> selectAllSkGoods();

    GoodsVoOrder selectGoodsVoByGoodsId(long goodsId);

    int reduceStock(SkGoods skGoods);
}
