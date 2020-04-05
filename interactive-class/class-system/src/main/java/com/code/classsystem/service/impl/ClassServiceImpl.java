package com.code.classsystem.service.impl;

import com.code.classsystem.entity.Class;
import com.code.classsystem.dao.ClassMapper;
import com.code.classsystem.service.ClassService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.service.ClassUserService;
import com.code.classsystem.shiro.util.ShiroUtils;
import com.code.core.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    @Autowired
    private ClassUserService classUserService;

    @Override
    @Transactional
    public void createClass(Class cla) {
        String classId = UUIDUtil.getRandomUUID();
        cla.setId(classId);
        this.insert(cla);
        String userId = ShiroUtils.getUserId();
        classUserService.saveClassUser(classId,userId);
    }
}
