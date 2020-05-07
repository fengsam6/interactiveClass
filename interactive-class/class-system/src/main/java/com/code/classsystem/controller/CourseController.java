package com.code.classsystem.controller;


import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Course;
import com.code.classsystem.service.CourseService;
import com.code.classsystem.vo.CourseInfoVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/course")
@Api(value = "课程接口", tags = "课程接口")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "创建课程", notes = "创建课程")
    @PostMapping("/createCourse")
    public ResponseResult createCourse(Course course) {
        courseService.addCourse(course);
        return ResponseResultUtil.renderSuccessMsg("创建课程成功！");
    }

    /**
     * 我学的课程
     *
     * @return
     */
    @ApiOperation(value = "查看当前用户参加的课程", notes = "查看当前用户参加的课程")
    @PostMapping("/queryCourseInfo")
    public ResponseResult queryCourseInfo() {
        String userId = ShiroUtils.getUserId();
        List list = courseService.queryCourseInfo(userId);
        return ResponseResultUtil.renderSuccess(list);
    }

    /**
     * 我所教的课程
     *
     * @return
     */
    @ApiOperation(value = "查看当前老师参加的课程", notes = "查看当前老师参加的课程")
    @PostMapping("/queryTeachCourse")
    public ResponseResult queryTeachCourse() {
        String userId = ShiroUtils.getUserId();
        List list = courseService.queryTeachCourse(userId);
        return ResponseResultUtil.renderSuccess(list);
    }


    @GetMapping("/listPage")
    @ApiOperation(value = "分页查找用户", notes = "分页查找用户")
    public ResponseResult listPage(Course course, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<CourseInfoVo> userInfoVoPageInfo = courseService.listPage(course, pageNum, pageSize);
        return ResponseResultUtil.renderSuccess(userInfoVoPageInfo, "退分页查找用户成功");
    }

    @ApiOperation(value = "添加课程", notes = "添加课程")
    @PostMapping("/addCourse")
    public ResponseResult addCourse(@RequestBody CourseInfoVo course) {
        courseService.addCourseInfoVo(course);
        return ResponseResultUtil.renderSuccess("添加课程成功！");
    }

    @ApiOperation(value = "更新课程", notes = "更新课程")
    @PostMapping("/updateCourse")
    public ResponseResult updateCourse(@RequestBody CourseInfoVo course) {
        courseService.updateCourse(course);
        return ResponseResultUtil.renderSuccess("更新课程成功！");
    }

    @ApiOperation(value = "删除课程", notes = "删除课程")
    @PostMapping("/deleteCourse")
    public ResponseResult deleteCourse(@RequestParam("ids") String[] ids) {
        courseService.deleteCourse(ids);
        return ResponseResultUtil.renderSuccess("删除课程成功！");
    }

    @ApiOperation(value = "根据id查看课程详细信息", notes = "根据id查看课程详细信息")
    @GetMapping("/getDetailById/{id}")
    public ResponseResult getDetailById(@PathVariable("id") String id) {
        CourseInfoVo courseInfoVo = courseService.getDetailById(id);
        return ResponseResultUtil.renderSuccess(courseInfoVo);
    }


}

