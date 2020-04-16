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

const app = new Vue({
  ...App
})
app.$mount()
