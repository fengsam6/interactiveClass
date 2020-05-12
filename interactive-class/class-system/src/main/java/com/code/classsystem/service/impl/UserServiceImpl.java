package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.code.classsystem.entity.Role;
import com.code.classsystem.entity.User;
import com.code.classsystem.dao.UserMapper;
import com.code.classsystem.service.RoleService;
import com.code.classsystem.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.util.IPUtil;
import com.code.classsystem.vo.UserInfoVo;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.BusinessException;
import com.code.core.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public String login(String userAccount, String password) {
        AuthenticationToken token = new UsernamePasswordToken(userAccount, password);
        //调用shiro userRealm登录，登录失败会抛出异常，由spring 拦截器拦截返回json数据
        ShiroUtils.getSubject().login(token);

        // 登录成功，将token 返回前端
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
        if (StringUtils.isNull(user.getAccount())) {
            user.setAccount(user.getUserNum());
        }
        Integer roleId = user.getRoleId();
        if (roleId == null || roleId == 0) {
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
    public User updateUserAvatar(String userId, String avatarPath) {
        User user = this.selectById(userId);
        user.setUserAvatar(avatarPath);
        return update(user);
    }

    @Override
    public PageInfo<UserInfoVo> listPage(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if(user==null){
            user=new User();
        }
        User userEntity = ShiroUtils.getUserEntity();

        Integer roleId = userEntity.getRoleId();
        if(roleId==1){
            throw new BusinessException(ErrorEnum.UNAUTHORIZED.setMsg("权限不够，禁止访问"));
        }else if(roleId==2){
            String userId = userEntity.getId();
            user.setId(userId);
        }
        List<UserInfoVo> userInfoVos = userMapper.listPage(user);
        return new PageInfo<>(userInfoVos);
    }

    @Override
    public UserInfoVo getUserInfo() {
        String userId = ShiroUtils.getUserId();
        return userMapper.getUserInfoByUserId(userId);
    }

    @Override
    public UserInfoVo getUserInfoById(String userId) {
        return userMapper.getUserInfoByUserId(userId);
    }

    @Override
    public void add(User user) {
        if (StringUtils.isNull(user.getAccount())) {
            user.setAccount(user.getUserNum());
        }
        Integer roleId = user.getRoleId();
        if (roleId == null || roleId == 0) {
            roleId = 1;
            user.setRoleId(roleId);
        }
        this.insert(user);
    }


}
