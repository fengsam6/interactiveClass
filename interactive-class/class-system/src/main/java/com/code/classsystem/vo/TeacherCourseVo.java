package com.code.classsystem.vo;

import com.code.classsystem.entity.Class;

import java.util.List;

public class TeacherCourseVo {
    private String courseId;
    private String courseName;
    private List<Class> classList;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }
}
