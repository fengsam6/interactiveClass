package com.code.classsystem.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.code.classsystem.entity.Notice;
import com.code.classsystem.service.NoticeService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 *  前端控制器
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
    public ResponseResult addNotice(Notice notice){
        noticeService.addNotice(notice);
        return ResponseResultUtil.renderSuccessMsg("创建通知成功！");
    }

    @ApiOperation(value = "查询通知接口", notes = "查询通知接口")
    @PostMapping("/queryNotice")
    public ResponseResult queryNotice(@RequestBody JSONObject data){
        int page= (int) data.get("page");
        int limit= (int) data.get("limit");
        String classId= (String) data.get("classId");
        String createUserId= (String) data.get("publishUserId");
        String courseId=(String) data.get("courseId");
        Notice notice=new Notice();
        notice.setClassId(classId);
        notice.setCourseId(courseId);
        notice.setPublishUserId(createUserId);
        List<Notice>data2=noticeService.queryNotice(notice,page,limit);
        return ResponseResultUtil.renderSuccess(data2);
    }

}

