package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
public class HomeWork implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 作业标题
     */
    @NotNull(message = "作业名称不能为空")
    private String workTitle;
    /**
     * 作业描述
     */
    private String workDes;
    /**
     * 附件路径
     */
    @NotNull(message = "作业附件不能为空")
    private String attachPath;
    /**
     * 作业提交者id
     */
    private String studentId;
    private String submitTime;
    private String teacherId;
    /**
     * 课程id
     */
    private String courseId;
    private String classId;
    /**
     * 下载次数
     */
    private Integer downloadNum;
    private String lastDownTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkDes() {
        return workDes;
    }

    public void setWorkDes(String workDes) {
        this.workDes = workDes;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getLastDownTime() {
        return lastDownTime;
    }

    public void setLastDownTime(String lastDownTime) {
        this.lastDownTime = lastDownTime;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
        "id=" + id +
        ", workTitle=" + workTitle +
        ", workDes=" + workDes +
        ", attachPath=" + attachPath +
        ", studentId=" + studentId +
        ", submitTime=" + submitTime +
        ", teacherId=" + teacherId +
        ", courseId=" + courseId +
        ", classId=" + classId +
        ", downloadNum=" + downloadNum +
        ", lastDownTime=" + lastDownTime +
        "}";
    }
}
