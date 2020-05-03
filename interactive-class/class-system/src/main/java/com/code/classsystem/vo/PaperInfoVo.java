package com.code.classsystem.vo;

import com.code.classsystem.entity.Paper;

public class PaperInfoVo extends Paper {
    private String courseName;
    private String className;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
