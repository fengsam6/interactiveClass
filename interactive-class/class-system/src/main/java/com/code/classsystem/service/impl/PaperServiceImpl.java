package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Paper;
import com.code.classsystem.dao.PaperMapper;
import com.code.classsystem.service.PaperService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.vo.PaperInfoVo;
import com.code.core.util.DateUtils;
import com.code.core.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Override
    public boolean createPaper(Paper paper) {
        String id= UUIDUtil.getUUid();
        String userId=ShiroUtils.getUserId();
        String curTime= DateUtils.getTimeStr("yyyy-mm-dd hh:mm:ss");
        paper.setPublishTime(curTime);
        paper.setId(id);
        paper.setPublishUserId(userId);
        return this.insert(paper);
    }

    @Override
    public List<Paper> queryPaper(Paper paper, int page, int limit) {
        Page<Paper> paperPage=new Page<>(page,limit);
        EntityWrapper<Paper> wrapper=new EntityWrapper<>();
        wrapper.eq("class_id",paper.getClassId());
        wrapper.eq("publish_user_id",paper.getPublishUserId());
        wrapper.eq("course_id",paper.getCourseId());
        wrapper.orderBy("publish_time",false);
        List<Paper>list=this.selectPage(paperPage,wrapper).getRecords();
        return list;
    }

    @Override
    public PageInfo<PaperInfoVo> listPage(Paper paper,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
       paper.setPublishUserId(ShiroUtils.getUserId());
        List<PaperInfoVo> paperInfoVoList=paperMapper.listPage(paper);
        return new PageInfo<>(paperInfoVoList);
    }
}
