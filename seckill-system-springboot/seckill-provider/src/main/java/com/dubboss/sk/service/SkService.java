package com.dubboss.sk.service;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkUser;
import com.dubboss.sk.vo.GoodsVo;

/**
 * @ClassName SkService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:25
 * @Vertion 1.0
 **/
public interface SkService {
    OrderInfo sk(SkUser skUser, GoodsVo goodsVo);
}
