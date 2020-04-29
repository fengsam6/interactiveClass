package com.code.classsystem.service;

import com.code.classsystem.entity.Paper;
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
public interface PaperService extends IService<Paper> {
    boolean createPaper(Paper paper);

    List<Paper> queryPaper(Paper paper, int page, int limit);
}
