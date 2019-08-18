<template>
  <div class="App">
    Hello World
  </div>
  <div>
	<!--router-link 组件用来导航路由-->
	<router-link to='/Home'>go home</router-link>
	<router-link to='/About'>go About</router-link>
  </div>
  <div>
	<p>{{getCounter}}</p>
    <button @click="add">add</button>
  </div>
  
  <div>
  <!--展示路由的组件视图的内容--->
  <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',

  data() {
    return {};
  },
  computed:{
	...mapGetters(['getCounter']);
  },
  methods:{
	...mapActions(['add'])
  }
};
</script>

<style lang="scss" scoped>
.App {
  color: skyblue;
  text-align:center;
  font-size:20px;
}
</style> 