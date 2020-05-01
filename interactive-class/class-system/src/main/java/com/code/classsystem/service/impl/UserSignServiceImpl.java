package com.code.classsystem.service.impl;

import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.UserSign;
import com.code.classsystem.dao.UserSignMapper;
import com.code.classsystem.service.UserSignService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.IPUtil;
import com.code.classsystem.vo.SignVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Service
public class UserSignServiceImpl extends ServiceImpl<UserSignMapper, UserSign> implements UserSignService {

    @Autowired
    private UserSignMapper userSignMapper;
    @Override
    public boolean doSign(UserSign userSign) {
        String userId= ShiroUtils.getUserId();
        String userName=ShiroUtils.getUserEntity().getName();
        userSign.setUserId(userId);
        userSign.setIp(IPUtil.getIp());
        userSign.setUserName(userName);
        return this.insert(userSign);
    }

    @Override
    public SignVo querySignInfo(UserSign userSign) {
        String userId=ShiroUtils.getUserId();
        userSign.setUserId(userId);
        SignVo signVo=userSignMapper.querySignInfo(userSign);
        return signVo;
    }
}
