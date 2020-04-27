package com.code.classsystem.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.Notice;
import com.code.classsystem.service.NoticeService;
import com.code.classsystem.vo.NoticeVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/notice")
@Api(value = "通知接口", tags = "通知接口")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "添加通知接口", notes = "添加通知接口")
    @PostMapping("/addNotice")
    public ResponseResult addNotice(Notice notice) {
        notice.setPublishUserId(ShiroUtils.getUserId());
        noticeService.addNotice(notice);
        return ResponseResultUtil.renderSuccessMsg("创建通知成功！");
    }

    @ApiOperation(value = "查询通知接口", notes = "查询通知接口")
    @PostMapping("/queryNotice")
    public ResponseResult queryNotice(@RequestBody NoticeVo noticeVo) {
        int page = noticeVo.getPage();
        int limit = noticeVo.getLimit();

        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeVo, notice);
        List<Notice> data2 = noticeService.queryNotice(notice, page, limit);
        return ResponseResultUtil.renderSuccess(data2);
    }

}

