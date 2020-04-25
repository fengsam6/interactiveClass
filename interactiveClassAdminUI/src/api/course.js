import request from '@/utils/request'


export function getList(params) {
  return request({
    url: '/course/list',
    method: 'get',
    params
  })
}
