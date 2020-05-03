package com.code.classsystem.dao;

import com.code.classsystem.entity.Course;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.vo.CourseAndClass;
import com.code.classsystem.vo.CourseInfoVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseAndClass> mcourseInfo(String userId);

    List<CourseInfoVo> listPage(Course course);
}
