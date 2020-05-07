package com.code.classsystem.service;

import com.code.classsystem.entity.HomeWork;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.HomeWorkVo;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
public interface HomeWorkService extends IService<HomeWork> {

    void addHomeWork(HomeWork homeWork);

    PageInfo<HomeWorkVo> listPage(HomeWork homeWork, int pageNum, int pageSize);

    HomeWorkVo getDetailById(String id);
}
