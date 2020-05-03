package com.code.classsystem.vo;

import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;

import java.util.List;

public class CourseInfoVo extends Course {
    private String courseId;
    private List<String> classNameList;
    private List<CourseResource> pptResources;
    private List<CourseResource> videoResources;

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

    public List<CourseResource> getPptResources() {
        return pptResources;
    }

    public void setPptResources(List<CourseResource> pptResources) {
        this.pptResources = pptResources;
    }

    public List<CourseResource> getVideoResources() {
        return videoResources;
    }

    public void setVideoResources(List<CourseResource> videoResources) {
        this.videoResources = videoResources;
    }
}
