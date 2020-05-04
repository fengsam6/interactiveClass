import { get, post } from '@/utils/request'

export function listPage(params) {
  return post('/courseResource/listPage', params)
}

export function myCourses(params) {
  return post('/courseResource/myCourses', params)
}
