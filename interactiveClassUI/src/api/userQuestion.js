import {get, post} from "@/utils/request"

export function subAnswer(data) {
    return post("userQuestion/subAnswer", data,'json')
}