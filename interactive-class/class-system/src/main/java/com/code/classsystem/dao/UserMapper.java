package com.code.classsystem.dao;

import com.code.classsystem.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.vo.UserInfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserInfoVo> listPage(User user);

    UserInfoVo getUserInfoByUserId(String userId);
}
