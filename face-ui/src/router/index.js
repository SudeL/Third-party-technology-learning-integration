import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login.vue'
import PersonalView from '../views/PersonalView.vue'
import Gruop from '../views/Gruop/Gruop.vue'
import User from '../views/user/User.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    }, 
    {
      path: '/views/PersonalView',
      name: 'PersonalView',
      component: PersonalView
    }, 
    {
      path: '/gruop',
      name: 'gruop',
      component: Gruop
    }, 
    {
      path: '/user',
      name: 'user',
      component: User
    }
  ]
})
