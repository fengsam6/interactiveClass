import request from '@/utils/request'

export function listPage(params) {
  return request({
    url: '/course/listPage',
    method: 'get',
    params
  })
}
