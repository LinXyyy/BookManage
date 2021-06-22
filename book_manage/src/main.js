import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import qs from 'qs'

Vue.config.productionTip = false
Vue.prototype.$qs = qs

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
