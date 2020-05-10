package com.code.classsystem.controller;


import com.code.classsystem.entity.Msg;
import com.code.classsystem.service.MsgService;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/msg")
@Api(value = "课堂讨论消息接口", tags = "课堂讨论消息接口")
public class MsgController {
    @Autowired
    private MsgService msgService;
    @ApiOperation(value = "查询班级最近n条消息", notes = "根据id查看作业")
    @GetMapping("/listLastN")
    public ResponseResult listLastN(String classId,@RequestParam(defaultValue = "20") int n){
      List<Msg> msgList = msgService.listLastN(classId,n);
      return ResponseResultUtil.renderSuccess(msgList);
    }
}

