package com.code.core.util;


import com.code.core.entity.ResponseResult;
import com.code.core.error.CommonError;

/**
 * Created by rf on 2019/3/4.
 */
public class ResponseResultUtil {
    public  static ResponseResult renderSuccess(Object data) {
        return renderSuccess(200,data);
    }
    public  static ResponseResult renderSuccessMsg(String msg) {
        return new ResponseResult(200,msg);
    }
    public static ResponseResult renderSuccess(int code,Object data) {
        return new ResponseResult(code,data);
    }

    public static ResponseResult renderSuccess(Object data,String msg) {
        return new ResponseResult(msg,data);
    }
    public static ResponseResult renderError(int code,String msg) {
        return new ResponseResult(code,msg);
    }

    public static ResponseResult renderError(CommonError commonError) {
        return renderError(commonError.getCode(),commonError.getMsg());
    }

}
