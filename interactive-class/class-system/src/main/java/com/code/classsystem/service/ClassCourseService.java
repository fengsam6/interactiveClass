package com.code.classsystem.service;

import com.code.classsystem.entity.ClassCourse;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-05-11
 */
public interface ClassCourseService extends IService<ClassCourse> {
void save(String classId,String courseId);
}
