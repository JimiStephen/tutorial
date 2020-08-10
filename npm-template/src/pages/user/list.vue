<template>
  <div class="account-container">
    <el-form :inline="true" :model="query" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="昵称:">
            <el-input v-model="query.nickname" placeholder="昵称"></el-input>
          </el-form-item>
          <el-form-item label="姓名:">
            <el-input v-model="query.name" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showDialog">新建用户</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="createUser">新建用户(跳转)</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="batchRemove" :disabled="!selections || selections.length === 0">批量删除

            </el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-row>
      <el-col :span="24" class="table-wrapper">
        <el-table
          :data="tableData"
          style="width: 100%; min-height: 396px"
          :highlight-current-row="true"
          size="mini"
          @selection-change="select">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名" width="150">
            <template slot-scope="scope">
              <!--超链接的写法 target="_blank"代表打开一个新页面 推荐使用这种写法 可以选择写全Url
                eg :href="http://xxxx" 如果是本域名下的地址可简写为 :href="/#/xxxx" 简写一定要以"/#/开头"-->
              <a :href="'/#/user/info/' + scope.row.id + '?info=true'" target="_blank">{{scope.row.name}}</a>
            </template>
          </el-table-column>
          <el-table-column
            prop="nickname"
            label="昵称" width="100">
          </el-table-column>
          <el-table-column
            prop="sex"
            label="性别" width="100"
            :formatter="sexFormat">
          </el-table-column>
          <el-table-column
            prop="britchday"
            label="生日"
            :formatter="dateFormat">
          </el-table-column>
          <el-table-column
            prop="remark"
            label="备注">
          </el-table-column>
          <el-table-column
            width="150"
            prop="createTime"
            label="新增时间">
          </el-table-column>
          <el-table-column
            width="150"
            prop="modifyTime"
            label="修改时间">
          </el-table-column>

          <el-table-column
            width="180"
            label="操作"
            fixed="right">
            <template slot-scope="scope">
              <el-button type="text" @click="updateUser(scope.row)">编辑</el-button>
              <el-button type="text" @click="delete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="top-15">
        <ucar-pagination
          :page-sizes=[10,20,50,100]
          :current-page.sync="query.pageIndex"
          :page-size="query.pageSize"
          @size-change="reSearch"
          @current-change="search"
        ></ucar-pagination>
      </el-col>
    </el-row>

    <!-- 新增用户 弹窗 -->
    <el-dialog title="新建用户" :visible.sync="dialogShow" width="40%">
      <el-form :model="user" label-width="120px">

        <el-form-item label="用户姓名:">
          <el-input v-model="user.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称:">
          <el-input v-model="user.nickname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户性别:">
          <el-radio-group v-model="user.sex">
            <el-radio-button :label="0">女</el-radio-button>
            <el-radio-button :label="1">男</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="用户生日:">
          <el-form-item prop="britchday" :rules="[
                      {required: true, message: '请选择开始日期', trigger: 'change'}
                    ]">
            <el-date-picker style="width: 100%;" format="yyyy-MM-dd" type="date" :editable="false" placeholder="选择日期"
                            v-model="user.britchday"></el-date-picker>
          </el-form-item>
        </el-form-item>
        <el-form-item label="备注:">
          <el-input type="textarea"
                    :rows="4"
                    v-model="user.remark"
                    placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogShow = false">取 消</el-button>
        <el-button type="primary" @click="newUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>

</style>

<script>
  import api from '@/service/api'
  import moment from 'moment'

  export default {
    data() {
      return {
        query: {
          name: '',
          nickname: '',
          pageIndex: 1,
          pageSize: 10
        },
        user: {
          name: '',
          nickname: '',
          sex: '',
          britchday: '',
          remark: ''
        },
        dialogShow: false,
        tableData: [],
        selections: []
      }
    },
    computed: {
      total() {
        return this.query.currentPage * this.query.pageSize + 1
      }
    },
    methods: {
      reSearch(size) {
        this.query.pageSize = size
        this.search()
      },
      // 翻页
      pageChange(currentPage) {
        this.query.pageIndex = currentPage
        this.getList()
      },
      search() {
        api.getUserList(this.query)
          .then((data) => {
            this.query.currentPage = data.re.pageIndex
            this.tableData = data.re.dataList
          })
      },
      showDialog () {
        this.dialogShow = true
      },
      newUser () {
        api.addUser(this.user)
          .then(() => {
            this.$message.success('添加用户成功')
            this.search()
          })
        this.dialogShow = false
      },
      sexFormat (row, column, cellValue) {
        return row.sex === 0 ? '男' : '女'
      },
      dateFormat(row, column) {
        var date = row[column.property];
        if (date === undefined) {
          return ''
        }
        return moment(date).format('YYYY-MM-DD')
      },
      select(rows) {
        this.selections = rows
      },
      createUser() {
        this.callNewPage('/user/info', {callback: 'search'})
      },
      updateUser(row) {
        this.$router.push(`/user/info/${row.id}`)
      },
      batchRemove() {
        if (this.selections && this.selections.length <= 0) {
          this.$message.warning('请先选择要删除的列表项')
          return
        }
        api.batchRemoveMeetingAccount({
          target: this.selections.map(item => item.id)
        })
          .then(() => {
            this.$message.success('批量删除成功')
            this.search()
          })
      }
    },
    mounted() {
      this.search()
    }
  }
</script>
