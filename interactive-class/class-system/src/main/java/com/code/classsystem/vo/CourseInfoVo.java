package com.code.classsystem.vo;

import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;

import java.util.List;

public class CourseInfoVo extends Course {
    private String courseId;
    private List<CourseResource> courseResourceList;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<CourseResource> getCourseResourceList() {
        return courseResourceList;
    }

    public void setCourseResourceList(List<CourseResource> courseResourceList) {
        this.courseResourceList = courseResourceList;
    }
}
