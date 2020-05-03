import { get, post } from '@/utils/request'

export function listPage(params) {
  return get('/paper/listPage', params)
}
