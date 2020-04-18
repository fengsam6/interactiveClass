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
public class PaperQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private Integer id;
    /**
     * 问题名称
     */
    private String questionName;
    /**
     * 试卷题目类型。1：选择题；2：填空题
     */
    private Integer questionType;
    /**
     * 问题答案
     */
    private String questionAnswer;
    /**
     * 问题编号
     */
    private String questionNum;
    private String score;
    private String paperId;
    /**
     * 试题排序顺序
     */
    private Integer order;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PaperQuestion{" +
        "id=" + id +
        ", questionName=" + questionName +
        ", questionType=" + questionType +
        ", questionAnswer=" + questionAnswer +
        ", questionNum=" + questionNum +
        ", score=" + score +
        ", paperId=" + paperId +
        ", order=" + order +
        "}";
    }
}
