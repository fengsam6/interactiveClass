package com.code.classsystem.controller;


import com.code.classsystem.entity.UserQuestion;
import com.code.classsystem.service.UserQuestionService;
import com.code.classsystem.vo.PaperResultVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/userQuestion")
@Api(value = "用户答题卡接口", tags = "用户答题卡接口")
public class UserQuestionController {

    @Autowired
    private UserQuestionService userQuestionService;
    @PostMapping("/subAnswer")
    @ApiOperation(value = "用户提交答案接口", notes = "用户提交答案接口")
    public ResponseResult subAnswer(@RequestBody List<UserQuestion> userQuestions){
        userQuestionService.subAnswer(userQuestions);
        return ResponseResultUtil.renderSuccess("用户提交答案成功");
    }
    @PostMapping("/queryMyResult")
    @ApiOperation(value = "查询用户提交答案接口", notes = "查询用户提交答案接口")
    public ResponseResult queryMyResult(String paperId){
        List<PaperResultVo>paperResultVos=new LinkedList<>();
        paperResultVos=userQuestionService.queryMyResult(paperId);
        return ResponseResultUtil.renderSuccess(paperResultVos);
    }

}

