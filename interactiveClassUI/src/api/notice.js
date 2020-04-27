import {get, post} from "@/utils/request"
import {getStorage, setStorage} from '@/utils/storage'

export function addNotice(data) {
    return post("notice/addNotice", data)
}
export function queryNotice(data) {
    return post("notice/queryNotice", data,'json')
}