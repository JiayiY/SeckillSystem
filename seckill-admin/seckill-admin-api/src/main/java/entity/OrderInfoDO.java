package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName OrderInfoDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "order_info", schema = "db_seckill", catalog = "")
public class OrderInfoDO {
    private long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Byte orderChannel;
    private Byte status;
    private Timestamp createDate;
    private Timestamp payDate;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    @Column(name = "delivery_addr_id", nullable = true)
    public Long getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(Long deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
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
    @Column(name = "goods_count", nullable = true)
    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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
    @Column(name = "order_channel", nullable = true)
    public Byte getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Byte orderChannel) {
        this.orderChannel = orderChannel;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_date", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "pay_date", nullable = true)
    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfoDO that = (OrderInfoDO) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(deliveryAddrId, that.deliveryAddrId) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsCount, that.goodsCount) &&
                Objects.equals(goodsPrice, that.goodsPrice) &&
                Objects.equals(orderChannel, that.orderChannel) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(payDate, that.payDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, goodsId, deliveryAddrId, goodsName, goodsCount, goodsPrice, orderChannel, status, createDate, payDate);
    }
}
