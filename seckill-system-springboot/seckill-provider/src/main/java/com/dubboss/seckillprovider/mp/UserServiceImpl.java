package com.dubboss.seckillprovider.mp;

import com.dubboss.seckillapi.dao.SkUserMapper;
import com.dubboss.seckillapi.entity.SkUser;
import com.dubboss.seckillapi.exception.GlobalException;
import com.dubboss.seckillapi.service.UserService;
import com.dubboss.seckillapi.vo.LoginVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

import static com.dubboss.seckillapi.enums.ResultStatus.MOBILE_NOT_EXIST;
import static com.dubboss.seckillapi.enums.ResultStatus.SYSTEM_ERROR;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:17
 * @Vertion 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SkUserMapper skUserMapper;

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            //交给异常处理器处理
            throw new GlobalException(SYSTEM_ERROR);
        }
        return true;
    }
}
