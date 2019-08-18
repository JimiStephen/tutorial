// src/main.js
//console.log('hello webpack')
import Vue from 'vue'
import App from './App.vue'
//add router npm install vue-router vuex --save
import router from './router'
import store from './store'

new Vue({
	router,
	store,
    render: h => h(App)
}).$mount('#app')