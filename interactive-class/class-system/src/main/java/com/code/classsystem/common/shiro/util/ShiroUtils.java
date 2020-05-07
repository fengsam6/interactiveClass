package com.code.classsystem.common.shiro.util;

import com.code.classsystem.entity.User;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.AuthenticationFailException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtils { /**
 * 获取会话
 */
public static Session getSession() {
    return SecurityUtils.getSubject().getSession();
}

    /**
     * Subject：主体，代表了当前“用户”
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getUserEntity() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        User userEntity = getUserEntity();
        if(userEntity==null){
            throw new AuthenticationFailException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg("获取用户信息失败"));
        }
        return userEntity.getId();
    }

    public static Integer getRoleId() {
        User userEntity = getUserEntity();
        if(userEntity==null){
            throw new AuthenticationFailException(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg("获取用户信息失败"));
        }
        return userEntity.getRoleId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
