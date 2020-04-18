package com.code.classsystem.dao;

import com.code.classsystem.entity.Class;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface ClassMapper extends BaseMapper<Class> {

    Class getClassByClassCode(String classCode);
}
