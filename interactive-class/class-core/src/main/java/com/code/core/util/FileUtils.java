package com.code.core.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {

    public static void fileWrite(FileInputStream fis, OutputStream os) throws IOException {
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            while((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } finally {
            if(fis!=null){
                fis.close();
            }
            if(os!=null){
                os.close();
            }
        }

    }


    public static String getFileName(String filePath){
        if(StringUtils.isEmpty(filePath)){
            return  null;
        }
        if(filePath.contains("/")){
            return filePath.substring(filePath.lastIndexOf("/")+1);
        }
        return  filePath;
    }

    public static boolean isRelativePath(String filePath){
        if(StringUtils.isEmpty(filePath)){
            return  false;
        }
        if(filePath.startsWith(".")||filePath.startsWith("./")){
            return true;
        }
        return  false;
    }


    public static void checkFileDir(File dest) {
        // 判断路径是否存在，如果不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
    }
}
