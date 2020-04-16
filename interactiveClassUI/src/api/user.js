import {get, post} from "@/utils/request"
import {getStorage, setStorage} from '@/utils/storage'

const userStoreKey = "user"

/**
 * 登录接口
 * @param data
 * @returns {*}
 */
export function doLogin(data) {
    return post("user/login", data)
}

/**
 * 注册接口
 * @param data
 * @returns {*}
 */
export function doRegister(data) {
    return post("user/register", data)
}

export function getUserInfo() {
    return get("user/getUserInfo")
}

export function updateUserInfo(data) {
    return post("user/update", data)

}

export function logout() {
    return get("user/logout")
}

export function saveUserInfoStore(userInfo) {
    if (userInfo == null) {
        userInfo = getUserInfo()
    }
    return setStorage(userStoreKey, userInfo)
}

export async function getStoreUserInfo() {
    let userInfo = getStorage(userStoreKey)
    if (userInfo == null || userInfo == '' || userInfo.name == '') {
        userInfo = await getUserInfo()
        console.log(userInfo)
        saveUserInfoStore(userInfo)
    }
    return userInfo
}

