package com.code.classsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 *springboot圩您路径，配置外部图片访问
 */
public class ImgRequestMappingConfig implements WebMvcConfigurer {
    @Value("${system.img.requestUrl}")
    private String imgRequestUrl;
    @Value("${system.img.uploadDir}")
    private String imgLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler(imgRequestUrl).addResourceLocations(imgLocation);
    }
}

