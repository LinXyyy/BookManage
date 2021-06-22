import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})

export default new Vuex.Store({
  state: {
    account: '',
    userRole: '',
    books: '',
    noBook: false
  },
  mutations: {
    setAccount(state, account) {
      state.account = account
    },
    setUserRole(state, userRole) {
      state.userRole = userRole
    },
    setBooks(state, books) {
      state.books = books
    },
    setNoBook(state) {
      state.noBook = true
    },
    setHaveBook(state) {
      state.noBook = false
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [vuexLocal.plugin]
})
