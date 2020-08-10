<template>
  <div class="customer-list-container">
    <el-form :inline="true" :model="query" size="mini">
      <el-row>
        <el-col :span="24">
          <el-form-item label="手机号:">
            <el-input v-model="query.mobile" placeholder="手机号"></el-input>
          </el-form-item>
          <el-form-item label="姓名:">
            <el-input v-model="query.name" placeholder="姓名"></el-input>
          </el-form-item>
          <el-form-item label="在线设备号:">
            <el-input v-model="query.deviceId" placeholder="在线设备号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-show="moreQuery">
        <el-col :span="24">
          <el-form-item label="性别:">
            <el-switch :active-value="0" :inactive-value="1" v-model="query.sex"></el-switch>
          </el-form-item>
          <el-form-item label="上次登录时间:">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%" :editable="false" v-model="query.startDate"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" style="width: 100%" :editable="false" v-model="query.endDate"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态:">
            <el-select v-model="query.status" placeholder="请选择状态">
              <el-option label="正常" value="normal"></el-option>
              <el-option label="冻结" value="freeze"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="会员等级:">
            <el-select v-model="query.level" placeholder="请选择等级">
              <el-option label="普通会员" value="normal"></el-option>
              <el-option label="VVIP" value="vvip"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button type="primary" :class="{'el-icon-plus': !moreQuery, 'el-icon-minus': moreQuery}" @click="toggleQuery">{{moreQuery ? ' 简化条件': ' 更多条件'}}</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-edit-outline" @click="createCustomer">新建客户</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item>
          <el-button-group>
            <el-button type="primary" @click="update(scope.row)" :disabled="!currentRow">修改</el-button>
            <el-button type="primary" @click="remove(scope.row)" :disabled="!currentRow">删除</el-button>
            <el-button type="primary" :disabled="!currentRow">充值卡充值</el-button>
            <el-button type="primary" :disabled="!currentRow">发票抬头</el-button>
            <el-button type="primary" :disabled="!currentRow">历史记录</el-button>
            <el-button type="primary" :disabled="!currentRow">账号操作记录</el-button>
            <el-button type="primary" :disabled="!currentRow">收藏司机</el-button>
            <el-button type="primary" :disabled="!currentRow">拉新记录</el-button>
            <el-button type="primary" :disabled="!currentRow">定制服务</el-button>
            <el-button type="primary" :disabled="!currentRow">里程详情</el-button>
            <el-button type="primary" :disabled="!currentRow">等级记录</el-button>
            <el-button type="primary" :disabled="!currentRow">大转盘权益记录</el-button>
            <el-button type="primary" :disabled="!currentRow">中奖记录</el-button>
            <el-button type="primary" :disabled="!currentRow">调整未开票金额</el-button>
            <el-button type="primary" :disabled="!currentRow">乘车喜好</el-button>
          </el-button-group>
        </el-form-item>
      </el-row>
    </el-form>
    <el-row>
      <el-col :span="24" class="table-wrapper">
        <el-table
          :data="tableData"
          size="mini"
          style="width: 100%; min-height: 396px"
          @current-change="choose"
          :highlight-current-row="true">
          <el-table-column
            label="选择"
            width="50">
            <template slot-scope="scope">
              <el-radio v-model="currentRow" :label="scope.row.id"><i></i></el-radio>
            </template>
          </el-table-column>
          <el-table-column
            label="姓名" width="100">
            <template slot-scope="scope">
              <!--超链接的写法 target="_blank"代表打开一个新页面 推荐使用这种写法 可以选择写全Url
                eg :href="http://xxxx" 如果是本域名下的地址可简写为 :href="/#/xxxx" 简写一定要以"/#/开头"-->
              <a :href="'/#/customer/info/' + scope.row.id" target="_blank">{{scope.row.name}}</a>
            </template>
          </el-table-column>
          <el-table-column
            prop="sex"
            label="性别" width="50">
          </el-table-column>
          <el-table-column
            prop="mobile"
            label="手机号" width="120">
          </el-table-column>
          <el-table-column
            prop="level"
            label="会员等级" width="100">
          </el-table-column>
          <el-table-column
            prop="balance"
            label="余额" width="100">
          </el-table-column>
          <el-table-column
            prop="totalMiles"
            label="总里程" width="100">
          </el-table-column>
          <el-table-column
            prop="monthMiles"
            label="当月里程" width="100">
          </el-table-column>
          <el-table-column
            prop="type"
            label="客户类型" width="100"
            :formatter="typeFormat">
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态" width="100"
            :formatter="statusFormat">
          </el-table-column>
          <el-table-column
            prop="lastLoginTime"
            label="上次登录时间" width="120">
          </el-table-column>
          <el-table-column
            prop="lastLoginType"
            label="上次登录方式" width="100">
          </el-table-column>
          <el-table-column
            label="备注">
            <template slot-scope="scope">
              <el-popover
                placement="top-start"
                trigger="hover"
                :disabled="scope.row.remark.length < 15">
                <div class="ucar-ellipsis-cell" slot="reference">{{scope.row.remark}}</div>
                <div class="ucar-cell-popper">{{scope.row.remark}}</div>
              </el-popover>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="top-15">
        <ucar-pagination
          :page-sizes=[10,20,50,100]
          :current-page.sync="query.currentPage"
          :page-size="query.pageSize"
          @size-change="reSearch"
          @current-change="search"
        ></ucar-pagination>
      </el-col>
    </el-row>
  </div>
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
          deviceId: '',
          currentPage: 1,
          pageSize: 10,
          id: '',
          sex: 0,
          startDate: '',
          endDate: '',
          status: 'normal',
          level: 'normal'
        },
        moreQuery: false,
        tableData: [],
        currentRow: ''
      }
    },
    computed: {
      total () {
//        return this.query.currentPage * this.query.pageSize + 1
        return 10000
      }
    },
    methods: {
      toggleQuery () {
        this.moreQuery = !this.moreQuery
      },
      choose (item) {
        this.currentRow = item.id
      },
      reSearch (size) {
        this.query.pageSize = size
        this.search()
      },
      search () {
        api.getCustomerList(this.query)
          .then((data) => {
            this.query.currentPage = data.currentPage
            this.currentRow = ''
            this.tableData = data.list.map(item => {
              if (!item.name) item.name = this.query.name
              return item
            })
          })
      },
      createCustomer () {
        this.callNewPage('/customer/info', { callback: 'search' })
      },
      typeFormat (row) {
        return row.type === 'single' ? '个人' : '企业'
      },
      statusFormat (row) {
        return row.status === 'normal' ? '正常' : '异常'
      },
      update (row) {
        this.$router.push(`/customer/info/${row.id}`)
      },
      remove (row) {
        api.removeCustomer(row)
          .then(() => {
            this.$message.success('删除用户成功!')
            this.search()
          })
      }
    },
    mounted () {
      this.search()
    }
  }
</script>