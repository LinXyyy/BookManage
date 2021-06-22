import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/login/Login";
import Main from "@/views/index/Main";
import AllBook from "@/views/index/components/allBook/AllBook";
import MyBook from "@/views/index/components/allBook/components/MyBook";
import Log from "@/views/index/components/allBook/components/Log";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/index',
    component: Main,
    children: [
      {
        path: '/allBook',
        component: AllBook
      },
      {
        path: '/myBook',
        component: MyBook
      },
      {
        path: '/log',
        component: Log
      }
    ]
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
