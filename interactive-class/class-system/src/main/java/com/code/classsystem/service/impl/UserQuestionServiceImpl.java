package com.code.classsystem.service.impl;

import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.UserQuestion;
import com.code.classsystem.dao.UserQuestionMapper;
import com.code.classsystem.service.UserQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.DateUtils;
import com.code.classsystem.vo.PaperResultVo;
import com.code.core.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserQuestionMapper questionMapper;
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

    @Override
    public List<PaperResultVo> queryMyResult(String paperId) {
        String userId=ShiroUtils.getUserId();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("paperId",paperId);
        List<PaperResultVo>paperResultVos=questionMapper.queryMyResult(map);
        return paperResultVos;
    }
}
