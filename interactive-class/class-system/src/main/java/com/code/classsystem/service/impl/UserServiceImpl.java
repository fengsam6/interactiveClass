package com.code.classsystem.service.impl;

import com.code.classsystem.entity.User;
import com.code.classsystem.dao.UserMapper;
import com.code.classsystem.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rf
 * @since 2020-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
