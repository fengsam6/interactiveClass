package com.code.classsystem.service;

import com.code.classsystem.entity.CourseResource;
import com.baomidou.mybatisplus.service.IService;

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
}
