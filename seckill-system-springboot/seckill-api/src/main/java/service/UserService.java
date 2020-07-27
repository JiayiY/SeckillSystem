package service;

import com.dubboss.sk.entity.SkUser;
import vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:08
 * @Vertion 1.0
 **/
public interface UserService {

    String COOKIE_NAME_TOKEN = "token";

    /**
     * @param response
     * @param loginVo
     * @return boolean
     * @Author yangjiayi
     * @Description //
     * @Date 23:16 2020/5/12
     */
    boolean login(HttpServletResponse response, LoginVo loginVo);

    SkUser getByToken(HttpServletResponse response, String token);
}
