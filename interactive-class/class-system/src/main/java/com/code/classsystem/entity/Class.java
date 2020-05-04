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
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @NotNull(message = "班级名称不能为空")
    private String className;
    /**
     * 班级人数
     */
    private Integer classNum;
    /**
     * 班级简介
     */
    private String classIntroduce;
    /**
     * 班级创建j标识,可以根据班级code 搜索
     */
    private String classCreateCode;
    private String createDate;
    private String updateDate;
    /**
     * 逻辑删除。1标识被删除
     */
    private Integer isDelete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getClassCreateCode() {
        return classCreateCode;
    }

    public void setClassCreateCode(String classCreateCode) {
        this.classCreateCode = classCreateCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Class{" +
        "id=" + id +
        ", className=" + className +
        ", classNum=" + classNum +
        ", classIntroduce=" + classIntroduce +
        ", classCreateCode=" + classCreateCode +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", isDelete=" + isDelete +
        "}";
    }
}
