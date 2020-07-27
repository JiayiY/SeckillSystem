package com.dubboss.sk.dao;

import com.dubboss.sk.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import vo.GoodsVo;

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