package com.dubboss.seckilladminservice.mapper;


import entity.LogInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogInfoMapper {

	int deleteByPrimaryKey(Long id);

	int insert(LogInfo record);

	LogInfo selectByPrimaryKey(Long id);

	List<LogInfo> selectAll();

	int updateByPrimaryKey(LogInfo record);

	int getCountByNickname(@Param("nickname") String nickname,
                           @Param("userType") int userType);

	LogInfo getLoginInfoByNickname(@Param("nickname") String nickname,
						   @Param("userType") int userType);

	LogInfo login(@Param("name") String name,
                    @Param("password") String password, @Param("userType") int userType);

	List<Map<String, Object>> autoComplate(@Param("word") String word, @Param("userType") int userType);
}