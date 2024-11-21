import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      alias: ['/index.html', '/creer-compte'],
      name: 'account-create',
      component: () => import('../views/AccountCreateView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/AccountLogInView.vue'),
    },
    {
      path: '/:notFound',
      name: 'not-found',
      component: () => import('../views/PageNotFoundView.vue'),
    }
  ],
})

export default router
