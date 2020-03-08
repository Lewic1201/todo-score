// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from './interceptor/http'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/display.css'

import elementUI from 'element-ui'
import qs from 'qs';

Vue.use(elementUI);

Vue.prototype.axios = axios;

Vue.prototype.qs = qs;

Vue.config.productionTip = false;

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
