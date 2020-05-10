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
 * @since 2020-05-10
 */
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String content;
    /**
     * 消息发送id
     */
    private String sendId;
    /**
     * 发送者名字
     */
    private String sendName;
    private String msgType;
    private String classId;
    /**
     * 发送日期
     */
    private String sendTime;
    /**
     * 消息接收者id
     */
    private String toId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "Msg{" +
        "id=" + id +
        ", content=" + content +
        ", sendId=" + sendId +
        ", sendName=" + sendName +
        ", msgType=" + msgType +
        ", classId=" + classId +
        ", sendTime=" + sendTime +
        ", toId=" + toId +
        "}";
    }
}
