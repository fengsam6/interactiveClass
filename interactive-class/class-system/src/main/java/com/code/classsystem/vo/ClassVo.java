package com.code.classsystem.vo;

import com.code.classsystem.entity.Class;

public class ClassVo extends Class {
    private String createUserName;

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
