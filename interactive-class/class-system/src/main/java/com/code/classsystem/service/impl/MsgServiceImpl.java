package com.code.classsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.code.classsystem.entity.Msg;
import com.code.classsystem.dao.MsgMapper;
import com.code.classsystem.service.MsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author coder
 * @since 2020-05-10
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {

    @Override
    public List<Msg> listLastN(String classId, int n) {
        Wrapper<Msg> msgWrapper =new EntityWrapper<>();
        msgWrapper.eq("class_id",classId);
        msgWrapper.orderBy("send_time",false);

        PageHelper.startPage(0,n);
        List<Msg> msgs = this.selectList(msgWrapper);
        Collections.reverse(msgs);
        return msgs;
    }
}
