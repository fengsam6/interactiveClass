package com.code.classsystem.vo;

import com.code.classsystem.entity.HomeWork;

public class HomeWorkVo extends HomeWork {
    private String courseName;
    private String teacherName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
