package com.dubboss.seckillapi.dao;

import com.dubboss.seckillapi.entity.SkUser;
import java.util.List;

public interface SkUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SkUser record);

    SkUser selectByPrimaryKey(Integer id);

    List<SkUser> selectAll();

    int updateByPrimaryKey(SkUser record);

    SkUser selectByUsername(String username);
}