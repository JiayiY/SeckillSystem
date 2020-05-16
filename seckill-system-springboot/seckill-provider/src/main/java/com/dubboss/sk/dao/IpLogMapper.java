package com.dubboss.sk.dao;

import com.dubboss.sk.entity.IpLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IpLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IpLog record);

    IpLog selectByPrimaryKey(Long id);

    List<IpLog> selectAll();

    int updateByPrimaryKey(IpLog record);
}