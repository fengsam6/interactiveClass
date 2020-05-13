package com.code.classsystem.service;

import com.code.classsystem.entity.Class;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.ClassSearchVo;
import com.code.classsystem.vo.ClassVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface ClassService extends IService<Class> {

    /**
     * 创建班级
     * @param cla
     */
    void createClass(Class cla);


    Class getClassByClassCode(String classCode);

    List<Class> getClassByClassName(String className[]);

    /**
     * 根据当前用户id，className 查找班级（创建班级时候会校验班级名称是否重复）
     * @param className
     * @return
     */
    Class getClassByClassNameAndCurId(String className);

    PageInfo<ClassVo> listPage(ClassSearchVo cla, int pageNum, int pageSize);
}
