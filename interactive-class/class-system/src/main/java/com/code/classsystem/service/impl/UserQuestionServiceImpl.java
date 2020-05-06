package com.code.classsystem.service.impl;

import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.UserQuestion;
import com.code.classsystem.dao.UserQuestionMapper;
import com.code.classsystem.service.UserQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.DateUtils;
import com.code.core.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-05-06
 */
@Service
public class UserQuestionServiceImpl extends ServiceImpl<UserQuestionMapper, UserQuestion> implements UserQuestionService {

    @Override
    public boolean subAnswer(List<UserQuestion> userQuestions) {
        String userId= ShiroUtils.getUserId();
        for(int i=0;i<userQuestions.size();i++){
            userQuestions.get(i).setId(UUIDUtil.getUUid());
            userQuestions.get(i).setUserId(userId);
            userQuestions.get(i).setCreateTime(DateUtils.getCurTimeStr());
        }
        return this.insertBatch(userQuestions);
    }
}
