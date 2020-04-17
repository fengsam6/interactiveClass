package com.code.core.util;

public class ResourceUtils {
    public static String getClassPath(){
        return System.getProperty("user.dir");
    }

    public static String getResourcePath(){
        return getClassPath()+"/src/main/resource";
    }

    public static void main(String[] args) {
        System.out.print(getClassPath());
    }
}
