package com.code.classsystem.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rf
 * @since 2020-04-04
 */
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String className;
    private Integer classNum;
    private String classIntroduce;
    /**
     * 班级标识,可以根据班级code 搜索
     */
    private String classCode;
    private Date createDate;
    private Date updateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getClassIntroduce() {
        return classIntroduce;
    }

    public void setClassIntroduce(String classIntroduce) {
        this.classIntroduce = classIntroduce;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Class{" +
        "id=" + id +
        ", className=" + className +
        ", classNum=" + classNum +
        ", classIntroduce=" + classIntroduce +
        ", classCode=" + classCode +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
