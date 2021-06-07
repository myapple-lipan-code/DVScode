import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login.vue'
import appConfig from '@/views/appConfig'
import vsys from '@/views/vsys/index.vue'
import statistics from '@/views/statistics/index.vue'
import operatelog from '@/views/operatelog/index.vue'
import channels from '@/views/channel'
import  channelIndex from '@/views/channel/channel'
import  channelDetail from '@/views/channel/channelComponent'
import  account from '@/views/account'
import  accountDetail from './../views/account/accountDetail'
import  accountMgr from './../views/account/accountMgr'
import  scconfig from './../views/scconfig'

Vue.use(Router)

const router = new Router({
  routes: [

    // 登录
    {
      path:'/login',
      name:'Login',
      component:Login,
      meta: { hidden: true,title:"TelConsole"}
    },

    {
      path:"/vsys",
      name:"vsys",
      component:vsys,
      meta:{
        requireAuth:true
      }
    },
    {
      path:"/statistics",
      name:"statistics",
      component:statistics,
      meta:{
        requireAuth:true
      }
    },
    {
      path:"/operatelog",
      name:"operatelog",
      component:operatelog,
      meta:{
        requireAuth:true
      }
    },
    {
      path:"/appConfig",
      name:"appConfig",
      component:appConfig,
      meta:{
        requireAuth:true
      }
    },
    {path:"/channel",
      name:"channel",
      component:channels,
      meta:{
        requireAuth:true
      }
    },
    {path:"/channelIndex",
      name:"channelIndex",
      component:channelIndex,
      meta:{
        requireAuth:true
      }
    },
    {
      path:"/channelDetail",
      name:"channelDetail",
      component:channelDetail,
      meta:{
        requireAuth:true
      }
    },
    {
      path:"/account",
      name:"account",
      component:account,
      meta:{
        requireAuth:true
      }
    }, {
      path: '/account/accountDetail',
      name: 'accountDetail',
      component: accountDetail,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/account/accountMgr',
      name: 'accountMgr',
      component: accountMgr,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/scConfig',
      name: 'scConfig',
      component: scconfig,
      meta: {
        requireAuth: true
      }
    }




  ]
});

export default  router
