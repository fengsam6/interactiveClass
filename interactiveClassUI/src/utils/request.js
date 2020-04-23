//es6
import {getToken} from '@/utils/tokenUtil'
import config from "./config";
import {errorAlert} from './alert'

const url_base = config.url_base
const OK = 200
const jsonDataType = 'json'
const formContentType = 'application/x-www-form-urlencoded'
export default function request(params) {
    let url = params.url
    //设置url前缀
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = url_base + url;
        params.url = url
    }
    //超时时间5000ms
    params.timeout = 5000
    let dataType = params.dataType
    let header = {}
    debugger
    if (dataType == undefined || dataType == null || dataType != jsonDataType) {
        //默认是json格式
        //请求参数类型改为application/x-www-form-urlencoded
        header['content-type'] = formContentType
    }

    const token = getToken()
    if (token != null) {
        header['USER-TOKEN'] = token
        // header = {
        //     'content-type': 'application/x-www-form-urlencoded',
        //     'USER-TOKEN': token
        // }
    }
    params.header = header
    return new Promise((resolve, reject) => {
        //加载中
        uni.showLoading({
            title: "加载中"
        })
        uni.request({
            ...params,
            success(res) {
                const data = res.data
                let code = data.code
                console.debug(data)

                if (code == OK) {
                    //code==200，成功，直接返回data.data
                    resolve(data.data);
                } else {
                    //code!=200 提示错误信息
                    const msg = data.message
                    errorAlert(msg)
                    reject(data);
                }
            },
            fail(err) {
                const msg = err || "服务器繁忙，请稍后访问"
                errorAlert(msg)
                reject(err);
            },
            complete() {
                uni.hideLoading();
            }
        })
    })
}

/**
 *如果发送json请求参数，dataType值为json
 * @param url
 * @param data
 * @param dataType 如果值为json，请求参数为json格式
 * @returns {*|Promise<unknown>}
 */
export function get(url, data, dataType) {
    return request({url: url_base + url, method: "GET", data})
}

/**
 * 发送json请求参数
 * @param url
 * @param data
 * @returns {*|Promise<unknown>}
 */
export function getWithJsonData(url, data) {
    return get(url, data, jsonDataType)
}
/**
 *如果发送json请求参数，dataType值为json
 * @param url
 * @param data
 * @param dataType 如果值为json，请求参数为json格式
 * @returns {*|Promise<unknown>}
 */
export function post(url, data, dataType) {
    //数据转换为 query string
    return request({url: url_base + url, method: "POST", data})
}


/**
 * 发送json请求参数
 * @param url
 * @param data
 * @returns {*|Promise<unknown>}
 */
export function postWithJsonData(url, data) {
    return post(url, data, jsonDataType)
}