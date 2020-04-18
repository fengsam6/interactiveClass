package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.dao.ClassUserMapper;
import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.ClassUser;
import com.code.classsystem.entity.User;
import com.code.classsystem.service.ClassService;
import com.code.classsystem.service.ClassUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Service
public class ClassUserServiceImpl extends ServiceImpl<ClassUserMapper, ClassUser> implements ClassUserService {
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassUserMapper classUserMapper;

    @Override
    public void saveClassUser(String classId, String userId) {
        ClassUser classUser = new ClassUser();
        classUser.setCreatedUserId(userId);
        this.insert(classUser);
    }

    @Override
    public List<Class> listClassByUserId(String userId) {
        return classUserMapper.listClassByUserId(userId);
    }

    @Override
    public void joinClass(String classCode) {
        Class clazz = classService.getClassByClassCode(classCode);
        //获取当前登录用户id
        String userId = ShiroUtils.getUserId();
        ClassUser classUser = new ClassUser();
        classUser.setClassId(clazz.getId());
        classUser.setMemberId(userId);
        this.insert(classUser);
    }

    @Override
    public List<User> showClassUsers(String classId) {
        return classUserMapper.showClassUsers(classId);
    }
}
