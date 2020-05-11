package com.code.classsystem.service.impl;

import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Course;
import com.code.classsystem.entity.HomeWork;
import com.code.classsystem.dao.HomeWorkMapper;
import com.code.classsystem.entity.User;
import com.code.classsystem.service.CourseService;
import com.code.classsystem.service.HomeWorkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.DateUtils;
import com.code.classsystem.vo.HomeWorkVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
@Service
public class HomeWorkServiceImpl extends ServiceImpl<HomeWorkMapper, HomeWork> implements HomeWorkService {
    @Autowired
    private HomeWorkMapper homeWorkMapper;
    @Autowired
    private CourseService courseService;

    @Override
    public void addHomeWork(HomeWork homeWork) {
        String userId = ShiroUtils.getUserId();
        homeWork.setStudentId(userId);
        homeWork.setSubmitTime(DateUtils.getCurTimeStr());
        String courseId = homeWork.getCourseId();
        Assert.notNull(courseId, "课程id不能为空");
        Course course = courseService.selectById(courseId);
        Assert.notNull(course, "课程不存在！");
        String createdUserId = course.getCreatedUserId();
        homeWork.setTeacherId(createdUserId);
        this.insert(homeWork);
    }

    @Override
    public PageInfo<HomeWorkVo> listPage(HomeWork homeWork, int pageNum, int pageSize) {
        User userEntity = ShiroUtils.getUserEntity();
        Integer roleId =  userEntity.getRoleId();
        String userEntityId = userEntity.getId();
        switch (roleId){
            case 1:{
                homeWork.setStudentId(userEntityId);
                break;
            }
            case 2:{
                homeWork.setTeacherId(userEntityId);
                break;
            } case 3 :{
                break;
            } default:{
                homeWork.setStudentId(userEntityId);
            }
        }

        PageHelper.startPage(pageNum, pageSize);
        List<HomeWorkVo> userInfoVos = homeWorkMapper.listPage(homeWork);
        return new PageInfo<>(userInfoVos);
    }

    @Override
    public HomeWorkVo getDetailById(String id) {
        return homeWorkMapper.getDetailById(id);
    }
}
