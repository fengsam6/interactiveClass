//request请求，用于前端调用接口
import {getToken} from '@/utils/tokenUtil'
import config from "./config";
import {errorAlert} from './alert'
//后端请求根路径url
const url_base = config.url_base
const OK = 200
//定义json格式
const jsonDataType = 'json'
const formContentType = 'application/x-www-form-urlencoded'

/**
 * 判断是否是http url
 * @param url
 * @returns {boolean}
 */
function isHttpUrl(url) {
    // 微信小程序，判断是否相等，不能用“===”，使用“===”报错
    if (url == undefined || url == null) {
        console.log("请求url错误")
        return false;
    }
    if (url.startsWith("http://") || url.startsWith("https://")) {
        return true
    }
    return false;
}

export default function request(params) {
    let url = params.url
    //设置url前缀
    if (!isHttpUrl(url)) {
        url = url_base + url;
        params.url = url
    }
    //超时时间5000ms
    params.timeout = 5000
    let dataType = params.dataType
    let header = {}
    if (dataType == undefined || dataType == null || dataType.toLowerCase() != jsonDataType) {
        //默认是json格式
        //请求参数类型改为application/x-www-form-urlencoded，即请求数据转换为 query string
        header['content-type'] = formContentType
    }

    const token = getToken()
    if (token != null) {
        header['USER-TOKEN'] = token
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
                //控制台打印数据
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
 * @param url 传入后端相对路径，比如：user/login (/user/login 也可以)
 * @param data
 * @param dataType 如果值为json，请求参数为json格式
 * @returns {*|Promise<unknown>}
 */
export function get(url, data, dataType) {
    return request({url: url_base + url, method: "GET", data, dataType})
}

/**
 * 发送json请求参数
 * @param url 传入后端相对路径，比如：user/login (/user/login 也可以)
 * @param data
 * @returns {*|Promise<unknown>}
 */
export function getWithJsonData(url, data) {
    return get(url, data, jsonDataType)
}

/**
 *如果发送json请求参数，dataType值为json
 * @param url 传入后端相对路径，比如：user/login (/user/login 也可以)
 * @param data
 * @param dataType 如果值为json，请求参数为json格式
 * @returns {*|Promise<unknown>}
 */
export function post(url, data, dataType) {
    return request({url: url_base + url, method: "POST", data, dataType})
}


/**
 * 发送json请求参数
 * @param url 传入后端相对路径 比如：user/login (/user/login 也可以)
 * @param data
 * @returns {*|Promise<unknown>}
 */
export function postWithJsonData(url, data) {
    return post(url, data, jsonDataType)
}