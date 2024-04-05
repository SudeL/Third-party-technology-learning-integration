import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login.vue'
import PersonalView from '../views/PersonalView.vue'
import group from '../views/group/Group.vue'
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
      path: '/group',
      name: 'group',
      component: group
    },
    {
      path: '/user',
      name: 'user',
      component: User
    }
  ]
})
