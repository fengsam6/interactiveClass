package com.code.classsystem.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @NotNull(message = "姓名不能为空")
    private String name;
    /**
     * 性别，0：未知；1：男性；2：女性。
     */
    private Integer sex;
    /**
     * 登录账号
     */

    private String account;
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 学号
     */
    @NotNull(message = "学号不能为空")
    private String userNum;
    /**
     * 用户图像路径
     */
    private String userAvatar;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String updateDate;
    /**
     * 逻辑删除。1被删除
     */
    private Integer isDelete;
    /**
     * 是否禁用。0账号被禁用
     */
    private Integer isUsed;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", sex=" + sex +
        ", account=" + account +
        ", password=" + password +
        ", userNum=" + userNum +
        ", userAvatar=" + userAvatar +
        ", roleId=" + roleId +
        ", loginIp=" + loginIp +
        ", loginTime=" + loginTime +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", isDelete=" + isDelete +
        ", isUsed=" + isUsed +
        "}";
    }
}
