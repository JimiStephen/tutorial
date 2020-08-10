<template>
  <div class="uPage">
    <span class="select-wrapper">
      <el-select v-model="size" size="mini" class="select" @change="sizeChange">
        <el-option
          v-for="item in count"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </span>
    <el-button type="text" @click="prevClick" class="btn-prev" icon="el-icon-arrow-left" :disabled="currentPage < 2"
    ></el-button>
    <span class="currPage">{{currentPage}}</span>
    <el-button type="text" @click="nextClick" class="btn-next" icon="el-icon-arrow-right" :disabled="pageData.length < pageSize"
    ></el-button>
  </div>
</template>

<style lang="css">
  .uPage {
    display: flex;
    align-items: center;
    font-size: 12px;
  }

  .uPage .select-wrapper {
    margin-right: 25px;
  }

  .uPage .currPage {
    font-size: 14px;
    margin: 0 15px;
    font-weight: 800;
  }

  .uPage .select {
    width: 120px;
  }

</style>

<script>
  export default {
    name: 'DIYPage',
    data () {
      return {
        inputPage: 1,
        count: [],
        size: 10
      }
    },
    props: {
      currentPage: {
        type: Number
      },
      pageSize: {
        type: Number
      },
      pageSizes: {
        type: Array
      },
      pageData: {
        type: Array
      }
    },
    watch: {
      currentPage (val) {
        this.inputPage = val
      }
    },
    methods: {
      sizeChange (val) {
        this.$emit('size-change', val)
      },
      currentChange (val) {
        this.$emit('update:currentPage', val)
        this.$emit('current-change', val)
      },
      nextClick () {
        this.currentChange(this.currentPage + 1)
      },
      prevClick () {
        if (this.currentPage <= 1) return
        this.currentChange(this.currentPage - 1)
      }
    },
    mounted () {
      let countArr = []
      if (this.pageSizes && this.pageSizes.length > 0) {
        this.pageSizes.forEach(function (value, index, array) {
          countArr.push({
            value: value,
            label: value + '条/页'
          })
        })
        this.count = countArr
        this.size = this.pageSize ? this.pageSize : this.pageSizes[0]
      }
    }
  }
</script>
