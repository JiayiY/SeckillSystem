package com.dubboss.sk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultSk;
import com.dubboss.sk.enums.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GoodsService;
import service.OrderService;
import vo.GoodsVo;
import vo.OrderDetailVo;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/17 18:14
 * @Vertion 1.0
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private GoodsService goodsService;

    @Reference
    private OrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public ResultSk<OrderDetailVo> info(Model model, SkUser skUser, @RequestParam("orderId")long orderId) {
        ResultSk<OrderDetailVo> resultSk = ResultSk.build();
        if (skUser == null) {
            return ResultSk.error(ResultStatus.SESSION_ERROR);
        }
        OrderInfo orderInfo =orderService.getOrderById(orderId);
        if(orderInfo == null){
            return ResultSk.error(ResultStatus.ORDER_NOT_EXIST);

        }
        Long gId = orderInfo.getGoodsId();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(gId);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setGoodsVo(goodsVo);
        orderDetailVo.setOrderInfo(orderInfo);
        resultSk.setData(orderDetailVo);
        return resultSk;
    }


}
