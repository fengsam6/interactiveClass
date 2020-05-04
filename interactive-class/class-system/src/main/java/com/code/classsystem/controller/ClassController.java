package com.code.classsystem.controller;


import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.User;
import com.code.classsystem.service.ClassService;
import com.code.classsystem.vo.ClassVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getClassById/{id}")
    public ResponseResult getClassById(@PathVariable("id") String id) {
        Class clazz = classService.selectById(id);
        return ResponseResultUtil.renderSuccess(clazz,"查看班级信息！");
    }

    @GetMapping("/getClassById")
    public ResponseResult getClassById2(String id) {
        Class clazz = classService.selectById(id);
        return ResponseResultUtil.renderSuccess(clazz,"查看班级信息！");
    }

    @ApiOperation(value = "创建班级接口", notes = "创建班级接口")
    @PostMapping("/createClass")
    public ResponseResult createClass(@Valid Class cla, BindingResult bindingResult) {
        classService.createClass(cla);
        return ResponseResultUtil.renderSuccess("创建班级成功！");
    }

    @ApiOperation(value = "更新班级接口", notes = "更新班级接口")
    @PostMapping("/updateClass")
    public ResponseResult updateClass(@Valid Class cla) {
        classService.updateById(cla);
        return ResponseResultUtil.renderSuccess("更新班级成功！");
    }

    @ApiOperation(value = "删除班级接口", notes = "删除班级接口")
    @PostMapping("/deleteClass")
    public ResponseResult deleteClass(@RequestParam("ids") List<String> ids) {
        classService.deleteBatchIds(ids);
        return ResponseResultUtil.renderSuccess("删除班级成功！");
    }

    @ApiOperation(value = "分页查询班级", notes = "分页查询班级")
    @GetMapping("/listPage")
    public ResponseResult listPage(Class cla, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<ClassVo> classVoPageInfo = classService.listPage(cla, pageNum, pageSize);
        return ResponseResultUtil.renderSuccess(classVoPageInfo);
    }
}

