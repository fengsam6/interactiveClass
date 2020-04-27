package com.code.classsystem.service;

import com.code.classsystem.entity.Notice;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
public interface NoticeService extends IService<Notice> {
    boolean addNotice (Notice notice);

    List<Notice> queryNotice(Notice notice, int page, int limit);
}
