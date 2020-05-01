// 本地开发url
const local_url = "http://192.168.0.104:8080/"
//const local_url = "http://localhost:8080/"
//真机测试url（内网IP或者外网ip）
const test_url = "http://192.168.0.104:8080/"
const env = 'local'

let url_base = local_url
if (env != 'local') {
    url_base = test_url
}
let websocketUrl=url_base.replace("http","ws")+'imServer/'
export const fileUploadUrl = url_base + "/file/upload"
export const imgUploadUrl = url_base + "/file/uploadImg"
export const imgAccessUrl = url_base + "/img/"
export default {
    url_base: url_base,
    websocketUrl: websocketUrl
}