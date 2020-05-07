import {get, post} from "@/utils/request"

export function addUserScore(data) {
    return post("userScore/addUserScore", data)
}
export function queryUserScore(data) {
    return post("userScore/queryUserScore", data)
}