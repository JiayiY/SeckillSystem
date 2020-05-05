package entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName SkOrderDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "miaosha_order", schema = "db_seckill", catalog = "")
public class SkOrderDO {
    private long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;

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
    @Column(name = "order_id", nullable = true)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "goods_id", nullable = true)
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkOrderDO skOrderDO = (SkOrderDO) o;
        return id == skOrderDO.id &&
                Objects.equals(userId, skOrderDO.userId) &&
                Objects.equals(orderId, skOrderDO.orderId) &&
                Objects.equals(goodsId, skOrderDO.goodsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderId, goodsId);
    }
}
