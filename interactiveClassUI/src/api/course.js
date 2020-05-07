import {get, post} from "@/utils/request"
import {getStorage, setStorage} from '@/utils/storage'

/**
 * 创建课程
 * @param data
 * @returns {*}
 */
export function createCourse(data) {
    return post("course/createCourse", data)
}

/**
 * 我学的课程
 * @param data
 * @returns {*}
 */
export function queryMCourse(data) {
    return post("course/queryCourseInfo", data)
}

/**
 * 查询老师教的课程
 * @param data
 * @returns {*}
 */
export function queryTeachCourse(data) {
    return post("course/queryTeachCourse", data)
}
