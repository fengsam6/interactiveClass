package com.code.classsystem;

import com.code.classsystem.util.ApplicationContextInfoUtils;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages="com.code")
@MapperScan("com.code.classsystem.dao")
public class ClassSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ClassSystemApplication.class, args);
        //打印系统访问信息
        ApplicationContextInfoUtils.printSystemInfo(applicationContext);
    }

}
