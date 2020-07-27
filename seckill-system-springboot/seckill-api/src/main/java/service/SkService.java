package service;

import com.dubboss.sk.entity.OrderInfo;
import com.dubboss.sk.entity.SkUser;
import vo.GoodsVo;

import java.awt.image.BufferedImage;

/**
 * @ClassName SkService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/16 20:25
 * @Vertion 1.0
 **/
public interface SkService {
    OrderInfo sk(SkUser skUser, GoodsVo goodsVo);

    long getSkResult(Long uId, long goodsId);

    boolean checkPath(SkUser skUser, long goodsId, String path);

    String createSkPath(SkUser skUser, long goodsId);

    BufferedImage createVerifyCode(SkUser skUser, long goodsId);

    boolean checkVerifyCode(SkUser skUser, long goodsId, int verifyCode);
}
