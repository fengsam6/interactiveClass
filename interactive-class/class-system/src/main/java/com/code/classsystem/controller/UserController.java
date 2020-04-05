package com.code.classsystem.controller;


import com.code.classsystem.entity.User;
import com.code.classsystem.service.UserService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口",tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation(value="用户登录接口",notes="用户登录接口")
    public ResponseResult login(String userAccount, String password) {
        User user = userService.login(userAccount, password);
        return ResponseResultUtil.renderSuccess(user);
    }
}

