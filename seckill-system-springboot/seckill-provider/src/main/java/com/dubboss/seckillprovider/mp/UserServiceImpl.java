package com.dubboss.seckillprovider.mp;

import com.dubboss.seckillapi.dao.SkUserMapper;
import com.dubboss.seckillapi.entity.SkUser;
import com.dubboss.seckillapi.exception.GlobalException;
import com.dubboss.seckillapi.service.UserService;
import com.dubboss.seckillapi.vo.LoginVo;
import com.dubboss.seckillprovider.mp.service.RedisService;
import com.dubboss.seckillprovider.redis.SkUserKey;
import com.dubboss.seckillprovider.util.MD5Util;
import com.dubboss.seckillprovider.util.UUIDUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.dubboss.seckillapi.enums.ResultStatus.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:17
 * @Vertion 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private SkUserMapper skUserMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            //交给异常处理器处理
            throw new GlobalException(SYSTEM_ERROR);
        }
        SkUser skUser = skUserMapper.selectByMobile(Long.parseLong(loginVo.getMobile()));
        if (!skUser.getPassword().equals(MD5Util.formPwdToDBPwd(loginVo.getPassword(), skUser.getSalt()))) {
            throw new GlobalException(PASSWORD_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.generateUuid();
        redisService.set(SkUserKey.token, token, skUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        // 设置cookie有效时间
        cookie.setMaxAge(SkUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    @Override
    public SkUser getByToken(String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        SkUser skUser = redisService.get(SkUserKey.token, token, SkUser.class);
        return skUser;
    }
}
