import permission from './permission'

const install = function(Vue) {
  Vue.directive('permission', permission)
}
// vue 自定义permission指令
if (window.Vue) {
  window['permission'] = permission
  Vue.use(install); // eslint-disable-line
}

permission.install = install
export default permission
