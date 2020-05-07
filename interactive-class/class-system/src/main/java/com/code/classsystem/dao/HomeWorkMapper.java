package com.code.classsystem.dao;

import com.code.classsystem.entity.HomeWork;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.vo.HomeWorkVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
public interface HomeWorkMapper extends BaseMapper<HomeWork> {

    List<HomeWorkVo> listPage(HomeWork homeWork);

    HomeWorkVo getDetailById(String id);
}
