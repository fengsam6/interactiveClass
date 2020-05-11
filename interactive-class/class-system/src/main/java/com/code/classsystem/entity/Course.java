package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-05-11
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程介绍
     */
    private String courseIntroduce;
    /**
     * 课程时长
     */
    private String courseTime;
    /**
     * 课程学分
     */
    private String courseScore;
    /**
     * 课程创建者Id
     */
    private String createdUserId;
    /**
     * 上课人数
     */
    private Integer studentNum;
    /**
     * 上课时间
     */
    private String beginTime;
    /**
     * 下课时间
     */
    private String endTime;

    @TableField(exist = false)
    private List<Class> courseClass;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseIntroduce() {
        return courseIntroduce;
    }

    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(String courseScore) {
        this.courseScore = courseScore;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Class> getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(List<Class> courseClass) {
        this.courseClass = courseClass;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", courseName=" + courseName +
        ", courseIntroduce=" + courseIntroduce +
        ", courseTime=" + courseTime +
        ", courseScore=" + courseScore +
        ", createdUserId=" + createdUserId +
        ", studentNum=" + studentNum +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        "}";
    }
}
