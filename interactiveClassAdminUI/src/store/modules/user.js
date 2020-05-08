import { doLogin, logout, getUserInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    roles: [],
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_ROLE: (state, role) => {
    state.roles.push(role)
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  async login({ commit }, userInfo) {
    // const { userAccount, password } = userInfo
    const { data } = await doLogin(userInfo)
    const token = data
    commit('SET_TOKEN', token)
    setToken(token)
    return token
  },

  // get user info
  async getInfo({ commit }) {
    const { data } = await getUserInfo()
    const { name, avatar,roleCode } = data
    commit('SET_NAME', name)
    commit('SET_AVATAR', avatar)
    commit('SET_ROLE', roleCode)
    return data
  },

  // user logout
  async logout({ commit }) {
    await logout()
    removeToken() // must remove  token  first
    resetRouter()
    commit('RESET_STATE')
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

