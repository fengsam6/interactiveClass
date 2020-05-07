import { get, post } from '@/utils/request'

export function listPage(params) {
  return get('/course/listPage', params)
}

export function addCourse(params) {
  return post('/course/addCourse/', params, 'json')
}

export function deleteCourseByIds(params) {
  return post('/course/deleteCourse/', params)
}

export function updateCourse(params) {
  return post('/course/updateCourse/', params, 'json')
}

export function getDetailById(id) {
  return get('/course/getDetailById/' + id)
}

export function queryCourseInfoByCurUser() {
  return post('/course/queryCourseInfo')
}

