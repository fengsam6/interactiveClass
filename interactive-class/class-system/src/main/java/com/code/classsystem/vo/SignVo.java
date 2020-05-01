package com.code.classsystem.vo;

import com.code.classsystem.entity.UserSign;

public class SignVo extends UserSign {
    private String signPreTime;
    private String signNextTime;

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
