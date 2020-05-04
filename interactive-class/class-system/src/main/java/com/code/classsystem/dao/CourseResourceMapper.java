package com.code.classsystem.dao;

import com.code.classsystem.entity.CourseResource;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface CourseResourceMapper extends BaseMapper<CourseResource> {
    List<CourseResource> queryMyCourse(String userId);
}
