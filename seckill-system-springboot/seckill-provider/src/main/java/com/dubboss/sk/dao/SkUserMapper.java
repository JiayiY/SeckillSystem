package com.dubboss.sk.dao;

import com.dubboss.sk.entity.SkUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkUser record);

    SkUser selectByPrimaryKey(Long id);

    List<SkUser> selectAll();

    int updateByPrimaryKey(SkUser record);

    SkUser selectByMobile(Long mobile);

}