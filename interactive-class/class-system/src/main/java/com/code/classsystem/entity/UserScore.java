package com.code.classsystem.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
public class UserScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String paperId;
    private String courseId;
    /**
     * 总分
     */
    private String totalScore;
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserScore{" +
        "id=" + id +
        ", userId=" + userId +
        ", paperId=" + paperId +
        ", courseId=" + courseId +
        ", totalScore=" + totalScore +
        ", createTime=" + createTime +
        "}";
    }
}
