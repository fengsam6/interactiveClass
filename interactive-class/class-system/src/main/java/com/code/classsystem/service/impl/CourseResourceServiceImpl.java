package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;
import com.code.classsystem.dao.CourseResourceMapper;
import com.code.classsystem.service.CourseResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CourseResource> getResources(Course course, int pageNum, int pageSize) {
        String userId= ShiroUtils.getUserId();
        EntityWrapper<CourseResource> wrapper=new EntityWrapper<>();
        wrapper.eq("class_id",course.getClassId());
        wrapper.eq("course_id",course.getId());
        wrapper.eq("user_id",userId);
        Page<CourseResource>page=new Page(pageNum,pageSize);
        page= this.selectPage(page,wrapper);
        return page.getRecords();
    }
}
