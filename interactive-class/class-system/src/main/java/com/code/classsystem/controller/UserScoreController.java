package com.code.classsystem.controller;


import com.code.classsystem.entity.UserScore;
import com.code.classsystem.service.UserScoreService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/userScore")
@Api(value = "学生得分接口", tags = "学生得分接口")
public class UserScoreController {

    @Autowired
    private UserScoreService userScoreService;
    @PostMapping("/addUserScore")
    @ApiOperation(value = "添加学生分数接口", notes = "添加学生分数接口")
    public ResponseResult addUserScore(UserScore userScore){
        userScoreService.addUserScore(userScore);
        return ResponseResultUtil.renderSuccess("添加数据成功!");
    }

    @PostMapping("/queryUserScore")
    @ApiOperation(value = "查询学生分数接口", notes = "查询学生分数接口")
    public ResponseResult queryUserScore(UserScore userScore){
        UserScore userScore1=userScoreService.queryUserScore(userScore);
        return ResponseResultUtil.renderSuccess(userScore1);
    }

}

