import { get, post } from '@/utils/request'

export function listPage(params) {
  return get('/homework/listPage', params)
}

export function addHomework(params) {
  return post('/homework/createHomeWork/', params, 'json')
}

export function deleteHomeworkByIds(params) {
  return post('/homework/deleteHomework/', params)
}

export function updateHomework(params) {
  return post('/homework/updateHomework/', params, 'json')
}

export function getDetailById(id) {
  return get('/homework/getDetailById/' + id)
}

