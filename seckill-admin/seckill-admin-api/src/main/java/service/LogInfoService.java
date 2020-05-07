package service;

import entity.LogInfo;
import resultbean.ResultS;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LogininfoService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 22:29
 * @Vertion 1.0
 **/
public interface LogInfoService {

    /**
     * @param username
     * @param password
     * @return void
     * @Author yangjiayi
     * @Description //注册
     * @Date 23:39 2020/5/7
     */
    void register(String username, String password);

    /**
     * @param name
     * @param userType
     * @return boolean
     * @Author yangjiayi
     * @Description //检查是否有重复的用户名
     * @Date 23:39 2020/5/7
     */
    boolean checkUsername(String name, int userType);

    /**
     * @param name
     * @param password
     * @param userType
     * @return entity.LogInfo
     * @Author yangjiayi
     * @Description //用户登陆
     * @Date 23:39 2020/5/7
     */
    LogInfo login(String name, String password, int userType);

    /**
     * @param
     * @return boolean
     * @Author yangjiayi
     * @Description //是否有管理员
     * @Date 23:39 2020/5/7
     */
    boolean hasAdmin();

    /**
     * @param
     * @return void
     * @Author yangjiayi
     * @Description //创建默认的管理员
     * @Date 23:38 2020/5/7
     */
    void createDefaultAdmin();

    /**
     * @param word
     * @param userType
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author yangjiayi
     * @Description //查询用户的id和name
     * @Date 23:40 2020/5/7
     */
    List<Map<String, Object>> autoComplate(String word, int userType);
}
