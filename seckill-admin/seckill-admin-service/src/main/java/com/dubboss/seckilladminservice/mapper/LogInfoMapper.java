package com.dubboss.seckilladminservice.mapper;


import entity.LogInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogInfoMapper {

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:40 2020/5/7
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:40 2020/5/7
     * @param record
     * @return int
     */
    int insert(LogInfo record);

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:40 2020/5/7
     * @param id
     * @return entity.LogInfo
     */
    LogInfo selectByPrimaryKey(Long id);

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:40 2020/5/7
     * @param
     * @return java.util.List<entity.LogInfo>
     */
    List<LogInfo> selectAll();

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:41 2020/5/7
     * @param record
     * @return int
     */
    int updateByPrimaryKey(LogInfo record);

    /**
     * @Author yangjiayi
     * @Description //
     * @Date 23:41 2020/5/7
     * @param nickname
     * @param userType
     * @return int
     */
    int getCountByNickname(@Param("nickname") String nickname, @Param("userType") int userType);

    /**
     * @Author yangjiayi
     * @Description //TODO
     * @Date 23:41 2020/5/7
     * @param nickname
     * @param userType
     * @return entity.LogInfo
     */
    LogInfo getLoginInfoByNickname(@Param("nickname") String nickname, @Param("userType") int userType);

    /**
     * @Author yangjiayi
     * @Description //TODO
     * @Date 23:41 2020/5/7
     * @param name
     * @param password
     * @param userType
     * @return entity.LogInfo
     */
    LogInfo login(@Param("name") String name, @Param("password") String password, @Param("userType") int userType);

    /**
     * @Author yangjiayi
     * @Description //TODO
     * @Date 23:41 2020/5/7
     * @param word
     * @param userType
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> autoComplate(@Param("word") String word, @Param("userType") int userType);
}