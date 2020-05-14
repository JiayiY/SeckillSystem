package com.dubboss.seckillconsumer.controller;

import com.dubboss.seckillapi.entity.SkUser;
import com.dubboss.seckillapi.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/14 22:20
 * @Vertion 1.0
 **/
@RestController
public class GoodsController {

    @Reference
    private UserService userService;

    @GetMapping("to_list")
    public String list(Model model,
                       @CookieValue(value = UserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                       @RequestParam(value = UserService.COOKIE_NAME_TOKEN, required = false) String paramToken) {
        if(StringUtils.isEmpty(cookieToken)&&StringUtils.isEmpty(paramToken)){
            return "to_login";
        }
        // 优先取 paramcookie
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        SkUser skUser = userService.getByToken(token);
        model.addAttribute("skuser",skUser);
        return "good_list";
    }

}
