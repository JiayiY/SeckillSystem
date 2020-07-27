package vo;

import com.dubboss.sk.entity.Goods;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GoodsVo
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/15 23:47
 * @Vertion 1.0
 **/
public class GoodsVo extends Goods {

    private BigDecimal miaoshaPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

}
