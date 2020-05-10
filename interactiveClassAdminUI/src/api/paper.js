import { get, post } from '@/utils/request'

export function listPage(params) {
  return get('/paper/listPage', params)
}
export function addQuestion(params) {
  return post('/paperQuestion/addQuestion', params)
}
export function queryPaperQuestionById(params) {
  return get('/paperQuestion/queryPaperQuestionById', params)
}
export function delPaper(params) {
  return get('/paperQuestion/delPaper', params)
}
