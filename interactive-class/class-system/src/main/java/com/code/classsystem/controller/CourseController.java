package com.code.classsystem.controller;


import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.Course;
import com.code.classsystem.service.CourseService;
import com.code.classsystem.vo.CourseAndClass;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
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
        List<String> list=new ArrayList<>();
        courseService.addCourse(course);
        return ResponseResultUtil.renderSuccessMsg("创建课程成功！");
    }

    /**
     * 我学的课程
     * @param userId
     * @return
     */
    @ApiOperation(value = "查看当前用户参加的课程", notes = "查看当前用户参加的课程")
    @PostMapping ("/queryCourseInfo")
    public ResponseResult queryCourseInfo(String userId) {
        List<CourseAndClass>list=new ArrayList<>();
        list=courseService.queryCourseInfo(userId);
        return ResponseResultUtil.renderSuccess(list);
    }

}

