package com.code.classsystem.service;

import com.code.classsystem.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param userName
     * @param password
     * @return 返回登录token
     */
    String login(String userName, String password);

    User getByUserAccount(String userName);

    User register(User user);
}
