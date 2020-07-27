package com.dubboss.sk.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultSk;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GoodsService;
import service.UserService;
import vo.GoodsDetailVo;
import vo.GoodsVo;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/14 22:20
 * @Vertion 1.0
 **/
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Reference
    private UserService userService;

    @Reference
    private GoodsService goodsService;

/*    @GetMapping("to_list")
    public String list(HttpServletResponse response, Model model,
                       @CookieValue(value = UserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                       @RequestParam(value = UserService.COOKIE_NAME_TOKEN, required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return "to_login";
        }
        // 优先取 paramcookie
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        SkUser skUser = userService.getByToken(response, token);
        model.addAttribute("skuser", skUser);
        return "good_list";
    }*/

    @RequestMapping("to_list")
    public String list(Model model, SkUser skUser) {
        model.addAttribute("user", skUser);
        List<GoodsVo> goodsVos = goodsService.getSkGoods();
        model.addAttribute("goodsList", goodsVos);
        return "goodslist";
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public ResultSk<GoodsDetailVo> detail(SkUser skUser, @PathVariable("goodsId") long goodsId) {
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        ResultSk<GoodsDetailVo> result = ResultSk.build();

        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        //秒杀还没开始，倒计时
        if (now < startAt) {
            remainSeconds = (int) ((startAt - now) / 1000);
        } //秒杀已经结束
        else if (now > endAt) {
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
        }
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoodsVo(goodsVo);
        vo.setSkUser(skUser);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        result.setData(vo);
        return result;
    }

}
