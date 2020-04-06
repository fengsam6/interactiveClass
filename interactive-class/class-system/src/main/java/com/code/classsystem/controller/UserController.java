package com.code.classsystem.controller;


import com.code.classsystem.entity.User;
import com.code.classsystem.service.UserService;
import com.code.classsystem.shiro.util.ShiroUtils;
import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "用户接口", tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口", notes = "用户注册接口")
    public ResponseResult register(User user) {
        userService.register(user);
        return ResponseResultUtil.renderSuccessMsg("用户注册成功");
    }


    /**
     * 用户登录接口，登录成功返回token
     *
     * @param userAccount
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public ResponseResult login(String userAccount, String password) {
        String token = null;
        try {
            token = userService.login(userAccount, password);
        } catch (Exception e) {
            return ResponseResultUtil.renderError(ErrorEnum.USER_PASSWORD_ERROR.setMsg(e.getMessage()));
        }
        return ResponseResultUtil.renderSuccess(token);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public ResponseResult getUserInfo() {
        User userEntity = ShiroUtils.getUserEntity();
        return ResponseResultUtil.renderSuccess(userEntity);
    }
}

