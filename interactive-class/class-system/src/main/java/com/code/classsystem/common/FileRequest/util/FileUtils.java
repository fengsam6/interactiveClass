package com.code.classsystem.common.FileRequest.util;

import com.code.core.enums.ErrorEnum;
import com.code.core.exception.FileException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;

public class FileUtils {

    public static void fileWrite(FileInputStream fis, OutputStream os) throws IOException {
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        if (filePath.contains("/")) {
            filePath = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return getShortName(filePath);
    }

    public static boolean isRelativePath(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }
        if (filePath.startsWith(".") || filePath.startsWith("./")) {
            return true;
        }
        return false;
    }


    public static void checkFileDir(File dest) {
        // 判断路径是否存在，如果不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
    }


    public static String getFileDownName(String fileName, String userAgent) throws UnsupportedEncodingException {
//        if (userAgent.contains("firefox")) {
//            // firefox浏览器
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
//        } else if (userAgent.contains("MSIE")) {
//            // IE浏览器
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        } else if (userAgent.contains("CHROME")) {
//            // 谷歌
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
//        }
        if (userAgent.toLowerCase().indexOf("msie") > 0
                || userAgent.indexOf("like Gecko") > 0) {
            // IE浏览器
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,
            // 这个文件名称用于浏览器的下载框中自动显示的文件名
            fileName = new String(fileName.replaceAll(" ", "").getBytes("UTF-8"), "ISO8859-1");
        }
        return fileName;
    }

    public static String getFileExt(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static boolean checkFileEtc(String fileName, String[] fileExts) {
        String fileExt = getFileExt(fileName);
        boolean result = false;
        for (String fileAllowedExt : fileExts) {
            if (fileAllowedExt.equals(fileExt)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean checkImgEtc(String fileName) {
        String imgExt[] = {"jpg", "gif", "jpeg"};
        boolean result = checkFileEtc(fileName, imgExt);
        if (!result) {
            throw new FileException(ErrorEnum.FILE_FORMAT_ERROR.setMsg("请上传图片"));
        }
        return result;
    }

    public static String getShortName(String fileName) {
        int length = fileName.length();
        if (length >= 12) {
            fileName = fileName.substring(length - 12 , length);
        }
        return fileName;
    }

    public static String getRelativePath(String fileName) {
        // 防止文件重命名，防止重复
        return com.code.core.util.StringUtils.getFileSaveDirStr() + "/" + com.code.core.util.StringUtils.getTimeRandomCode(6) + "_" + fileName;
    }
}
