package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public class UserSign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String userId;
    private String userName;
    private Date signTime;
    private String signPlace;
    /**
     * 签到。1表示签到，0表示签退
     */
    private Integer signIn;
    private String ip;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getSignPlace() {
        return signPlace;
    }

    public void setSignPlace(String signPlace) {
        this.signPlace = signPlace;
    }

    public Integer getSignIn() {
        return signIn;
    }

    public void setSignIn(Integer signIn) {
        this.signIn = signIn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "UserSign{" +
        "id=" + id +
        ", userId=" + userId +
        ", userName=" + userName +
        ", signTime=" + signTime +
        ", signPlace=" + signPlace +
        ", signIn=" + signIn +
        ", ip=" + ip +
        "}";
    }
}
