package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName SkGoodsDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "miaosha_goods", schema = "db_seckill", catalog = "")
public class SkGoodsDO {
    private long id;
    private Long goodsId;
    private BigDecimal miaoshaPrice;
    private Integer stockCount;
    private Timestamp startDate;
    private Timestamp endDate;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goods_id", nullable = true)
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "miaosha_price", nullable = true, precision = 2)
    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    @Basic
    @Column(name = "stock_count", nullable = true)
    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkGoodsDO skGoodsDO = (SkGoodsDO) o;
        return id == skGoodsDO.id &&
                Objects.equals(goodsId, skGoodsDO.goodsId) &&
                Objects.equals(miaoshaPrice, skGoodsDO.miaoshaPrice) &&
                Objects.equals(stockCount, skGoodsDO.stockCount) &&
                Objects.equals(startDate, skGoodsDO.startDate) &&
                Objects.equals(endDate, skGoodsDO.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsId, miaoshaPrice, stockCount, startDate, endDate);
    }
}
