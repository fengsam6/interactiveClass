package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.code.classsystem.entity.User;
import com.code.classsystem.dao.UserMapper;
import com.code.classsystem.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.AuthenticationFailException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String userAccount, String password) {
        User user1 = this.getByUserAccount(userAccount);
        if(user1==null){
            throw new AuthenticationFailException(ErrorEnum.USER_NAME_ERROR);
        }
        if(user1.getPassword().equals(password)){
            throw new AuthenticationFailException(ErrorEnum.USER_PASSWORD_ERROR);
        }
        return user1;
    }

    private User getByUserAccount(String userAccount){
        User  user = new User();
        user.setAccount(userAccount);
        Wrapper<User> userWrapper =new EntityWrapper<>(user);
        User user1 = this.selectOne(userWrapper);
        return user1;
    }
}
