package com.code.classsystem.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
    public static boolean isJsonStr(String str) {
        try {
            JSONObject jsonStr = JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
