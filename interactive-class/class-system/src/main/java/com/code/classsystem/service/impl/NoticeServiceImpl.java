package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.code.classsystem.entity.Notice;
import com.code.classsystem.dao.NoticeMapper;
import com.code.classsystem.service.NoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.code.classsystem.util.DateUtils;
import com.code.core.util.UUIDUtil;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public boolean addNotice(Notice notice) {
        String noticeId = UUIDUtil.getRandomUUID();
        notice.setId(noticeId);
        notice.setPublishTime(DateUtils.getCurTimeStr());
        return this.insert(notice);
    }

    @Override
    public List<Notice> queryNotice(Notice notice,int page,int limit) {
        Page<Notice> noticePage=new Page<>(page,limit);
        EntityWrapper<Notice> wrapper=new EntityWrapper<>();
        wrapper.eq("class_id",notice.getClassId());
        wrapper.eq("course_id",notice.getCourseId());
        wrapper.orderBy("publish_time",false);
        List<Notice>list=this.selectPage(noticePage,wrapper).getRecords();
        return list;
    }
}
