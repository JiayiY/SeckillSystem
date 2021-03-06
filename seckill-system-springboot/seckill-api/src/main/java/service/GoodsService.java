package service;


import com.dubboss.sk.entity.SkGoods;
import vo.GoodsVo;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 0:01
 * @Vertion 1.0
 **/
public interface GoodsService {
    List<GoodsVo> getSkGoods();

    GoodsVo getGoodsVoByGoodsId(long goodsId);

    boolean reduceStock(SkGoods skGoods);
}
