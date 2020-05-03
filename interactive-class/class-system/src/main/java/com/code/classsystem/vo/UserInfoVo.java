package com.code.classsystem.vo;

import com.code.classsystem.entity.User;

public class UserInfoVo extends User {
    private String roleType;
    private String sexType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }
}
