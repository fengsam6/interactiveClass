package com.code.classsystem.vo;

import javax.validation.constraints.NotNull;

public class UserLoginVo {
    @NotNull(message = "账号密码不能为空！")
    private String password;
    @NotNull(message = "登录账号不能为空！")
    private String userAccount;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
