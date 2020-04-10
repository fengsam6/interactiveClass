package com.code.classsystem.controller.FileRequest;

import com.code.classsystem.config.FileRequestConfig;
import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.util.FileUtils;
import com.code.core.util.ResponseResultUtil;
import com.code.core.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@Api(value = "文件接口", tags = "文件接口")
public class FileRequestController {
    @Autowired
    private FileRequestConfig fileRequestConfig;


    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
        String fileUploadDir = fileRequestConfig.getFileUploadUrl();
        // 获取原始名字
        String fileName = file.getOriginalFilename();

        // 文件重命名，防止重复
        String relativePath = StringUtils.getFileSaveDirStr() + "/" + StringUtils.getTimeRandomCode(6) + "_" + fileName;
        // 文件保存路径
        String absolutePath = fileUploadDir + "/" + relativePath;
        // 文件对象
        File dest = new File(absolutePath);
        FileUtils.checkFileDir(dest);
        try {
            // 保存到服务器中
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResultUtil.renderError(ErrorEnum.FILE_UPLOAD_FAIL);
        }
        return ResponseResultUtil.renderSuccess("上传文件", relativePath);
    }


    @GetMapping("/download")
    public void download(String filePath, HttpServletResponse response) throws Exception {
        String fileUploadDir = fileRequestConfig.getFileUploadUrl();
        // 文件地址，真实环境是存放在数据库中的
        String absolutePath = fileUploadDir + "/" + filePath;
        File file = new File(absolutePath);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        String fileName = FileUtils.getFileName(filePath);
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        // 创建输出对象
        OutputStream os = response.getOutputStream();
        FileUtils.fileWrite(fis, os);
    }


}
