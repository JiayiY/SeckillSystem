package com.dubboss.sk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author yjy
 * @Date 2020/8/12 9:47
 * @Vertion 1.0
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
