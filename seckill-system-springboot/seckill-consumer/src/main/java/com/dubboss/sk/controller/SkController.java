package com.dubboss.sk.controller;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultSk;
import com.dubboss.sk.enums.ResultStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/do_miaosha")
    @ResponseBody
    public ResultSk<OrderInfo> miaosha(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId) {
        ResultSk<OrderInfo> result = ResultSk.build();
        model.addAttribute("user", skUser);
        if (skUser == null) {
            result.withError(ResultStatus.SESSION_ERROR.getCode(), ResultStatus.SESSION_ERROR.getMessage());
            return result;
        }
        // 判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        if(goodsVo.getStockCount()<=0){
            result.withError(ResultStatus.EXCEPTION.getCode(), ResultStatus.MIAO_SHA_OVER.getMessage());
            return result;
        }

        // 判断是否已经秒杀到了
        SkOrder skOrder = orderService.getSkOrderByUIdGId(skUser.getId(),goodsVo.getId());
        if(skOrder!=null){
            result.withError(ResultStatus.EXCEPTION.getCode(), ResultStatus.REPEATE_MIAOSHA.getMessage());
            return result;
        }

        // 秒杀 减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = skService.sk(skUser,goodsVo);
        result.success(orderInfo);
        return result;
    }

}
