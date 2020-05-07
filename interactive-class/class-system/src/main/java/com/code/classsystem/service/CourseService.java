package com.code.classsystem.service;

import com.code.classsystem.entity.Course;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.CourseAndClass;
import com.code.classsystem.vo.CourseInfoVo;
import com.github.pagehelper.PageInfo;

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

    PageInfo<CourseInfoVo> listPage(Course course, int pageNum, int pageSize);

    void addCourseInfoVo(CourseInfoVo course);

    void deleteCourse(String[] ids);

    CourseInfoVo getDetailById(String id);

    void updateCourse(CourseInfoVo course);

    List queryTeachCourse(String userId);
}
