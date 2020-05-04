package com.code.classsystem.common.FileRequest.config;

import com.code.classsystem.common.FileRequest.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Slf4j
@Configuration
public class FileRequestConfig {
    @Value("${system.fileUpload.requestUrl}")
    private String fileRequestUrl;
    @Value("${system.fileUpload.path}")
    private String fileUploadPath;
    @Value("${system.img.requestUrl}")
    private String imgRequestUrl;
    @Value("${system.img.uploadDir}")
    private String imgLocation;

    /**
     * 文件存储位置
     *
     * @return
     */
    public String getFileUploadPath() {
        if (FileUtils.isRelativePath(fileUploadPath)) {
            File file = new File(fileUploadPath);
            fileUploadPath = file.getAbsolutePath()+ "\\";
        }
        if(!fileUploadPath.endsWith("\\")){
            fileUploadPath +="\\";
        }
        log.debug("文件上传位置：{}" , fileUploadPath);
        return fileUploadPath;
    }

    public String getImgUploadDirPath() {
        if (FileUtils.isRelativePath(imgLocation)) {
            File file = new File(imgLocation);
            imgLocation = file.getAbsolutePath() + "\\";
        }
        if(!imgLocation.endsWith("\\")){
            imgLocation +="\\";
        }
        log.debug("图片上传位置:{}", imgLocation);
        return imgLocation;
    }

    public String getImgRequestUrl() {
        return imgRequestUrl;
    }

    public String getFileRequestUrl() {
        return fileRequestUrl;
    }
}
