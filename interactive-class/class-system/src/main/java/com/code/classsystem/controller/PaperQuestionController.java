package com.code.classsystem.controller;


import com.code.classsystem.entity.Paper;
import com.code.classsystem.entity.PaperQuestion;
import com.code.classsystem.service.PaperQuestionService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/paperQuestion")
public class PaperQuestionController {

    @Autowired
    private PaperQuestionService paperQuestionService;
    @ApiOperation(value = "添加试题接口", notes = "添加试题接口")
    @PostMapping("/addQuestion")
    public ResponseResult addPaperQuestion(PaperQuestion paper) {
        paperQuestionService.addPaperQuestion(paper);
        return ResponseResultUtil.renderSuccessMsg("添加试题成功！");
    }
    @ApiOperation(value = "查询试卷题目接口", notes = "查询试卷题目接口")
    @GetMapping("/queryPaperQuestionById")
    public ResponseResult queryPaperQuestionById(String paperId) {
        List<PaperQuestion>paperQuestions= paperQuestionService.queryPaperQuestionById(paperId);
        return ResponseResultUtil.renderSuccess(paperQuestions);
    }
    @RequestMapping("/delPaper")
    public ResponseResult delPaper(String paperId) {
        paperQuestionService.delPaper(paperId);
        return ResponseResultUtil.renderSuccess("");
    }
}

