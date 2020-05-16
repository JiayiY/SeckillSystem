package com.dubboss.sk.dao;

import com.dubboss.sk.entity.Goods;
import com.dubboss.sk.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    List<GoodsVo> selectAllSkGoods();

    GoodsVo selectGoodsVoByGoodsId(long goodsId);

}