package com.code;

import com.code.classgenerator.gen.Generator;

public class GenCode {
    public static void main(String[] args) {
        //指定包名
        String packageName = "com.code.classsystem";
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        //指定生成的表名
        String[] tableNames = new String[]{"course", "course_resource", "notice","paper","paper_question"};
        Generator generator = new Generator();
        generator.generateByTables(serviceNameStartWithI, packageName, tableNames);
    }
}
