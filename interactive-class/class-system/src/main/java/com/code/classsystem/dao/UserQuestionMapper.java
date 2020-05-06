package com.code.classsystem.dao;

import com.code.classsystem.entity.UserQuestion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.vo.PaperResultVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-05-06
 */
public interface UserQuestionMapper extends BaseMapper<UserQuestion> {

    List<PaperResultVo> queryMyResult(Map<String,Object> map);
}
