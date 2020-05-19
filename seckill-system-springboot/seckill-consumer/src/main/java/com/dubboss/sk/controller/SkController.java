package com.dubboss.sk.controller;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.enums.ResultSk;
import com.dubboss.sk.enums.ResultStatus;
import com.dubboss.sk.rabbitmq.MQSender;
import com.dubboss.sk.rabbitmq.SkMessage;
import com.dubboss.sk.redis.GoodsKey;
import com.dubboss.sk.service.GoodsService;
import com.dubboss.sk.service.OrderService;
import com.dubboss.sk.service.SkService;
import com.dubboss.sk.service.impl.RedisService;
import com.dubboss.sk.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

import static com.dubboss.sk.enums.ResultStatus.EXCEPTION;
import static com.dubboss.sk.enums.ResultStatus.MIAO_SHA_OVER;

/**
 * @ClassName SkController
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:22
 * @Vertion 1.0
 **/
@Controller
public class SkController implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(SkController.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SkService skService;

    @Autowired
    MQSender mqSender;

    private HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

/*    @RequestMapping(value = "/do_miaosha")
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
    }*/

    @RequestMapping(value = "/do_miaosha")
    @ResponseBody
    public ResultSk<Integer> miaosha(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId) {
        ResultSk<Integer> result = ResultSk.build();
        model.addAttribute("user", skUser);
        if (skUser == null) {
            result.withError(ResultStatus.SESSION_ERROR.getCode(), ResultStatus.SESSION_ERROR.getMessage());
            return result;
        }

        //内存标记，减少redis访问
        boolean over = localOverMap.get(goodsId);
        if (over) {
            result.withError(EXCEPTION.getCode(), MIAO_SHA_OVER.getMessage());
            return result;
        }

        // 预减库存
        Long stock = redisService.decr(GoodsKey.getSkGoodsStock, ""+goodsId);
        if(stock<0){
            localOverMap.put(goodsId, true);
            result.withError(MIAO_SHA_OVER.getCode(), ResultStatus.SESSION_ERROR.getMessage());
            return result;
        }

        // 判断是否已经秒杀到了
        SkOrder skOrder = orderService.getSkOrderByUIdGId(skUser.getId(),goodsId);
        if(skOrder!=null){
            result.withError(EXCEPTION.getCode(), ResultStatus.REPEATE_MIAOSHA.getMessage());
            return result;
        }

        // 入队
        SkMessage skMessage = new SkMessage();
        skMessage.setSkUser(skUser);
        skMessage.setGoodsId(goodsId);
        mqSender.sendSkMessage(skMessage);

        // 排队中
        return result;
    }

    /**
     * @Author yangjiayi
     * @Description // 前端轮询 orderId:成功 -1:库存不足 0:排队中
     * @Date 22:53 2020/5/19
     * @param model
     * @param skUser
     * @param goodsId
     * @return com.dubboss.sk.enums.ResultSk<java.lang.Integer>
     */
    @RequestMapping(value = "result")
    @ResponseBody
    public ResultSk<Long> skResult(Model model, SkUser skUser, @RequestParam("goodsId") long goodsId) {
        ResultSk<Long> result = ResultSk.build();
        model.addAttribute("user", skUser);
        if (skUser == null) {
            result.withError(ResultStatus.SESSION_ERROR.getCode(), ResultStatus.SESSION_ERROR.getMessage());
            return result;
        }
        Long orderId = skService.getSkResult(skUser.getId(),goodsId);
        result.setData(orderId);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVos = goodsService.getSkGoods();
        if (goodsVos == null) {
            return;
        }
        for (GoodsVo goodsVo : goodsVos) {
            redisService.set(GoodsKey.getSkGoodsStock, "" + goodsVo.getId(), goodsVo.getStockCount());
            localOverMap.put(goodsVo.getId(), false);
        }
    }
}
