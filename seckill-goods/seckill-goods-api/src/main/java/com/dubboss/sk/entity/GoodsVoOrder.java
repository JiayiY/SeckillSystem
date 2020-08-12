package com.dubboss.sk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName GoodsVoOrder
 * @Description TODO
 * @Author yjy
 * @Date 2020/8/12 9:48
 * @Vertion 1.0
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Alias("goodsVoOrder")
public class GoodsVoOrder implements Serializable {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
