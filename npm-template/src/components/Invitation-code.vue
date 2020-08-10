<template>
  <el-row>
    <el-col :span="24">
      <el-form :inline="true" :model="query">
        <el-form-item label="邀请码类型:">
          <el-select v-model="query.invitationType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邀请码:">
          <el-input v-model="query.invitationCode" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="邀请人姓名:">
          <el-input v-model="query.name" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="邀请人手机号:">
          <el-input v-model="query.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="员工ID:">
          <el-input v-model="query.id" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="创建日期:">
          <el-col :span="11">
            <el-form-item prop="startDate">
              <el-date-picker type="date" placeholder="选择日期" :editable="false" v-model="query.startDate"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :offset="1" :span="1">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endDate">
              <el-date-picker type="date" placeholder="选择日期" :editable="false" v-model="query.endDate"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="24" class="table-wrapper">
      <el-table
        :data="tableData"
        style="width: 100%" height="528"
        :highlight-current-row="true"
        @current-change="choose">
        <el-table-column
          prop="invitationCode"
          label="邀请码" width="180">
        </el-table-column>
        <el-table-column
          prop="invitationType"
          :formatter="typeFormat"
          label="邀请码类型" width="120">
        </el-table-column>
        <el-table-column
          prop="id"
          label="员工ID" width="100">
        </el-table-column>
        <el-table-column
          prop="name"
          label="邀请人姓名" width="120">
        </el-table-column>
        <el-table-column
          prop="mobile"
          label="邀请人手机号" width="180">
        </el-table-column>
        <el-table-column
          prop="startDate"
          label="创建时间" width="180">
        </el-table-column>
        <el-table-column
          prop="endDate"
          label="最后编辑时间">
        </el-table-column>
      </el-table>
    </el-col>
    <el-col :span="24" class="top-15">
      <el-pagination
        :background="true"
        @current-change="search"
        :current-page.sync="query.currentPage"
        :page-size="query.pageSize"
        layout="total, prev, pager, next"
        :total="total">
      </el-pagination>
    </el-col>
  </el-row>
</template>

<style>

</style>

<script>
  import api from '@/service/api'

  export default {
    data () {
      return {
        query: {
          mobile: '',
          name: '',
          invitationCode: '',
          invitationType: '',
          startDate: '',
          id: '',
          endDate: '',
          currentPage: 1,
          pageSize: 10
        },
        options: [
          {
            label: '全部',
            value: ''
          },
          {
            label: '兼职司机',
            value: '1'
          },
          {
            label: '专职司机',
            value: '2'
          },
          {
            label: '销售员工',
            value: '3'
          },
          {
            label: '普通员工',
            value: '4'
          }
        ],
        tableData: [],
        total: 0
      }
    },
    methods: {
      search () {
        api.getInvitationCode(this.query)
          .then((data) => {
            this.total = data.total
            this.tableData = data.list
          })
      },
      choose (item) {
        this.$emit('choose', item)
      },
      typeFormat (row) {
        const constant = {
          1: '兼职司机',
          2: '专职司机',
          3: '销售员工',
          4: '普通员工'
        }
        return constant[row.invitationType]
      }
    },
    mounted () {
      this.search()
    }
  }
</script>