package com.code.classsystem.vo;

import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;

import java.util.List;

public class CourseInfoVo extends Course {
    private String courseId;
    private List<String> classNameList;
    private List<String> resourcePathList;
    private List<CourseResource> courseResources;


    public List<String> getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List<String> classNameList) {
        this.classNameList = classNameList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getResourcePathList() {
        return resourcePathList;
    }

    public List<CourseResource> getCourseResources() {
        return courseResources;
    }

    public void setCourseResources(List<CourseResource> courseResources) {
        this.courseResources = courseResources;
    }

    public void setResourcePathList(List<String> resourcePathList) {
        this.resourcePathList = resourcePathList;
    }
}
