package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.code.classsystem.entity.PaperQuestion;
import com.code.classsystem.dao.PaperQuestionMapper;
import com.code.classsystem.service.PaperQuestionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.core.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@Service
public class PaperQuestionServiceImpl extends ServiceImpl<PaperQuestionMapper, PaperQuestion> implements PaperQuestionService {

    @Override
    public Boolean addPaperQuestion(PaperQuestion paper) {
       // String id=UUIDUtil.getUUid();
        return this.insert(paper);
    }

    @Override
    public List<PaperQuestion> queryPaperQuestionById(String paperId) {
        EntityWrapper<PaperQuestion> wrapper=new EntityWrapper<>();
        wrapper.eq("paper_id",paperId);
        List<String> list=new LinkedList<>();
        list.add("question_type");
        list.add("question_num");
        wrapper.orderAsc(list);
        return this.selectList(wrapper);
    }

    @Override
    public boolean delPaper(String paperId) {
        Map<String,Object> map=new HashMap<>();
        map.put("paper_id",paperId);
        return this.deleteByMap(map);
    }
}
