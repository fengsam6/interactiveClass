package com.code.classsystem.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-05-06
 */
public class UserQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String questionId;
    private String userId;
    /**
     * 我的答案
     */
    private String myAnswer;
    /**
     * 正确答案
     */
    private String correctAnswer;
    /**
     * 所得分数
     */
    private String score;
    private String createTime;
    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserQuestion{" +
        "id=" + id +
        ", questionId=" + questionId +
        ", userId=" + userId +
        ", myAnswer=" + myAnswer +
        ", correctAnswer=" + correctAnswer +
        ", score=" + score +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
