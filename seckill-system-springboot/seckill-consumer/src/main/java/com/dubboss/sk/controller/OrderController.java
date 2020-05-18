package com.dubboss.sk.controller;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultSk;
import com.dubboss.sk.enums.ResultStatus;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.vo.GoodsVo;
import com.dubboss.sk.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private GoodsService goodsService;

    @Autowired
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
