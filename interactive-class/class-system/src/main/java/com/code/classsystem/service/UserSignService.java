package com.code.classsystem.service;

import com.code.classsystem.entity.UserSign;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.SignVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface UserSignService extends IService<UserSign> {
    boolean doSign(UserSign userSign);
    SignVo querySignInfo(UserSign userSign);
}
