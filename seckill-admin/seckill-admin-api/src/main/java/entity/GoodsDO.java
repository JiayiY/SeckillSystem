package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName GoodsDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "goods", schema = "db_seckill", catalog = "")
public class GoodsDO {
    private long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private BigDecimal goodsPrice;
    private Integer goodsStock;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goods_name", nullable = true, length = 16)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "goods_title", nullable = true, length = 64)
    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    @Basic
    @Column(name = "goods_img", nullable = true, length = 64)
    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Basic
    @Column(name = "goods_detail", nullable = true, length = -1)
    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Basic
    @Column(name = "goods_price", nullable = true, precision = 2)
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Basic
    @Column(name = "goods_stock", nullable = true)
    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDO goodsDO = (GoodsDO) o;
        return id == goodsDO.id &&
                Objects.equals(goodsName, goodsDO.goodsName) &&
                Objects.equals(goodsTitle, goodsDO.goodsTitle) &&
                Objects.equals(goodsImg, goodsDO.goodsImg) &&
                Objects.equals(goodsDetail, goodsDO.goodsDetail) &&
                Objects.equals(goodsPrice, goodsDO.goodsPrice) &&
                Objects.equals(goodsStock, goodsDO.goodsStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodsName, goodsTitle, goodsImg, goodsDetail, goodsPrice, goodsStock);
    }
}
