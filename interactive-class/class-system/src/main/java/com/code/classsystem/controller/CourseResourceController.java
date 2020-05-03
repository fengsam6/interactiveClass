package com.code.classsystem.controller;


import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;
import com.code.classsystem.service.CourseResourceService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
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
public class CourseResourceController {

    @Autowired
    private CourseResourceService courseResourceService;

    @PostMapping("/listPage")
    @ApiOperation(value = "分页查找课件", notes = "分页查找课件")
    public ResponseResult listPage(Course course, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
       List<CourseResource> resources=courseResourceService.getResources(course,pageNum,pageSize);
       return ResponseResultUtil.renderSuccess(resources);
    }

}

