<template>
  <div id="app">
    <router-view class="app-container"/>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    mounted() {
      /**
       * 根据携带的cookie，自动获取当前系统的所有按钮权限，会发起一个http请求
       */ 
      if (this.$route.fullPath !== '/login') this.updatePermissions()
    },
    methods: {
      ...mapActions([
        'updatePermissions'
      ])
    }
  }
</script>


<style lang="scss">
  html, body, #app {
    margin: 0;
    padding: 0;
    height: 100%;
  }

  .el-loading-service-ex {
    z-index: 999999 !important;
  }

  .v-viewer-witout-ppn {
    z-index: 999999 !important;
  }

  .v-viewer-witout-ppn .viewer-toolbar .viewer-prev {
    display: none;
  }

  .v-viewer-witout-ppn .viewer-toolbar .viewer-play {
    display: none;
  }

  .v-viewer-witout-ppn .viewer-toolbar .viewer-next {
    display: none;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .app-iframe-container {
    padding: 20px;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
  }

  .app-iframe-container .row-title {
    display: flex;
    align-items: center;
    border-bottom: 1px solid #ddd;
    margin-bottom: 15px;
  }

  .app-iframe-container .row-title h2 {
    color: #409eff;
    font-size: 20px;
    line-height: 20px;
    font-weight: bolder;
    border-left: 2px solid #409eff;
    padding-left: 10px;
    margin-right: 20px;
  }

  .app-iframe-container .top-15 {
    margin-top: 15px;
  }

  .app-iframe-container .table-wrapper {
    border: 1px solid #ddd;
    padding: 20px;
    box-shadow: 0 2px 3px 0 #ddd;
  }

  .app-iframe-container .choose-input .el-input {
    width: 80%;
  }

  .app-iframe-container {
    .tips {
      margin-bottom: 10px;

      .green {
        color: #67c23a;
      }

      .blue {
        color: #409eff;
      }

      .red {
        color: #f56c6c;
      }
    }
  }

  .allowModify .el-upload {
    cursor: not-allowed;
  }

  .el-collapse-blod-header .el-collapse-item__header {
    font-weight: bold;
  }

  /* 省市县下的input*/
  .ssx .el-input {
    width: 105% !important;
  }
</style>
