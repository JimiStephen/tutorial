<template>
  <div v-loading="loading">
    <el-row v-if="info=='true'">
      <el-col :span="24" class="row-title">
        <h2>用户信息信息</h2><a href="#/user/list">列表</a>
      </el-col>
      <el-col :span="18">
        <el-col :span="8">
          姓名：{{user.name}}
        </el-col>
        <el-col :span="8">
          昵称：{{user.nickname}}
        </el-col>
        <el-col :span="8">
          性别：  {{user.sex | sexFormat}}
        </el-col>
        <el-col :span="16">
            生日： {{user.britchday}}
        </el-col>
        <el-col :span="24">
          备注： {{user.remark}}
        </el-col>
      </el-col>

    </el-row>
    <!--表单-->
    <el-form v-else ref="user" status-icon :model="user" label-width="120px" size="mini">
      <el-row>
        <el-col :span="24" class="row-title">
          <h2>用户信息信息</h2> <a href="#/user/list">列表</a>
        </el-col>
        <el-col :span="18">
          <el-col :span="8">

            <el-form-item label="用户姓名:" prop="name" :rules="[
                    {required: true, message: '请输入用户姓名', trigger: 'blur'}
                   ]">
              <el-input v-model="user.name"></el-input>
            </el-form-item>
            <el-form-item label="用户昵称:" prop="name" :rules="[
                    {required: true, message: '请输入用户昵称', trigger: 'blur'}
                   ]">
              <el-input v-model="user.nickname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别">
              <el-radio-group v-model="user.sex">
                <el-radio-button :label="0">女</el-radio-button>
                <el-radio-button :label="1">男</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="生日">
              <el-col :span="10">
                <el-form-item prop="britchday" :rules="[
                      {required: true, message: '请选择开始日期', trigger: 'change'}
                    ]">
                  <el-date-picker format="yyyy-MM-dd" type="date" :editable="false" placeholder="选择日期"
                                  @change="dateChangebirthday" v-model="user.britchday"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col class="line" :offset="1" :span="1">-</el-col>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input type="textarea"
                        :rows="4"
                        v-model="user.remark"
                        placeholder="备注"></el-input>
            </el-form-item>
          </el-col>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button type="primary" size="normal" @click="onSubmit('user')">提交</el-button>
            <el-button type="primary" size="normal">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>




  </div>
</template>

<style>
  .avatar-uploader-wrapper {
    display: flex;
    flex-flow: column;
    align-items: center;
  }

  .avatar-uploader {
    padding: 0 20px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    display: block;
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
  import api from '@/service/api'

  export default {
    data() {
      return {
        user: {
          id: '',
          name: '',
          nickname: '',
          sex: 0,
          britchday: '',
          remark: ''
        },
        showImg: false,
        dialogShow: false,
        loading: false,
        info: this.$route.query.info
      }
    },
    methods: {
      dateChangebirthday (val) {
        console.log(val)
        this.form.birthdayName = val
      },
      onSubmit (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.$route.params.id) {
              api.updateUser(this.user)
                .then(() => {
                  this.$notify({
                    title: '成功',
                    message: '更新客户成功',
                    type: 'success'
                  })
                  this.$router.go(-1)
                })
                .catch(e => {
                  this.$notify.error({
                    title: '出错了',
                    message: e.message
                  })
                })
            } else {
              api.addUser(this.user)
                .then(() => {
                  this.$notify({
                    title: '成功',
                    message: '新增客户成功',
                    type: 'success'
                  })
                  this.$router.go(-1)
                })
                .catch(e => {
                  this.$notify.error({
                    title: '出错了',
                    message: e.message
                  })
                })
            }
          } else {
            return false
          }
        })
      }
    },
    sexFormat (cellValue) {
      return cellValue === 0 ? '男' : '女'
    },
    mounted() {
      if (this.$route.params.id) {
        alert(this.$route.params.id);
        console.log('哈哈哈' + this.info)
        this.loading = true
        api.getUser({id: this.$route.params.id})
          .then((data) => {
            this.loading = false
            this.user = data.re
          })
          .catch(e => {
            this.$notify.error({
              title: '出错了',
              message: e.message
            })
          })
      }
    }
  }
</script>
