package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 班级用户
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public class ClassUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String classId;
    private String className;
    /**
     * 班级成员id
     */
    private String memberId;
    /**
     * 班级创建者Id
     */
    private String createdUserId;
    private Date creatTime;
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ClassUser{" +
        "id=" + id +
        ", className=" + className +
        ", memberId=" + memberId +
        ", createdUserId=" + createdUserId +
        ", creatTime=" + creatTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
