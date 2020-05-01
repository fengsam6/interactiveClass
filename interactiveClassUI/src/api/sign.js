import {get, post} from "@/utils/request"


/**
 * 签到
 * @param data
 * @returns {*}
 */
export function signIn(data) {
    return post("userSign/signIn", data)
}

/**
 * 签退
 * @param data
 * @returns {*}
 */
export function signOut(data) {
    return post("userSign/signOut", data)
}

/**
 * 查询个人签到情况
 * @param data
 * @returns {*}
 */
export function queryMySignInfo(data) {
    return post("userSign/queryMySignInfo", data)
}
