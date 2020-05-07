package com.code.classsystem.controller;


import com.code.classsystem.entity.HomeWork;
import com.code.classsystem.service.HomeWorkService;
import com.code.classsystem.vo.HomeWorkVo;
import com.code.core.entity.ResponseResult;
import com.code.core.util.ResponseResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coder
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/homework")
public class HomeWorkController {
    @Autowired
private HomeWorkService homeWorkService;
    @ApiOperation(value = "创建作业接口", notes = "创建作业接口")
    @PostMapping("/createHomeWork")
    public ResponseResult createHomeWork(@Valid @RequestBody HomeWork homeWork, BindingResult bindingResult) {
        homeWorkService.addHomeWork(homeWork);
        return ResponseResultUtil.renderSuccess("创建作业成功！");
    }

    @ApiOperation(value = "更新作业接口", notes = "更新作业接口")
    @PostMapping("/updateHomeWork")
    public ResponseResult updateHomeWork(@Valid HomeWork homeWork) {
        homeWorkService.updateById(homeWork);
        return ResponseResultUtil.renderSuccess("更新作业成功！");
    }

    @ApiOperation(value = "删除作业接口", notes = "删除作业接口")
    @PostMapping("/deleteHomeWork")
    public ResponseResult deleteHomeWork(@RequestParam("ids") List<String> ids) {
        homeWorkService.deleteBatchIds(ids);
        return ResponseResultUtil.renderSuccess("删除作业成功！");
    }


    @ApiOperation(value = "根据id查看作业", notes = "根据id查看作业")
    @GetMapping("/getDetailById")
    public ResponseResult getDetailById(String id) {
        HomeWorkVo homeWork = homeWorkService.getDetailById(id);
        return ResponseResultUtil.renderSuccess(homeWork);
    }

    @ApiOperation(value = "分页查询作业", notes = "分页查询作业")
    @GetMapping("/listPage")
    public ResponseResult listPage(HomeWork homeWork, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<HomeWorkVo> homeWorkVoPageInfo = homeWorkService.listPage(homeWork, pageNum, pageSize);
        return ResponseResultUtil.renderSuccess(homeWorkVoPageInfo);
    }
}

