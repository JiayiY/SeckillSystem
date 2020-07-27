package com.dubboss.sk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkGoods;
import com.dubboss.sk.entity.SkOrder;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.redis.SkKey;
import com.dubboss.sk.util.MD5Util;
import com.dubboss.sk.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.GoodsService;
import service.OrderService;
import service.SkService;
import vo.GoodsVo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName SkServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:26
 * @Vertion 1.0
 **/
@Service(interfaceClass = SkService.class)
@Component
public class SkServiceImpl implements SkService {

    /**
     * + - *
     */
    private static final char[] OPS = new char[]{'+', '-', '*'};

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            Integer catch1 = (Integer) engine.eval(exp);
            return catch1.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Transactional
    @Override
    public OrderInfo sk(SkUser skUser, GoodsVo goodsVo) {
        SkGoods skGoods = new SkGoods();
        skGoods.setGoodsId(goodsVo.getId());
        // 秒杀 减库存，下订单，写入秒杀订单
        boolean success = goodsService.reduceStock(skGoods);
        if (success) {
            return orderService.createOrder(skUser, goodsVo);
        } else {
            //如果库存不存在则内存标记为true
            setGoodsOver(skGoods.getId());
            return null;
        }
    }

    @Override
    public long getSkResult(Long uId, long goodsId) {
        SkOrder skOrder = orderService.getSkOrderByUIdGId(uId, goodsId);
        if (skOrder != null) {
            return skOrder.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean checkPath(SkUser skUser, long goodsId, String path) {
        if (skUser == null || path == null) {
            return false;
        }
        String str = redisService.get(SkKey.getSkPath, "" + skUser.getId() + "_" + goodsId, String.class);
        return path.equals(str);
    }

    @Override
    public String createSkPath(SkUser skUser, long goodsId) {
        String str = MD5Util.md5(UUIDUtil.generateUuid() + "123456");
        redisService.set(SkKey.getSkPath, "" + skUser.getId() + "_" + goodsId, str);
        return str;
    }

    private void setGoodsOver(long goodsId) {
        redisService.set(SkKey.isGoodsOver, "" + goodsId, true);
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SkKey.isGoodsOver, "" + goodsId);
    }

    @Override
    public BufferedImage createVerifyCode(SkUser skUser, long goodsId) {
        if (skUser == null || goodsId <= 0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisService.set(SkKey.getMiaoshaVerifyCode, skUser.getNickname() + "," + goodsId, rnd);
        //输出图片
        return image;
    }

    @Override
    public boolean checkVerifyCode(SkUser skUser, long goodsId, int verifyCode) {
        if (skUser == null || goodsId <= 0) {
            return false;
        }
        Integer codeOld = redisService.get(SkKey.getMiaoshaVerifyCode, skUser.getNickname() + "," + goodsId, Integer.class);
        if (codeOld == null || codeOld - verifyCode != 0) {
            return false;
        }
        redisService.delete(SkKey.getMiaoshaVerifyCode, skUser.getNickname() + "," + goodsId);
        return true;
    }

    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = OPS[rdm.nextInt(3)];
        char op2 = OPS[rdm.nextInt(3)];
        return "" + num1 + op1 + num2 + op2 + num3;
    }

}
