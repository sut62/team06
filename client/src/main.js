import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(vuetify)
Vue.config.productionTip = false

new Vue({
 vuetify,
 router,
 BootstrapVue,
 render: h => h(App)
}).$mount('#app')
