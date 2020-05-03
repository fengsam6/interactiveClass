import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import Qs from 'qs'
const jsonDataType = 'json'
const formDataHeader = 'application/x-www-form-urlencoded'
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 7000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['USER-TOKEN'] = getToken()
    }
    const contentType = config.headers['Content-type']
    if (contentType === null || contentType === '') {
      config.headers['Content-type'] = formDataHeader
    }
    if (config.headers['Content-type'] === formDataHeader) {
      config.data = Qs.stringify(config.data)
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
const OK = 200
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    if (res.code === OK) {
      console.log(res)
      return res
    }
    // if the custom code is not 200, it is judged as an error.
    if (res.code !== OK) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 401: Token expired;
      if (res.code === 401 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('你已经退出系统, 请重新登录或取消', '已经退出系统', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

/**
 * 默认请求参数是form data（包括值为undefined,null），如果只为json，则请求参数为json格式
 * @param url
 * @param params
 * @param dataType
 * @returns {AxiosPromise}
 */
export function get(url, params, dataType) {
  const headers = {}
  if (dataType === undefined || dataType === null || dataType !== jsonDataType) {
    headers['Content-type'] = formDataHeader
  }
  return service({
    url: url,
    method: 'get',
    params,
    headers
  })
}

/**
 * post请求
 * @param url
 * @param data
 * @param dataType 默认请求参数是form data（包括值为undefined,null），如果只为json，则请求参数为json格式
 * @returns {AxiosPromise}
 */
export function post(url, data, dataType) {
  const headers = {}
  if (dataType === undefined || dataType === null || dataType !== jsonDataType) {
    headers['Content-type'] = formDataHeader
  }
  return service({
    url: url,
    method: 'post',
    data,
    headers
  })
}

/**
 * 对外暴露请求封装后的axios
 */
export default service
