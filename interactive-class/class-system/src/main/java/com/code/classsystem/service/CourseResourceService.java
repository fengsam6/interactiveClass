package com.code.classsystem.service;

import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.CourseResource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface CourseResourceService extends IService<CourseResource> {

    void deleteByCourseId(String id);

    List<CourseResource> getResources(Course course, int pageNum, int pageSize);

    List<CourseResource> getMyResources(int pageNum, int pageSize);

    List<CourseResource> queryCourseRes(int pageNum, int pageSize);
}
