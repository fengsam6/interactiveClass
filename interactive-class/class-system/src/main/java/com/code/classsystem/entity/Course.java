package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-04-18
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
    private String classId;
    private String className;
    /**
     * 上课人数
     */
    private Integer classNum;
    /**
     * 上课时间
     */
    private String beginTime;
    /**
     * 下课时间
     */
    private String endTime;


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

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
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

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", courseName=" + courseName +
        ", courseIntroduce=" + courseIntroduce +
        ", courseTime=" + courseTime +
        ", courseScore=" + courseScore +
        ", classId=" + classId +
        ", className=" + className +
        ", classNum=" + classNum +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        "}";
    }
}
