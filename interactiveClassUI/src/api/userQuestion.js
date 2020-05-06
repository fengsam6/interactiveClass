import {get, post} from "@/utils/request"

export function subAnswer(data) {
    return post("userQuestion/subAnswer", data,'json')
}
export function queryMyResult(data) {
    return post("userQuestion/queryMyResult", data)
}