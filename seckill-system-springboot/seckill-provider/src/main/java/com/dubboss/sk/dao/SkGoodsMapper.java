package com.dubboss.sk.dao;

import com.dubboss.sk.entity.Goods;
import com.dubboss.sk.entity.SkGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkGoods record);

    SkGoods selectByPrimaryKey(Long id);

    List<SkGoods> selectAll();

    int updateByPrimaryKey(SkGoods record);

    int reduceStock(SkGoods skGoods);
}