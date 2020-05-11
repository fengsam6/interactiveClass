package com.code.classsystem.service.impl;

import com.code.classsystem.entity.ClassCourse;
import com.code.classsystem.dao.ClassCourseMapper;
import com.code.classsystem.service.ClassCourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.DateUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-05-11
 */
@Service
public class ClassCourseServiceImpl extends ServiceImpl<ClassCourseMapper, ClassCourse> implements ClassCourseService {

    @Override
    public void save(String classId, String courseId) {
        ClassCourse classCourse =new ClassCourse();
        classCourse.setClassId(classId);
        classCourse.setCourseId(courseId);
        classCourse.setSelectCourseTime(DateUtils.getCurTimeStr());
        this.insert(classCourse);
    }
}
