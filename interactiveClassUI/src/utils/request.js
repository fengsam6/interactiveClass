//es6
const url_base = "http://localhost:8080/"
const OK = 200
export default function request(params) {
    let url = params.url
    //设置url前缀
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = url_base + url;
    }
    //超时时间5000ms
    params.timeout = 5000
    params.url = url
    const token = uni.getStorage({key: "user-token"})
    if (token != null) {
        params.header["USER-TOKEN"] = token
    }
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
                //code!=200 提示错误信息
                if (code != OK) {
                    const msg = data.message
                    uni.showToast({
                        title: msg,
                        image: '/static/icon/error.png',
                        duration: 4000
                    });
                    reject(data);
                } else {
                    resolve(data);
                }
            },
            fail(err) {
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
    const header = {'content-type': 'application/x-www-form-urlencoded'}
    return request({url: url_base + url, header, method: "POST", data})
}