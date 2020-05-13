package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.code.classsystem.entity.Class;
import com.code.classsystem.dao.ClassMapper;
import com.code.classsystem.entity.User;
import com.code.classsystem.service.ClassService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.service.ClassUserService;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.vo.ClassSearchVo;
import com.code.classsystem.vo.ClassVo;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.BusinessException;
import com.code.core.util.StringUtils;
import com.code.core.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.ArrayList;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    @Autowired
    private ClassUserService classUserService;
    @Autowired
    private ClassMapper classMapper;

    @Override
    @Transactional
    public void createClass(Class cla) {
        String className = cla.getClassName();
        Class class2 = getClassByClassNameAndCurId(className);
        if (class2 != null) {
            throw new BusinessException(ErrorEnum.BUSINESS_EXCEPTION.setMsg("该班级名称已经存在，请重新定义班级名称"));
        }
        String classId = UUIDUtil.getRandomUUID();
        cla.setId(classId);
        //获取6位时间戳
        String timeRandomCode = StringUtils.getTimeRandomCode(6);
        cla.setClassCreateCode(timeRandomCode);
        this.insert(cla);
        String userId = ShiroUtils.getUserId();
        classUserService.saveClassUser(classId, userId);
    }

    @Override
    public Class getClassByClassCode(String classCode) {
        return classMapper.getClassByClassCode(classCode);
    }

    @Override
    public List<Class> getClassByClassName(String className[]) {
        EntityWrapper<Class> wrapper = new EntityWrapper<>();
        wrapper.in("class_name", className);
        List<Class> classes = this.selectList(wrapper);
        return classes;
    }

    @Override
    public Class getClassByClassNameAndCurId(String className) {
        String userId = ShiroUtils.getUserId();
        return classMapper.getClassByClassNameAndCurId(className, userId);
    }

    @Override
    public PageInfo<ClassVo> listPage(ClassSearchVo searchVo, int pageNum, int pageSize) {
        if(searchVo==null){
            searchVo=new ClassSearchVo();
        }
        User userEntity = ShiroUtils.getUserEntity();

        Integer roleId = userEntity.getRoleId();
        if(roleId==1){
            throw new BusinessException(ErrorEnum.UNAUTHORIZED.setMsg("权限不够，禁止访问"));
        }else if(roleId==2){
            String userId = userEntity.getId();
            searchVo.setCreatorId(userId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ClassVo> userInfoVos = classMapper.listPage(searchVo);
        return new PageInfo<>(userInfoVos);
    }


}
