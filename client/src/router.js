import Vue from 'vue'
import Router from 'vue-router'

import RepairAd from '@/components/RepairAd.vue'
import Customer from '@/components/Customer.vue'
import Addproduct from '@/components/Addproduct.vue'
import Fix from '@/components/Fix.vue'
import Cushome from '@/components/Cushome.vue'
import Receipt from  '@/components/Receipt.vue'
import ViewReceipt from  '@/components/ViewReceiptData.vue'
import LoginCustomer from '@/components/LoginCustomer.vue'
import LoginFix from '@/components/LoginFix.vue'
import LoginContact from '@/components/LoginContact.vue'
import LoginAdmin from '@/components/LoginAdmin.vue'
import LoginReceipt from '@/components/LoginReceipt.vue'
import LoginAddProduct from '@/components/LoginAddProduct.vue'
import Register from '@/components/Registerform.vue'
import Home from '@/components/Home.vue'
import Contact from '@/components/Contact.vue'
import ViewAddproduct from '@/components/ViewAddproductData.vue'
import ViewContact from '@/components/ViewContact.vue'
import ViewFix from  '@/components/ViewFixData.vue'
import viewrepairindex from '@/components/viewRepairAd.vue'
import PrintReceipt from '@/components/PrintReceipt.vue'
import ViewCustomer from '@/components/ViewCustomer.vue'


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
    path: '/printreceipt',
    name: 'printreceipt',
    component: PrintReceipt
  },
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/logincustomer',
      name: 'logincustomer',
      component: LoginCustomer
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
    {
      path: '/viewAddproduct',
      name: 'viewaddproduct',
      component: ViewAddproduct
    },
    {
      path: '/loginfix',
      name: 'loginfix',
      component: LoginFix
    },
    {
      path: '/logincontact',
      name: 'logincontact',
      component: LoginContact
    },
    {
      path: '/loginadmin',
      name: 'loginadmin',
      component: LoginAdmin
    },
    {
      path: '/loginaddproduct',
      name: 'loginaddproduct',
      component: LoginAddProduct
    },
    {
      path: '/loginreceipt',
      name: 'loginreceipt',
      component: LoginReceipt
    },
    {
      path: '/viewfix',
      name: 'viewfix',
      component: ViewFix
    },
    {
      path: '/viewcontact',
      name: 'viewcontact',
      component: ViewContact
    },
    {
      path: '/viewrepairindex',
      name: 'viewrepairindex',
      component: viewrepairindex
    },
    {
      path: '/viewCustomer',
      name: 'viewCustomer',
      component: ViewCustomer
    },
  ]
})