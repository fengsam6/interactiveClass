package com.code.classsystem.service;

import com.code.classsystem.entity.UserScore;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
public interface UserScoreService extends IService<UserScore> {

    boolean addUserScore(UserScore userScore);

    UserScore queryUserScore(UserScore userScore);
}
