package com.dubboss.seckilladminservice.service.impl;

import com.dubboss.seckilladminservice.mapper.LogInfoMapper;
import entity.LogInfo;
import enums.Constants;
import enums.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resultbean.ResultS;
import service.LogInfoService;
import util.MD5Utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LogininfoServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 22:43
 * @Vertion 1.0
 **/
@Service("LogInfoServiceImpl")
public class LogInfoServiceImpl implements LogInfoService {

    private static final Logger logger = LoggerFactory.getLogger(LogInfoServiceImpl.class);

    @Autowired
    private LogInfoMapper loginInfoMapper;

    @Override
    public void register(String username, String password) {

        int count = loginInfoMapper.getCountByNickname(username, Constants.USERTYPE_NORMAL);
        if (count <= 0) {
            LogInfo logInfo = new LogInfo();
            logInfo.setNickname(username);
            //获取随机salt
            String salt = MD5Utils.getSaltT();
            //MD5(MD5(password)+salt)
            logInfo.setPassword(MD5Utils.formPassToDBPass(password, salt));
            logInfo.setState(Constants.STATE_NORMAL);
            logInfo.setUserType(Constants.USERTYPE_NORMAL);
            logInfo.setRegisterDate(new Date());
            logInfo.setLastLoginDate(new Date());
            logInfo.setSalt(salt);
            this.loginInfoMapper.insert(logInfo);
        } else {
            throw new RuntimeException("用户名已经存在!");
        }
    }

    @Override
    public boolean checkUsername(String name, int userType) {
        return this.loginInfoMapper.getCountByNickname(name, userType) <= 0;
    }

    @Override
    public LogInfo login(String name, String password, int userType) {
        LogInfo current = new LogInfo();

        try {
            LogInfo logInfo = loginInfoMapper.getLoginInfoByNickname(name, Constants.USERTYPE_NORMAL);
            String salt = logInfo.getSalt();
            current = this.loginInfoMapper.login(name, MD5Utils.formPassToDBPass(password, salt), userType);
/*            if(current != null){
                redisService.set("Login"+current.getNickname(),current);
//				RedisCacheStorageService.set("login"+current.getId().toString(),10000,current);
            }*/
        } catch (Exception e) {
            logger.error("登录发生错误!", e);
            //  resultGeekQ.withError(ResultStatus.LOGIN_FIAL);
        }
        return current;
    }

    @Override
    public boolean hasAdmin() {
        return false;
    }

    @Override
    public void createDefaultAdmin() {
    }

    @Override
    public List<Map<String, Object>> autoComplate(String word, int userType) {
        return null;
    }
}
