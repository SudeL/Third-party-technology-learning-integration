// 引入Vue库
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 引入全局样式，清除默认样式
import '@/assets/global.css'
import '@/assets/font-size.css'

// Vue配置
Vue.config.productionTip = false
Vue.use(ElementUI, { size: "small" })

/* eslint-disable no-new */
// 创建和挂载Vue实例
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})