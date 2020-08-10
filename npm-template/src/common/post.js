import axios from 'axios'
import vue from 'vue'
var qs = require('qs')
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
export default {
  /**
   * post
   *
   * @param  {Object} args post参数对象
   *                  args.url : 请求地址
   *                  args.uri : 请求api
   *                  args.data : 参数
   *                  args.success : 成功cb
   *                  args.error : 失败cb
   */
  post (args) {
    args.data = args.data ? args.data : {}
    // 如果只需要获取本地缓存
    return axios.request({
      url: args.uri ? '/api/gw?api=' + args.uri : args.url,
      method: 'post',
      // headers: {'Access-Control-Allow-Origin': '*'},
      withCredentials: true,
      data: args.uri ? qs.stringify({
        uri: args.uri,
        data: JSON.stringify(args.data)
      }) : qs.stringify(args.data)
    })
      .then(response => {
        return Promise.resolve(response.data)
      })
      .catch(({ response }) => {
        vue.prototype.$message(response.data.message)
      })
  }
}
