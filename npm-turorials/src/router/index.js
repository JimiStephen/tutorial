// src/router/index.js
import Vue from 'vue'
import VueRouter from "vue-router";
import Home from '../views/Home';
import About from '../views/About';
Vue.use(VueRouter)
export default new VueRouter({
  mode: 'hash',
  routes: [
    {
      path: '/Home',
      //component: Home
	  //lazy load
	  component:()=>import('../views/Home.vue')
    },
    {
      path: '/About',
      //component: About
	  component:()=>import('../views/About.vue')
    },
    {
      path: '*',
      redirect: '/Home'
    }
  ]
}) 