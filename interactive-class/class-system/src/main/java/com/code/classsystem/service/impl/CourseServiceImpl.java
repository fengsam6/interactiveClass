package com.code.classsystem.service.impl;

import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.Course;
import com.code.classsystem.dao.CourseMapper;
import com.code.classsystem.vo.CourseAndClass;
import com.code.classsystem.service.ClassService;
import com.code.classsystem.service.CourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.vo.CourseInfoVo;
import com.code.core.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public void addCourse(Course course) {
        List<Course>lists=new ArrayList<>();
        List<Class>classes= classService.getClassByClassName(course.getClassName().split(","));
        for(int i=0;i<classes.size();i++){
            Course course1=new Course();
            course1.setCreatedUserId(course.getCreatedUserId());
            course1.setCourseName(course.getCourseName());
            course1.setBeginTime(course.getBeginTime());
            course1.setEndTime(course.getEndTime());
            course1.setCourseIntroduce(course.getCourseIntroduce());
            String courseId = UUIDUtil.getRandomUUID();
            course1.setId(courseId);
            course1.setClassId(classes.get(i).getId());
            course1.setClassName(classes.get(i).getClassName());
            lists.add(course1);
        }
        courseService.insertBatch(lists);
    }

    @Override
    public List<CourseAndClass> queryCourseInfo(String userId) {
        List<CourseAndClass> courseAndClassList=courseMapper.mcourseInfo(userId);
        return courseAndClassList;
    }

    @Override
    public PageInfo<CourseInfoVo> listPage(Course course, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseInfoVo> userInfoVos = courseMapper.listPage(course);
        return new PageInfo<>(userInfoVos);
    }
}
