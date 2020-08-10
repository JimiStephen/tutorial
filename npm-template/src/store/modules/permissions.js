import {permission} from '@/service/api'

const state = {
  permissions: null
}

const getters = {
  permissions: state => state.permissions
}

const actions = {
  updatePermissions ({ commit }, sys) {
    permission.getPermissionForButton(sys)
      .then((data) => {
        commit('UPDATEPERMISSION', data)
      })
  }
}

const mutations = {
  'UPDATEPERMISSION' (state, permissions) {
    state.permissions = permissions
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
