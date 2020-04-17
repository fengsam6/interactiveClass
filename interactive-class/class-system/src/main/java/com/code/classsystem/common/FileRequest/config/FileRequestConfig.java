package com.code.classsystem.common.FileRequest.config;

import com.code.classsystem.common.FileRequest.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
@Configuration
public class FileRequestConfig {
    @Value("${system.fileUpload.path}")
    private String fileUploadPath;
    @Value("${system.img.uploadDir}")
    private String imgUploadPath;

    /**
     * 文件存储位置
     * @return
     */
    public String getFileUploadPath() {
        if(FileUtils.isRelativePath(fileUploadPath)){
            File file = new File(fileUploadPath);
            return file.getAbsolutePath();
        }

        return fileUploadPath;
    }

    public String getImgUploadDirPath() {
        return imgUploadPath;
    }
}
