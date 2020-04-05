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

    User login(String userName, String password);
}
