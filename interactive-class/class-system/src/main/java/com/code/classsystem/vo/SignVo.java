package com.code.classsystem.vo;

import com.code.classsystem.entity.UserSign;

public class SignVo extends UserSign {
    private String signPreTime;
    private String signNextTime;
    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSignPreTime() {
        return signPreTime;
    }

    public void setSignPreTime(String signPreTime) {
        this.signPreTime = signPreTime;
    }

    public String getSignNextTime() {
        return signNextTime;
    }

    public void setSignNextTime(String signNextTime) {
        this.signNextTime = signNextTime;
    }
}
