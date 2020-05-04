package com.code.classsystem.common.FileRequest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 *springboot圩您路径，配置外部图片访问
 * 图片访问url： 项目根路径+/img/+relativePath
 * 文件访问url： 项目根路径+/file/+relativePath
 */
public class FileRequestMappingConfig implements WebMvcConfigurer {
    @Autowired
    private FileRequestConfig fileRequestConfig;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置图片虚拟路径映射，
        String imgRequestUrl = fileRequestConfig.getImgRequestUrl();
        String imgLocation = fileRequestConfig.getImgUploadDirPath();
//        如果请求url是imgRequestUrl，则到imgLocation文件位置寻找图片
        //http://localhost:8080//img/202004/p.jpg  浏览 upload/img/202004/p.jpg
        registry.addResourceHandler(imgRequestUrl).addResourceLocations("file:///" + imgLocation);

        //配置文件虚拟路径映射
// http://localhost:8080/file/202005/066187_springboot框架开发手册.md  下载upload/file/202005/066187_springboot框架开发手册.md
        String fileRequestUrl = fileRequestConfig.getFileRequestUrl();
        String fileUploadPath = fileRequestConfig.getFileUploadPath();
        //        如果请求url是fileRequestUrl，则到fileUploadPath文件位置寻找文件
        registry.addResourceHandler(fileRequestUrl).addResourceLocations("file:///" + fileUploadPath);
    }
}

