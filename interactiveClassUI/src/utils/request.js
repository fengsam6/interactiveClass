//es6
const url_base = "http://localhost:8080/"
const OK = 200
export default function request(params) {
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

                //code!=200 直接reject
                if (code !== OK) {
                    const msg = data.message
                    uni.showToast({
                        title: msg,
                        duration: 2000
                    });
                    reject(data);
                }

                resolve(res.data);
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
    return request({url: url_base + url, method: "POST", data})
}