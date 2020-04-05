package com.code.core.util;

import java.util.UUID;


public class UUIDUtil {
    public static String getUUid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     *get uuid36λ
     * @return
     */
    public static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
