import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Task from '@/components/Task'
import TaskRecord from '@/components/TaskRecord'
import TodayTask from '@/components/TodayTask'
import CycleType from '@/components/CycleType'
import Login from '@/components/Login'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      name: 'HelloWorld',
      path: '/',
      component: HelloWorld
    },
    {
      name: 'Task',
      path: '/task',
      component: Task
    },
    {
      name: 'TaskRecord',
      path: '/taskRecord',
      component: TaskRecord
    },
    {
      name: 'TodayTask',
      path: '/todayTask',
      component: TodayTask,
      meta: {
        requireAuth: true
      }
    },
    {
      name: 'CycleType',
      path: '/cycleType',
      component: CycleType
    },
    {
      name: 'Login',
      path: '/login',
      component: Login
    }
  ]
});

// 路由守卫
// 参数1 : to 目标路由对象
// 参数2 : from 来源路由对象
// 参数3 : next() 下一步
router.beforeEach((to, from, next) => {
  // 判断该路由是否需要登录权限
  if (to.meta.requireAuth) {
    let token = localStorage.getItem('token');
    // 通过localStorage.getItem()获取当前的 token 是否存在
    if (token) {
      next()
    }
    else {
      next({
        path: '/login',
        query: {
          // 将跳转的路由path作为参数，登录成功后跳转到该路由
          redirect: to.fullPath
        }
      })
    }
  }
  else {
    next()
  }
});
router.afterEach(route => {
  window.scroll(0, 0)
});

export default router
