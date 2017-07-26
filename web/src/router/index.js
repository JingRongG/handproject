import Vue from 'vue'
import Router from 'vue-router'
import wsock from '@/components/wsock'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'wsock',
      component: wsock
    }
  ]
})
