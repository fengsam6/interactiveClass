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
 * @since 2020-05-11
 */
public class ClassCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String classId;
    private String courseId;
    private String selectCourseTime;


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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSelectCourseTime() {
        return selectCourseTime;
    }

    public void setSelectCourseTime(String selectCourseTime) {
        this.selectCourseTime = selectCourseTime;
    }

    @Override
    public String toString() {
        return "ClassCourse{" +
        "id=" + id +
        ", classId=" + classId +
        ", courseId=" + courseId +
        ", selectCourseTime=" + selectCourseTime +
        "}";
    }
}
