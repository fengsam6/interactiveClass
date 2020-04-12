package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.code.classsystem.entity.User;
import com.code.classsystem.dao.UserMapper;
import com.code.classsystem.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.util.IPUtil;
import com.code.core.util.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(String userAccount, String password) {
        AuthenticationToken token = new UsernamePasswordToken(userAccount, password);
        //调用shiro userRealm登录，登录失败会抛出异常，由spring 拦截器拦截返回json数据
        ShiroUtils.getSubject().login(token);

        // 登录成功，将token 存储session中
        Session session = ShiroUtils.getSession();

        String loginToken = (String) session.getId();
        return loginToken;
    }


    @Override
    public User getByUserAccount(String userAccount) {
        User user = new User();
        user.setAccount(userAccount);
        Wrapper<User> userWrapper = new EntityWrapper<>(user);
        User user1 = this.selectOne(userWrapper);
        return user1;
    }

    @Override
    public User register(User user) {
        if(StringUtils.isNull(user.getAccount())){
            user.setAccount(user.getUserNum());
        }
        Integer roleId = user.getRoleId();
        if(roleId==null || roleId==0){
            roleId = 1;
            user.setRoleId(roleId);
        }
        String ip = IPUtil.getIp();
        user.setLoginIp(ip);
        this.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        String ip = IPUtil.getIp();
        user.setLoginIp(ip);
        this.updateById(user);
         return user;
    }

    @Override
    public User updateUserAvatar(String userId,String avatarPath) {
        User user = this.selectById(userId);
        user.setUserAvatar(avatarPath);
        return update(user);
    }
}
