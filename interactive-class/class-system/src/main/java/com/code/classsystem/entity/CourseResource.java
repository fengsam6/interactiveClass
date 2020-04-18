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
public class CourseResource implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程资源名称
     */
    private String courseResourceName;
    /**
     * 课件资源说明
     */
    private String resourceDes;
    /**
     * 课件类型1：ppt；2：视频
     */
    private Integer resourceType;
    /**
     * 资源存储路径
     */
    private String resourcePath;
    /**
     * 资源作者id
     */
    private String userId;
    private String courseId;
    private String classId;
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseResourceName() {
        return courseResourceName;
    }

    public void setCourseResourceName(String courseResourceName) {
        this.courseResourceName = courseResourceName;
    }

    public String getResourceDes() {
        return resourceDes;
    }

    public void setResourceDes(String resourceDes) {
        this.resourceDes = resourceDes;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CourseResource{" +
        "id=" + id +
        ", courseResourceName=" + courseResourceName +
        ", resourceDes=" + resourceDes +
        ", resourceType=" + resourceType +
        ", resourcePath=" + resourcePath +
        ", userId=" + userId +
        ", courseId=" + courseId +
        ", classId=" + classId +
        ", createTime=" + createTime +
        "}";
    }
}
