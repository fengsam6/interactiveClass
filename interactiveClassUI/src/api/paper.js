import {get, post} from "@/utils/request"

export function createPaper(data) {
    return post("paper/createPaper", data)
}
export function queryPaper(data) {
    return post("paper/queryPaper", data,'json')
}