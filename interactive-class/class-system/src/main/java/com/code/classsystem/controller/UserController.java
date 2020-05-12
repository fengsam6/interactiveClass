package com.code.classsystem.controller;


import com.code.classsystem.entity.User;
import com.code.classsystem.service.UserService;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.vo.UserInfoVo;
import com.code.classsystem.vo.UserLoginVo;
import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.util.ResponseResultUtil;
import com.code.core.util.ValidationUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseResult register(@Valid User user) {
        ValidationUtils.validate(user);
        userService.register(user);
        return ResponseResultUtil.renderSuccess("用户注册成功");
    }

    @PostMapping("/add")
    @RequiresRoles(value  ={"teacher","admin"})
    @ApiOperation(value = "添加用户接口", notes = "添加用户接口")
    public ResponseResult add(@Valid User user) {
        ValidationUtils.validate(user);
        userService.add(user);
        return ResponseResultUtil.renderSuccess("添加用户成功");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息接口", notes = "更新用户信息接口")
    public ResponseResult update(@Valid User user) {
        ValidationUtils.validate(user);
        userService.update(user);
        return ResponseResultUtil.renderSuccess("更新用户信息成功");
    }

    @PostMapping("/updateUserAvatar")
    @ApiOperation(value = "更新用户图像接口", notes = "更新用户图像接口")
    public ResponseResult updateUserAvatar(String userId, String avatarPath) {
        Assert.hasLength(userId, "userId不能为空");
        Assert.hasLength(avatarPath, "avatarPath不能为空");
        userService.updateUserAvatar(userId, avatarPath);
        return ResponseResultUtil.renderSuccess("更新用户信息成功");
    }


    /**
     * 用户登录接口，登录成功返回token
     *
     * @param userLoginVo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public ResponseResult login(@Valid UserLoginVo userLoginVo) {
        String token;
        try {
            String userAccount = userLoginVo.getUserAccount();
            token = userService.login(userAccount, userLoginVo.getPassword());
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                e = (Exception) e.getCause();
            }
            return ResponseResultUtil.renderError(ErrorEnum.USER_PASSWORD_ERROR.setMsg(e.getMessage()));
        }
        return ResponseResultUtil.renderSuccess(token);
    }


    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public ResponseResult getUserInfo() {
        UserInfoVo userInfoVo = userService.getUserInfo();
        return ResponseResultUtil.renderSuccess(userInfoVo);
    }

    @GetMapping("/getUserInfoById/{id}")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public ResponseResult getUserInfoById(@PathVariable("id") String id) {
        UserInfoVo userInfoVo = userService.getUserInfoById(id);
        return ResponseResultUtil.renderSuccess(userInfoVo);
    }

    @PostMapping("/deleteByIds")
    @RequiresRoles(value  ={"teacher","admin"},logical = Logical.OR)
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public ResponseResult deleteById(@RequestParam("ids") List<String> ids) {
        userService.deleteBatchIds(ids);
        return ResponseResultUtil.renderSuccess("删除用户成功");
    }

    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public ResponseResult logout() {
        ShiroUtils.logout();
        return ResponseResultUtil.renderSuccess("退出登录成功");
    }

    @GetMapping("/listPage")
    @RequiresRoles(value  ={"teacher","admin"},logical = Logical.OR)
    @ApiOperation(value = "分页查找用户", notes = "分页查找用户")
    public ResponseResult listPage(User user, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<UserInfoVo> userInfoVoPageInfo = userService.listPage(user, pageNum, pageSize);
        return ResponseResultUtil.renderSuccess(userInfoVoPageInfo, "退分页查找用户成功");
    }
}

