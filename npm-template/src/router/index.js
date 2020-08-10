import Router from 'vue-router'
import {mixin} from '@/utils'
import _404 from '@/pages/404'
import index from '@/pages/index'

const router = new Router({
  routes: [
    {
      path: '/',
      name: '首页',
      component: mixin(index)
    },
    {
      path: '*',
      name: '404',
      component: _404
    },
    {
      path: '/common/pdfpreview',
      name: 'pdf文件预览',
      component: mixin(require('@/components/pdf-preview').default)
    },{
        title: '用户列表',
        path: '/user/list',
		 component: mixin(require('@/pages/user/list').default)
      },
      {
        title: '新建信息',
        path: '/user/info',
		component: mixin(require('@/pages/user/info').default),
        hidden: true
      },
      {
        title: '修改信息',
        path: '/user/info/:id',
        hidden: true,
		 component: mixin(require('@/pages/user/info').default)
      }
     
  ]
})

router.beforeEach((to, from, next) => {
  next()
})

export default router
