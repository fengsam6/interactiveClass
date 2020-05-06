package com.code.classsystem.service;

import com.code.classsystem.entity.UserQuestion;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.PaperResultVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-05-06
 */
public interface UserQuestionService extends IService<UserQuestion> {

    boolean subAnswer(List<UserQuestion> userQuestions);

    List<PaperResultVo> queryMyResult(String paperId);
}
