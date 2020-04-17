package com.code.classsystem.vo;

import com.code.classsystem.entity.User;

public class UserInfoVo extends User {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
