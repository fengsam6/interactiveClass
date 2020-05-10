package com.code.classsystem.controller;


import com.code.classsystem.entity.UserSign;
import com.code.classsystem.service.UserSignService;
import com.code.classsystem.util.DateUtils;
import com.code.classsystem.vo.SignVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/userSign")
@Api(value = "用户签到接口", tags = "用户签到接口")
public class UserSignController {
    @Autowired
    private UserSignService userSignService;
    @ApiOperation(value = "签到", notes = "签到")
    @PostMapping("/signIn")
    public ResponseResult signIn( UserSign userSign) {
        userSignService.doSign(userSign);
       // userSign.setSignTime(DateUtils.getCurTimeStr());
       // userSign.setSignIn(1);
     //   userSignService.insert(userSign);
        return ResponseResultUtil.renderSuccess("签到成功！");
    }

    @ApiOperation(value = "签退", notes = "签退")
    @PostMapping("/signOut")
    public ResponseResult signOut(UserSign userSign) {
        userSignService.doSign(userSign);
      //  userSign.setSignTime(DateUtils.getCurTimeStr());
      //  userSign.setSignIn(0);
      //  userSignService.insert(userSign);
        return ResponseResultUtil.renderSuccess("签退成功！");
    }
    @ApiOperation(value = "个人签到情况", notes = "个人签到情况")
    @PostMapping("/queryMySignInfo")
    public ResponseResult queryMySignInfo(UserSign userSign) {
        SignVo signVo=userSignService.querySignInfo(userSign);
        return ResponseResultUtil.renderSuccess(signVo);
    }
}

