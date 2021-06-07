// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

//import 'normalize.css/normalize.css';

// 引入 ElementUI
import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/element-variables.scss';

import 'element-ui/lib/theme-chalk/index.css'     // 样式需要单独引入
import '../static/global/global.css'
// 全局样式
import '@/assets/css/global.scss';

// 自定义svg icon
import '@/components/SvgIcon';

Vue.config.productionTip = false

Vue.use(ElementUI,{ size: 'small' })


// 全局axios
var axios = require('axios')
Vue.prototype.$axios = axios
// 反向代理
axios.defaults.baseURL = 'http://127.0.0.1:8888'




import VueI18n from 'vue-i18n'

Vue.use(VueI18n) // 通过插件的形式挂载

const i18n = new VueI18n({
  locale: 'zh-CN',    // 语言标识
  //this.$i18n.locale // 通过切换locale的值来实现语言切换
  messages: {
    'zh-CN': require('./common/lang/zh'),   // 中文语言包
    'en-US': require('./common/lang/en')    // 英文语言包
  }
})


//钩子函数，访问路由前调用 路由验证工具去验证路由的
router.beforeEach((to, from, next) => {
  if(to.meta.title){
    document.title = to.meta.title
  }
  //路由需要认证
  if (to.meta.requireAuth) {
    //判断store里是否有token
    if (store.state.token) {
      next()
    } else {
      next({
        path: 'login',
        query: { redirect: to.fullPath }
      })
    }
  } else {
    next()
  }
}
)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  store,
  components: { App },
  template: '<App/>'
})
