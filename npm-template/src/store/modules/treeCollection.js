const state = {
  treeCollection: []
}

const getters = {
  treeCollection: state => state.treeCollection
}

const actions = {
  updateTreeCollection ({ commit }, treeCollection) {
    commit('ADDTREE', treeCollection)
  },
  removeTreeCollection ({ commit }) {
    commit('REMOVETREE')
  }
}

const mutations = {
  'ADDTREE' (state, treeCollection) {
    state.treeCollection.push(treeCollection)
  },
  'REMOVETREE' (state) {
    state.treeCollection.length = 0
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
