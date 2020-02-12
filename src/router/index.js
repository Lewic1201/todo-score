import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Task from '@/components/Task'
import TaskRecord from '@/components/TaskRecord'
import TodayTask from '@/components/TodayTask'
import CycleType from '@/components/CycleType'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/task',
      name: 'Task',
      component: Task
    },
    {
      path: '/taskRecord',
      name: 'TaskRecord',
      component: TaskRecord
    },
    {
      path: '/todayTask',
      name: 'TodayTask',
      component: TodayTask
    },
    {
      path: '/cycleType',
      name: 'CycleType',
      component: CycleType
    }
  ]
})
