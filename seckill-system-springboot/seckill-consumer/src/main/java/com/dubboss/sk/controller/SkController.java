package com.dubboss.sk.controller;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.service.SkService;
import com.dubboss.sk.service.impl.RedisService;
import com.dubboss.sk.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName SkController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:22
 * @Vertion 1.0
 **/
@Controller
public class SkController {

    private static Logger logger = LoggerFactory.getLogger(SkController.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SkService skService;

    @RequestMapping(value = "/{path}/do_miaosha")
    public String miaosha(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", skUser);
        if (skUser == null) {
            return "login";
        }
        // 判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        if(goodsVo.getStockCount()<=0){
            model.addAttribute("errmsg","库存不足");
            return "miaosha_fail";
        }

        // 判断是否已经秒杀到了
        SkOrder skOrder = orderService.getSkOrderByUIdGId(skUser.getId(),goodsVo.getId());
        if(skOrder!=null){
            model.addAttribute("errmsg","重复秒杀");
            return "miaosha_fail";
        }

        // 秒杀 减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = skService.sk(skUser,goodsVo);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goodsVo);
        return "order_detail";
    }

}
