import {get, post} from "@/utils/request"
import {getStorage, setStorage} from '@/utils/storage'

export function joinClass(data) {
    return post("classUser/joinClass", data)
}
export function showClassUsers(data) {
    return post("classUser/showClassUsers", data)
}