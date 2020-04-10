package com.code.core.util;

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
}
