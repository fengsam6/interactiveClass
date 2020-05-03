import { get, post } from '@/utils/request'

export function listClassByUserId(params) {
  return get('/classUser/listClassByUserId', params)
}



