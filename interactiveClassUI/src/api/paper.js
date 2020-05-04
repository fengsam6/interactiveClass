import {get, post} from "@/utils/request"

export function createPaper(data) {
    return post("paper/createPaper", data)
}
export function queryPaper(data) {
    return post("paper/queryPaper", data,'json')
}
export function queryPaperQuestionById(params) {
    return get('/paperQuestion/queryPaperQuestionById', params)
}
export function queryMyPaper(params) {
    return get('/paper/queryMyPaper', params)
}
export function queryAllPaper(params) {
    return get('/paper/queryAllPaper', params)
}