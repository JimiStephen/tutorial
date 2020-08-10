// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'core-js/fn/array/from'
import 'core-js/fn/object/assign'
import 'core-js/es6/promise'
import Vue from 'vue'
import App from './App'
import Router from 'vue-router'
import router from './router'
import ElementUI from 'element-ui'
// import './theme.scss'
import 'element-ui/lib/theme-chalk/index.css'
import './icons' // icon
// import {parseRoute, getDynamicUrl} from '@/utils'
import store from '@/store/index'
import {Permission, RichTextEditor, UcarPagination} from '@ucar/ucar-vue'
// import menus from '@/constant/menu'
import EditorBar from '@/components/editor/WangEditor'
import Table from '@/components/table.vue'
import tip_message from '@/pages/component/tip_message.vue'

// 导入图片预览组件
import 'viewerjs/dist/viewer.css'
import Viewer from 'v-viewer'

require('promise.prototype.finally').shim()

// document.domain = '10101111.com'

Vue.use(Viewer)
Vue.use(Router)
Vue.use(ElementUI)
Vue.use(Permission)
Vue.use(RichTextEditor)
Vue.use(UcarPagination)
Vue.use(require(`vue-moment`))
Vue.component('editor-bar', EditorBar)
Vue.component('v-table', Table)
Vue.component('v-tip_message', tip_message)

/**
 * element-ui hack
 */
Vue.prototype.$message.success = (str) => {
  Vue.prototype.$message({
    type: 'success',
    message: str,
    showClose: true
  })
}
Vue.prototype.$message.warning = (str) => {
  Vue.prototype.$message({
    type: 'warning',
    message: str,
    showClose: true
  })
}
Vue.prototype.$message.error = (str) => {
  Vue.prototype.$message({
    type: 'error',
    message: str,
    showClose: true
  })
}

// register globally
Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
