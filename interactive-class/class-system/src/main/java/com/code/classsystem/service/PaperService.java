package com.code.classsystem.service;

import com.code.classsystem.entity.Paper;
import com.baomidou.mybatisplus.service.IService;
import com.code.classsystem.vo.PaperInfoVo;
import com.code.classsystem.vo.PaperResultBinVo;
import com.github.pagehelper.PageInfo;

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
    PageInfo<PaperInfoVo> listPage(Paper paper, int pageNum, int pageSize);

    List<Paper> queryAllPaper(int pageNum, int pageSize);

    List<Paper> queryMyPaper(int pageNum, int pageSize);

    List<PaperResultBinVo> queryPaperResult(String courseId);

    boolean delPaper(String paperId);
}
