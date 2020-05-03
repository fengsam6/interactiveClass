package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.code.classsystem.entity.CourseResource;
import com.code.classsystem.dao.CourseResourceMapper;
import com.code.classsystem.service.CourseResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@Service
public class CourseResourceServiceImpl extends ServiceImpl<CourseResourceMapper, CourseResource> implements CourseResourceService {

    @Override
    public void deleteByCourseId(String courseId) {
        Wrapper<CourseResource> courseResourceWrapper = new EntityWrapper<>();
        courseResourceWrapper.eq("course_id",courseId);
         this.delete(courseResourceWrapper);
    }
}
