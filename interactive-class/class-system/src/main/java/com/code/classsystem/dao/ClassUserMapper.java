package com.code.classsystem.dao;

import com.code.classsystem.entity.Class;
import com.code.classsystem.entity.ClassStudent;
import com.code.classsystem.entity.ClassUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.entity.User;
import com.code.classsystem.vo.ClassStudentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
@Mapper
public interface ClassUserMapper extends BaseMapper<ClassUser> {

    /**
     * 查看班级所有用户成员
     * @param classId
     * @return
     */
    List<User> showClassUsers(String classId);

    List<Class> listClassByUserId(String userId);

    /**
     * 班级成员查看
     * @return
     */
    List<ClassStudent> queryStuStatus(ClassStudentVo classStudentVo);

    List<Class> listTeacherClasses(String teacherId);
}
