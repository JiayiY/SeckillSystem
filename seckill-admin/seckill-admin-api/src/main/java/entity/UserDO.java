package entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName UserDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "user", schema = "db_seckill", catalog = "")
public class UserDO {
    private int id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDO userDO = (UserDO) o;
        return id == userDO.id &&
                Objects.equals(name, userDO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
