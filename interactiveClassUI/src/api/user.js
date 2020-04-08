import {get ,post} from "@/utils/request"

/**
 * 登录接口
 * @param data
 * @returns {*}
 */
export  function doLogin(data) {
   return  post("user/login",data)
}

/**
 * 注册接口
 * @param data
 * @returns {*}
 */
export function doRegister(data) {
    return  post("user/register",data)
}