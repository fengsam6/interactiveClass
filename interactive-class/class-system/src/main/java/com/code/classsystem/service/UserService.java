package com.code.classsystem.service;

import com.code.classsystem.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.UserInfoVo;
import com.github.pagehelper.PageInfo;

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

    User update(User user);

    public User updateUserAvatar(String userId,String avatarPath);

    PageInfo<UserInfoVo> listPage(User user, int pageNum, int pageSize);

    UserInfoVo getUserInfo();

    UserInfoVo getUserInfoById(String userId);

    void add(User user);
}
