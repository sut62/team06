import Vue from 'vue'
import Router from 'vue-router'

import RepairAd from '@/components/RepairAd.vue'
import Customer from '@/components/Customer.vue'
import Addproduct from '@/components/Addproduct.vue'
import Fix from '@/components/Fix.vue'
import fp from  '@/components/fp.vue'
import Receipt from  '@/components/Receipt.vue'


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/repairindex',
      name: 'RPCP1',
      component: RepairAd
    },
    {
      path: '/customer',
      name: 'customer',
      component: Customer
    },
    {
      path: '/addproduct',
      name: 'addproduct',
      component: Addproduct
    },
    {
      path: '/fix',
      name: 'fix',
      component: Fix
  },

  {
    path: '/receipt',
    name: 'receipt',
    component: Receipt
  },
    {
      path: '/',
      name:'firstpage',
      components: fp
    },
  
  

  ]
})
