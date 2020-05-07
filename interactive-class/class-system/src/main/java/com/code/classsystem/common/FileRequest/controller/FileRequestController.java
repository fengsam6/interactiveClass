package com.code.classsystem.common.FileRequest.controller;

import com.code.classsystem.common.FileRequest.config.FileRequestConfig;
import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.classsystem.common.FileRequest.util.FileUtils;
import com.code.core.util.ResponseResultUtil;
import com.code.core.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
        String fileUploadDir = fileRequestConfig.getFileUploadPath();
        // 获取原始名字
        String fileName = file.getOriginalFilename();

        String relativePath = FileUtils.getRelativePath(fileName);
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
        return ResponseResultUtil.renderSuccess(relativePath, "上传文件");
    }

    @PostMapping("/uploadImg")
    public ResponseResult uploadImg(@RequestParam("file") MultipartFile file) {
        String fileUploadDir = fileRequestConfig.getImgUploadDirPath();
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        fileName = FileUtils.getShortName(fileName);
        FileUtils.checkImgEtc(fileName);
        String relativePath = FileUtils.getRelativePath(fileName);
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
        return ResponseResultUtil.renderSuccess(relativePath, "上传图片成功");
    }


    @GetMapping("/download")
    public void download(String filePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileUploadDir = fileRequestConfig.getFileUploadPath();
        // 文件地址，真实环境是存放在数据库中的
        String absolutePath = fileUploadDir + "/" + filePath;
        File file = new File(absolutePath);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        String fileName = FileUtils.getFileName(filePath);
        //获取浏览器名（IE/Chome/firefox）
        String userAgent = request.getHeader("User-Agent");
        fileName = FileUtils.getFileDownName(fileName, userAgent);
        response.reset();
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/octet-stream");

        // 创建输出对象
        OutputStream os = response.getOutputStream();
        try {
            FileUtils.fileWrite(fis, os);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }
}
