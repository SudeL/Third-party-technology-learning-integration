import Vue from 'vue'
import Router from 'vue-router'
import LoginView from '../views/LoginView.vue'
import PersonalView from '../views/PersonalView.vue'
import Group from '../views/group/Group.vue'
import User from '../views/user/User.vue'
import HomeView from '../views/HomeView.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LoginView',
      component: LoginView
    },
    {
      path: '/HomeView',
      name: 'HomeView',
      component: HomeView,
      redirect: '/PersonalView',
      children: [
        {
          path: '/PersonalView',
          name: 'PersonalView',
          component: PersonalView
        },

        {
          path: '/group',
          name: 'group',
          component: Group
        },
        {
          path: '/user',
          name: 'user',
          component: User
        },

      ]
    }
  ]
})
