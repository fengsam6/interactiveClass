package com.code.classsystem.controller;

import com.code.classsystem.entity.Paper;
import com.code.classsystem.service.PaperService;
import com.code.classsystem.vo.PaperVo;
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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/paper")
@Api(value = "试卷接口", tags = "试卷接口")
public class PaperController {

    @Autowired
    private PaperService paperService;
    @ApiOperation(value = "创建试卷接口", notes = "创建试卷接口")
    @PostMapping("/createPaper")
    public ResponseResult createPaper(Paper paper) {
        paperService.createPaper(paper);
        return ResponseResultUtil.renderSuccessMsg("创建试卷成功！");
    }

    @ApiOperation(value = "查询试卷接口", notes = "查询试卷接口")
    @PostMapping("/queryPaper")
    public ResponseResult queryPaper(@RequestBody PaperVo paperVo) {
        int page = paperVo.getPage();
        int limit = paperVo.getLimit();
        Paper paper=new Paper();
        BeanUtils.copyProperties(paperVo, paper);
        List<Paper> data = paperService.queryPaper(paper, page, limit);
        return ResponseResultUtil.renderSuccess(data);
    }

}

