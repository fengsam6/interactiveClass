import { get, post } from '@/utils/request'

export function listClassByUserId(params) {
  return get('/classUser/listClassByUserId', params)
}

export function listTeacherClasses(params) {
  return get('/classUser/listTeacherClasses', params)
}

export function getClassById(classId) {
  return get('/class/getClassById/' + classId)
}

export function listPage(params) {
  return get('/class/listPage', params)
}

export function add(data) {
  return post('/class/createClass', data)
}

export function update(data) {
  return post('/class/updateClass', data)
}

export function deleteById(params) {
  return post('/class/deleteClass', params)
}

