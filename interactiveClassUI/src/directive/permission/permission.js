// import store from '@/store'
import {takeStoreUserInfo} from "@/api/user"
export default {
  inserted(el, binding, vnode) {
    debugger
    const { value } = binding
    const user = takeStoreUserInfo()
    const roles = user.roleCode
    if (value && value instanceof Array && value.length > 0) {
      const permissionRoles = value
      const hasPermission = roles.some(role => {
        return permissionRoles.includes(role)
      })

      if (!hasPermission) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`need roles! Like v-permission="['admin','editor']"`)
    }
  }
}
