package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName SkUserDO
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/5 21:34
 * @Vertion 1.0
 **/
@Entity
@Table(name = "miaosha_user", schema = "db_seckill", catalog = "")
public class SkUserDO {
    private long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Timestamp registerDate;
    private Timestamp lastLoginDate;
    private Integer loginCount;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 10)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "head", nullable = true, length = 128)
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Basic
    @Column(name = "register_date", nullable = true)
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "last_login_date", nullable = true)
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Basic
    @Column(name = "login_count", nullable = true)
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkUserDO skUserDO = (SkUserDO) o;
        return id == skUserDO.id &&
                Objects.equals(nickname, skUserDO.nickname) &&
                Objects.equals(password, skUserDO.password) &&
                Objects.equals(salt, skUserDO.salt) &&
                Objects.equals(head, skUserDO.head) &&
                Objects.equals(registerDate, skUserDO.registerDate) &&
                Objects.equals(lastLoginDate, skUserDO.lastLoginDate) &&
                Objects.equals(loginCount, skUserDO.loginCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, password, salt, head, registerDate, lastLoginDate, loginCount);
    }
}
