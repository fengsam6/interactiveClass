package com.code.core.util;

public class StringUtils {
    public  static String getTimeRandomCode(int n){
       String timeRandomCode = System.currentTimeMillis()+"";
        int length = timeRandomCode.length();
        int start = length - n;
        if(start<0){
            start = 0;
        }
        return timeRandomCode.substring(start,length);
    }

    public static String getFileSaveDirStr(){
        String timeStr = DateUtils.getFileSaveDirPathStr();
        return timeStr;
    }
}
