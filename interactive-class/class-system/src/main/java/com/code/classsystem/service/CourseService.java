package com.code.classsystem.service;

import com.code.classsystem.entity.Course;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.CourseAndClass;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface CourseService extends IService<Course> {
    void addCourse(Course course);

    List<CourseAndClass> queryCourseInfo(String userId);
}
