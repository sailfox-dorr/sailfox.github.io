// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

Vue.config.productionTip = false
import 'element-ui/lib/theme-chalk/index.css'

// 导入 Element_ui
import ElementUI from 'element-ui'
// 导入 Element-ui 样式
import 'element-ui/lib/theme-chalk/index.css'
// 插件 Element-ui
Vue.use(ElementUI)
import './assets/css/index.css'
import poets from './components/view/discovery/poets'
import poem from './components/view/discovery/poem'
import paragraphs from './components/view/discovery/paragraphs'

const router = new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/poets'
    },
    {
      // 发现诗人
      path: '/paragraphs',
      component: paragraphs
    },
    {
      // 发现诗人
      path: '/poets',
      component: poets
    }
    ,
    {
      // 发现诗人
      path: '/poem',
      component: poem
    }
  ]
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
}).$mount('#app')
