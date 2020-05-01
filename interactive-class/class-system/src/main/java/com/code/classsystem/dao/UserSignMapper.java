package com.code.classsystem.dao;

import com.code.classsystem.entity.UserSign;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.code.classsystem.vo.SignVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coder
 * @since 2020-04-05
 */
public interface UserSignMapper extends BaseMapper<UserSign> {
    SignVo querySignInfo(UserSign userSign);
}
