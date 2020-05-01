package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "用户id不能为空")
    private String userId;
    private String userName;
    private String signTime;
    @NotNull(message = "签到地点不能为空")
    private String signPlace;
    /**
     * 签到。1表示签到，0表示签退
     */
    private Integer signIn;
    private String ip;
    private String courseId;
    private String classId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

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

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
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
