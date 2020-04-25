import { doLogin, logout, getUserInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
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
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    // const { userAccount, password } = userInfo
    doLogin(userInfo).then(resp => {
      const token = resp.data
      commit('SET_TOKEN', token)
      setToken(token)
    })
  },

  // get user info
  getInfo({ commit }) {
    getUserInfo().then(response => {
      const { data } = response
      const { name, avatar } = data
      commit('SET_NAME', name)
      commit('SET_AVATAR', avatar)
    })
  },

  // user logout
  async logout({ commit}) {
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

