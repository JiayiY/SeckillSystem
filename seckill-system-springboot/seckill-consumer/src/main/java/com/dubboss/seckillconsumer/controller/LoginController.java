package com.dubboss.seckillconsumer.controller;

import com.dubboss.seckillapi.enums.ResultSk;
import com.dubboss.seckillapi.service.UserService;
import com.dubboss.seckillapi.vo.LoginVo;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:36
 * @Vertion 1.0
 **/
@RestController
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Reference
    private UserService userService;

/*    @GetMapping("to_login")
    public String toLogin() {

    }*/

    @PostMapping
    public ResultSk<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        ResultSk<String> result = ResultSk.build();
        logger.info(loginVo.toString());
        userService.login(response, loginVo);
        return result;
    }

}
