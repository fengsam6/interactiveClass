import {get, post} from "@/utils/request"
import {getStoreUser, saveUserStore} from '@/utils/userStorage'

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

export async function getAndSaveUserInfoStore() {
   const userInfo = await getUserInfo()
    saveUserStore(userInfo)
    return userInfo
}

export async function saveUserInfoStore(userInfo) {
    if (userInfo == null) {
         userInfo = await getUserInfo()
    }
    saveUserStore(userInfo)
    return userInfo
}

export function takeStoreUserInfo() {
    return getStoreUser()
}

export async function getStoreUserInfo() {
    let userInfo = getStoreUser()
    if (userInfo == null || userInfo == '' || userInfo.name == '') {
        userInfo = await getUserInfo()
        console.log(userInfo)
        saveUserStore(userInfo)
    }
    return userInfo
}

