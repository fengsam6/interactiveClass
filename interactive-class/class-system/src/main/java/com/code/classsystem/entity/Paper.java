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
public class Paper implements Serializable {
    @TableId(value = "id", type = IdType.UUID)
    private static final long serialVersionUID = 1L;

    private String id;
    private String paperName;
    private String paperScore;
    /**
     * 答题时长
     */
    private String paperTime;
    private String answerBeginTime;
    private String answerEndTime;
    /**
     * 试卷发布者id
     */
    private String publishUserId;
    private String publishTime;
    /**
     * 试卷答题用户id
     */
    private String answerUserId;
    /**
     * 试卷答题班级班级
     */
    private String classId;
    private String courseId;
    private Integer studentNum;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(String paperScore) {
        this.paperScore = paperScore;
    }

    public String getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(String paperTime) {
        this.paperTime = paperTime;
    }

    public String getAnswerBeginTime() {
        return answerBeginTime;
    }

    public void setAnswerBeginTime(String answerBeginTime) {
        this.answerBeginTime = answerBeginTime;
    }

    public String getAnswerEndTime() {
        return answerEndTime;
    }

    public void setAnswerEndTime(String answerEndTime) {
        this.answerEndTime = answerEndTime;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "Paper{" +
        "id=" + id +
        ", paperName=" + paperName +
        ", paperScore=" + paperScore +
        ", paperTime=" + paperTime +
        ", answerBeginTime=" + answerBeginTime +
        ", answerEndTime=" + answerEndTime +
        ", publishUserId=" + publishUserId +
        ", publishTime=" + publishTime +
        ", answerUserId=" + answerUserId +
        ", classId=" + classId +
        ", studentNum=" + studentNum +
        "}";
    }
}
