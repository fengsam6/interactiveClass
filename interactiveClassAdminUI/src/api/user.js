import {get, post} from '@/utils/request'

/**
 * 登录接口
 * @param data
 * @returns {*}
 */
export function doLogin(data) {
  return post('/user/login', data)
}

/**
 * 添加用户
 * @param data
 * @returns {*}
 */
export function add(data) {
  return post('/user/add', data)
}

export function update(data) {
  return post('/user/update', data)
}

export function getUserInfo() {
  return get('/user/getUserInfo')
}

export function updateUserInfo(data) {
  return post('/user/update', data)
}

export function logout() {
  return get('/user/logout')
}

export function listPage(data) {
  return get('/user/listPage', data)
}

export function getUserInfoById(userId) {
  return get('/user/getUserInfoById/' + userId)
}

export function deleteUserById(params) {
  return post('/user/deleteByIds', params)
}
