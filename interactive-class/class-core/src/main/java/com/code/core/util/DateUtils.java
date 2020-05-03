package com.code.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getTimeStr(String dateFormatStr) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(dateFormatStr);
        return dateFormat1.format(new Date());
    }

    public static String getFileSaveDirPathStr() {
        String dateFormatStr="yyyyMM";
        return getTimeStr(dateFormatStr);
    }

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final SimpleDateFormat TIME_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    public static String getCurTimeStr() {
        return TIME_FORMAT.format(new Date());
    }
}
