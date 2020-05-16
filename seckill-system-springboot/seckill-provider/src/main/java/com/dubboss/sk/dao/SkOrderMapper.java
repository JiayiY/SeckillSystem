package com.dubboss.sk.dao;

import com.dubboss.sk.entity.SkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkOrder record);

    SkOrder selectByPrimaryKey(Long id);

    List<SkOrder> selectAll();

    int updateByPrimaryKey(SkOrder record);

    SkOrder getSkOrderByUIdGId(@Param("uId") Long uId, @Param("gId")Long gId);
}