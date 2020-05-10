import {get, post} from "@/utils/request"

export function listLastN(data) {
    return get("msg/listLastN",data)
}

