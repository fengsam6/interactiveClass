package com.code.classsystem.controller;


import com.code.classsystem.entity.Class;
import com.code.classsystem.service.ClassService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@RequestMapping("/class")
@Api(value = "班级接口", tags = "班级接口")
public class ClassController {
    @Autowired
    private ClassService classService;

    @ApiOperation(value = "查看班级信息", notes = "查看班级信息")
    @GetMapping("/getClassById")
    public ResponseResult getClassById(String id) {
        Class clazz = classService.selectById(id);
        return ResponseResultUtil.renderSuccess(clazz,"创建班级成功！");
    }



    @ApiOperation(value = "创建班级接口", notes = "创建班级接口")
    @PostMapping("/createClass")
    public ResponseResult createClass(@Valid Class cla, BindingResult bindingResult) {
        classService.createClass(cla);
        return ResponseResultUtil.renderSuccessMsg("创建班级成功！");
    }

    @ApiOperation(value = "创建班级接口", notes = "创建班级接口")
    @PostMapping("/list")
    public ResponseResult list(@Valid Class cla) {
        classService.createClass(cla);
        return ResponseResultUtil.renderSuccessMsg("创建班级成功！");
    }
}

