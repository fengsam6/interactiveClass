package com.code.classsystem.vo;

import com.code.classsystem.entity.Course;

import java.util.List;

public class CourseVo extends Course {
    private List<String> classNameList;

    public List<String> getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List<String> classNameList) {
        this.classNameList = classNameList;
    }
}
