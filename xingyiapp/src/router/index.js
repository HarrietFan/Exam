import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  //设置路由模式，默认hash,请求路径会有一个#
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: ()=>import('@/components/Login')
    },
    {
      path: '/register',
      name: 'Register', // 注册
      component: ()=>import('@/components/Register')
    },
    {
      path: '/index',
      meta:{"isLogin":true},
      name: 'Index',
      component: ()=>import('@/components/Index'),
      children:[
        {
          path:'userList', //子路由没有/
          meta:{"isLogin":true},
          name:'UserList',
          component:()=>import('@/components/UserList')
        },
        {
          path:'userSelf',
          meta:{"isLogin":true},
          name:'UserSelf',
          component:()=>import('@/components/UserSelf')
        },
        {
          path:'userEdit',
          meta:{"isLogin":true},
          name:'UserEdit',
          component:()=>import('@/components/UserEdit')
        },
        {
          path:'myCourse',
          meta:{"isLogin":true},
          name:'MyCourse',
          component:()=>import('@/components/MyCourse')
        },
        {
          path:'courseList',
          meta:{"isLogin":true},
          name:'CourseList',
          component:()=>import('@/components/CourseList')
        },
        {
          path: 'userEdit', // 子路由没有 /
          meta:{"isLogin":true},
          name: 'UserEdit',
          component: ()=>import('@/components/UserEdit')
        },
        {
          path: 'userInsert', // 子路由没有 /
          meta:{"isLogin":true},
          name: 'UserInsert',
          component: ()=>import('@/components/UserInsert')
        },
      ]
    },
  ]
})
