package com.code.classsystem.service;

import com.code.classsystem.entity.PaperQuestion;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface PaperQuestionService extends IService<PaperQuestion> {

    Boolean addPaperQuestion(PaperQuestion paper);

    List<PaperQuestion> queryPaperQuestionById(String paperId);

    boolean delPaper(String paperId);
}
