import Vue from 'vue'
import Router from 'vue-router'

import RepairAd from '@/components/RepairAd.vue'
import Customer from '@/components/Customer.vue'
import Addproduct from '@/components/Addproduct.vue'
import Fix from '@/components/Fix.vue'
import Cushome from '@/components/Cushome.vue'
import Receipt from  '@/components/Receipt.vue'
import ViewReceipt from  '@/components/ViewReceiptData.vue'
import Login from '@/components/Loginform.vue'
import Register from '@/components/Registerform.vue'
import Home from '@/components/Home.vue'
import Contact from '@/components/Contact.vue'


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
    path: '/viewreceipt',
    name: 'viewreceipt',
    component: ViewReceipt
  },
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/Contact',
      name: 'Contact',
      component: Contact
    },
    {
      path: '/cushome',
      name: 'customerhome',
      component: Cushome
    },
  ]
})
