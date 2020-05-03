import { get, post } from '@/utils/request'

export function listPage(params) {
  return post('/courseResource/listPage', params)
}
