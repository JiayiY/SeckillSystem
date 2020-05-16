package com.dubboss.sk.entity;

import java.io.Serializable;
import java.util.Date;

public class IpLog implements Serializable {
    private Long id;

    private String ip;

    private Byte loginstate;

    private String username;

    private Long logininfoid;

    private Byte logintype;

    private Date logintime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Byte loginstate) {
        this.loginstate = loginstate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getLogininfoid() {
        return logininfoid;
    }

    public void setLogininfoid(Long logininfoid) {
        this.logininfoid = logininfoid;
    }

    public Byte getLogintype() {
        return logintype;
    }

    public void setLogintype(Byte logintype) {
        this.logintype = logintype;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", loginstate=").append(loginstate);
        sb.append(", username=").append(username);
        sb.append(", logininfoid=").append(logininfoid);
        sb.append(", logintype=").append(logintype);
        sb.append(", logintime=").append(logintime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}