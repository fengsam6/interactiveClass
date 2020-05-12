import Vue from 'vue'
import App from './App'
import request,{get,post} from "./utils/request"
import {successAlert} from './utils/alert'
import  './style/uni-common.css'
Vue.config.productionTip = false
Vue.prototype.request=request;
Vue.prototype.get=get;
Vue.prototype.post=post;
Vue.prototype.successAlert=successAlert;
App.mpType = 'app'

import permission from '@/directive/permission/index' // vue 自定义permission指令
Vue.use(permission)
const app = new Vue({
  ...App
})
app.$mount()
