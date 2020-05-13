package com.code.classsystem.controller;


import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.ClassStudent;
import com.code.classsystem.service.ClassUserService;
import com.code.classsystem.vo.ClassStudentVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import com.code.core.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/classUser")
@Api(value = "班级用户接口", tags = "班级用户接口")
public class ClassUserController {
    @Autowired
    private ClassUserService classUserService;

    @ApiOperation(value = "查看用户加入班级列表", notes = "查看用户加入班级列表")
    @GetMapping("/listClassByUserId")
    public ResponseResult listClassByUserId() {
        String userId = ShiroUtils.getUserId();
        List<Class> classList = classUserService.listClassByUserId(userId);
        return ResponseResultUtil.renderSuccess(classList, "查看用户加入班级列表成功！");
    }

    @ApiOperation(value = "查看老师创建班级", notes = "查看老师创建班级")
    @GetMapping("/listTeacherClasses")
    public ResponseResult listTeacherClasses(String teacherId) {
        if(StringUtils.isNull(teacherId)){
            teacherId = ShiroUtils.getUserId();
        }
        List<Class> classList = classUserService.listTeacherClasses(teacherId);
        return ResponseResultUtil.renderSuccess(classList, "查看老师创建班级成功！");
    }

    @ApiOperation(value = "加入班级", notes = "加入班级")
    @PostMapping("/joinClass")
    public ResponseResult joinClass(String classCode) {
        classUserService.joinClass(classCode);
        return ResponseResultUtil.renderSuccess("加入班级成功！");
    }

   /* @ApiOperation(value = "查看班级成员", notes = "查看班级成员")
    @GetMapping("/showClassUsers")
    public ResponseResult showClassUsers(String classId) {
        List<User> users = classUserService.showClassUsers(classId);
        return ResponseResultUtil.renderSuccess(users,"创建班级成功！");
    }*/



    @ApiOperation(value = "查看班级成员", notes = "查看班级成员")
    @PostMapping("/showClassUsers")
    public ResponseResult showClassUsers(ClassStudentVo classStudentVo) {
        List<ClassStudent> data = classUserService.queryStuStatus(classStudentVo);
        return ResponseResultUtil.renderSuccess(data);
    }
}

