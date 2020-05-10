package com.dubboss.seckillprovider.mp;

import com.dubboss.seckillapi.dao.SkUserMapper;
import com.dubboss.seckillapi.entity.SkUser;
import com.dubboss.seckillapi.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean register(SkUser skUser) {
        return skUserMapper.insert(skUser) == 1;
    }

    @Override
    public SkUser findByUsername(String username) {
        SkUser skUser = skUserMapper.selectByUsername(username);
        return skUser;
    }
}
