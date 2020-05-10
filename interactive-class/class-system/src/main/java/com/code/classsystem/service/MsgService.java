package com.code.classsystem.service;

import com.code.classsystem.entity.Msg;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-05-10
 */
public interface MsgService extends IService<Msg> {

    List<Msg> listLastN(String classId, int n);
}
