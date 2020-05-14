package com.dubboss.seckillapi.dao;

import com.dubboss.seckillapi.entity.SkUser;
import java.util.List;

public interface SkUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkUser record);

    SkUser selectByPrimaryKey(Long id);

    List<SkUser> selectAll();

    int updateByPrimaryKey(SkUser record);

    SkUser selectByMobile(Long mobile);

}