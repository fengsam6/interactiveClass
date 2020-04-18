package com.code.classsystem.common.FileRequest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 *springboot圩您路径，配置外部图片访问
 */
public class ImgRequestMappingConfig implements WebMvcConfigurer {
    @Autowired
    private FileRequestConfig fileRequestConfig;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imgRequestUrl = fileRequestConfig.getImgRequestUrl();
        String imgLocation = fileRequestConfig.getImgUploadDirPath();
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler(imgRequestUrl).addResourceLocations("file:///" + imgLocation);
    }
}

