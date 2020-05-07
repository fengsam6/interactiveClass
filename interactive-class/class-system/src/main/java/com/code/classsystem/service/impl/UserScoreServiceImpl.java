package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.UserScore;
import com.code.classsystem.dao.UserScoreMapper;
import com.code.classsystem.service.UserScoreService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.core.util.DateUtils;
import com.code.core.util.UUIDUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
@Service
public class UserScoreServiceImpl extends ServiceImpl<UserScoreMapper, UserScore> implements UserScoreService {

    @Override
    public boolean addUserScore(UserScore userScore) {
        String userId= ShiroUtils.getUserId();
        userScore.setUserId(userId);
        userScore.setId(UUIDUtil.getUUid());
        userScore.setCreateTime(DateUtils.getCurTimeStr());
        return this.insert(userScore);
    }

    @Override
    public UserScore queryUserScore(UserScore userScore) {
        String userId=ShiroUtils.getUserId();
        EntityWrapper<UserScore> wrapper=new EntityWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("paper_id",userScore.getPaperId());
        UserScore userScore1=this.selectOne(wrapper);
        return userScore1;
    }
}
