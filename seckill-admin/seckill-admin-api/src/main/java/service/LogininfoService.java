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
public interface LogininfoService {

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 检查是否有重复的用户名
     */
    boolean checkUsername(String name, int userType);

    /**
     * 用户登陆
     * @param name
     * @param password
     * @return
     */
    ResultS<LogInfo> login(String name, String password, int userType, String ip);

    /**
     * 是否有管理员
     * @return
     */
    boolean hasAdmin();

    /**
     * 创建默认的管理员
     */
    void createDefaultAdmin();

    /**
     * 查询用户的id和name
     * @param word
     * @param userType
     * @return
     */
    List<Map<String, Object>> autoComplate(String word, int userType);
}
