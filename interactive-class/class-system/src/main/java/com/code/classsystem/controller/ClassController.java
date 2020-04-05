package com.code.classsystem.controller;


import com.code.classsystem.entity.Class;
import com.code.classsystem.service.ClassService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/class")
@Api(value = "班级接口", tags = "班级接口")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping("/createClass")
    @ApiOperation(value = "创建班级接口", notes = "创建班级接口")
    public ResponseResult createClass(@Valid Class cla, BindingResult bindingResult) {
        classService.createClass(cla);
        return ResponseResultUtil.renderSuccessMsg("创建班级成功！");
    }
}

