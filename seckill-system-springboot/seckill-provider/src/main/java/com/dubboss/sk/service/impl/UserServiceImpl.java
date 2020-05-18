package com.dubboss.sk.service.impl;

import com.dubboss.sk.dao.SkUserMapper;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.exception.GlobalException;
import com.dubboss.sk.redis.UserKey;
import com.dubboss.sk.service.UserService;
import com.dubboss.sk.vo.LoginVo;
import com.dubboss.sk.redis.SkUserKey;
import com.dubboss.sk.util.MD5Util;
import com.dubboss.sk.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.dubboss.sk.enums.ResultStatus.*;

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

    @Resource
    private SkUserMapper skUserMapper;

    @Resource
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
        String token = UUIDUtil.generateUuid();
        addCookie(response, token, skUser);
        return true;
    }

    @Override
    public SkUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SkUser skUser = redisService.get(SkUserKey.token, token, SkUser.class);
        // 延长有效期
        if (skUser != null) {
            addCookie(response, token, skUser);
        }
        return skUser;
    }

    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    private boolean updatePassword(String token, Long id, String formPass) {
        //取user
        SkUser skUser = getById(id);
        if (skUser == null) {
            throw new GlobalException(MOBILE_NOT_EXIST);
        }
        //更新数据库
        SkUser toBeUpdate = new SkUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPwdToDBPwd(formPass, skUser.getSalt()));
        skUserMapper.updatePwd(toBeUpdate);
        //处理缓存
        redisService.delete(UserKey.getById, "" + id);
        skUser.setPassword(toBeUpdate.getPassword());
        redisService.set(SkUserKey.token, token, skUser);
        return true;
    }

    private SkUser getById(Long id) {
        // 取缓存
        SkUser skUser = redisService.get(UserKey.getById, "" + id, SkUser.class);
        if (skUser != null) {
            return skUser;
        }
        skUser = skUserMapper.selectByPrimaryKey(id);
        // 放入缓存
        if (skUser != null) {
            redisService.set(UserKey.getById, "" + id, skUser);
        }
        return skUser;
    }


    /**
     * @param response
     * @param skUser
     * @return void
     * @Author yangjiayi
     * @Description // 生成cookie
     * @Date 23:31 2020/5/15
     */
    private void addCookie(HttpServletResponse response, String token, SkUser skUser) {
        redisService.set(SkUserKey.token, token, skUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        // 设置cookie有效时间
        cookie.setMaxAge(SkUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
