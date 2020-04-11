//es6
import {getToken} from '@/utils/tokenUtil'
import config from "./config";
const url_base = config.url_base
const OK = 200
export default function request(params) {
    let url = params.url
    //设置url前缀
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = url_base + url;
        params.url = url
    }
    //超时时间5000ms
    params.timeout = 5000
    let header = {'content-type': 'application/x-www-form-urlencoded'}
    const token = getToken()
    if (token != null) {
        header = {
            'content-type': 'application/x-www-form-urlencoded',
            'USER-TOKEN': token
        }
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
                    uni.showToast({
                        title: msg,
                        image: '/static/icon/error.png',
                        duration: 4000
                    });
                    reject(data);
                }
            },
            fail(err) {
                uni.showToast({
                    title: err || "服务器繁忙，请稍后访问",
                    image: '/static/icon/error.png',
                    duration: 4000
                });
                reject(err);
            },
            complete() {
                uni.hideLoading();
            }
        })
    })
}

export function get(url, data) {
    return request({url: url_base + url, method: "GET", data})
}

export function post(url, data) {
    //数据转换为 query string

    return request({url: url_base + url, method: "POST", data})
}