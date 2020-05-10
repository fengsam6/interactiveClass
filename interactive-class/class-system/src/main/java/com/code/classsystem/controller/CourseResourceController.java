package com.code.classsystem.controller;


import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;
import com.code.classsystem.service.CourseResourceService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/courseResource")
@Api(value = "课堂文件资源接口", tags = "课堂文件资源接口")
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    @PostMapping("/listPage")
    @ApiOperation(value = "分页查找课件", notes = "分页查找课件")
    public ResponseResult listPage(Course course, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
       List<CourseResource> resources=courseResourceService.getResources(course,pageNum,pageSize);
       return ResponseResultUtil.renderSuccess(resources);
    }

    @PostMapping("/myCourses")
    @ApiOperation(value = "查找我所学课程的课件", notes = "查找我所学课程的课件")
    public ResponseResult mylistPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        List<CourseResource> resources=courseResourceService.getMyResources(pageNum,pageSize);
        return ResponseResultUtil.renderSuccess(resources);
    }

    @GetMapping("/queryCourseRes")
    @ApiOperation(value = "查找所有课件", notes = "查找所有课件")
    public ResponseResult queryCourseRes(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        List<CourseResource> resources=courseResourceService.queryCourseRes(pageNum,pageSize);
        return ResponseResultUtil.renderSuccess(resources);
    }

}

