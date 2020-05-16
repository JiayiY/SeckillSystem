package com.dubboss.sk.controller;

import com.dubboss.sk.enums.ResultSk;
import com.dubboss.sk.service.UserService;
import com.dubboss.sk.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/10 17:36
 * @Vertion 1.0
 **/
@Controller
@RequestMapping("login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/to_login")
    public String tologin(LoginVo loginVo) {
        logger.info(loginVo.toString());
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public ResultSk<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        ResultSk<String> result = ResultSk.build();
        logger.info(loginVo.toString());
        userService.login(response, loginVo);
        return result;
    }

}
