/**
 * 本页面如非必要，请不要修改，如需更新，请联系模板项目相关负责人：韩劲驰 jinchi.han@ucarinc.com
 */

export const recursion = (array, key, value, childKey) => {
  for (let i = 0; i < array.length; i++) {
    if (array[i][key] === value) return array[i]
    if (array[i][childKey] && array[i][childKey] instanceof Array && array[i][childKey].length > 0) {
      let result = recursion(array[i][childKey], key, value, childKey)
      if (result) return result
    }
  }
  return null
}

export const cookies = {
  // 设置cookie
  setCookie: (cName, value, expiredays) => {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = cName + '=' + escape(value) + ((expiredays === null) ? '' : ';expires=' + exdate.toGMTString())
  },
// 获取cookie
  getCookie: (name) => {
    let reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
    let arr = document.cookie.match(reg)
    if (arr) {
      return unescape(arr[2])
    } else {
      return null
    }
  },
// 删除cookie
  delCookie: (name) => {
    var exp = new Date()
    exp.setTime(exp.getTime() - 1)
    var cval = cookies.getCookie(name)
    if (cval !== null) {
      document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString()
    }
  }
}


export const mixin = (obj) => {
  const base = {
    data() {
      return {
        $ucarConfig: {
          autoClose: false,
          openerCallBack: ''
        }
      }
    },
    computed: {
      userPermissions() {
        return this.$store.getters.permissions
      }
    },
    methods: {
      registryVm() {
        window.$currentVm = this
      },
      pageOver() {
        const timer = arguments[arguments.length - 1] &&
        typeof arguments[arguments.length - 1] === 'number' ? arguments[arguments.length - 1] : 300
        const opener = window.opener
        if (opener) {
          const {autoClose, openerCallBack} = this.$data.$ucarConfig
          if (openerCallBack &&
            typeof opener.$currentVm[openerCallBack] === 'function') {
            opener.$currentVm[openerCallBack](...arguments)
          }
          if (autoClose === true) {
            setTimeout(() => {
              window.close()
            }, timer)
          }
          ;
        } else {
          window.close();
        }
      },
      callNewPage(url, options = {}) {
        if (!options.autoClose) options.autoClose = true
        let path = /\/$/.test(url) ? url : `${url}/`
        const target = /^(http|https):\/\//.test(path) ? url
          : `/#${url}`
        let queryStr = ''
        for (let key in options) {
          queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
        }
        return window.open(`${target}${queryStr}`);
      },
      callDCMSNewPage(url, options = {}) {
        if (!options.autoClose) options.autoClose = true
        let path = /\/$/.test(url) ? url : `${url}/`
        const host = getDynamicUrl('//fcarcmtdcms.10101111.com/#/')
        const target = /^(http|https):\/\//.test(path) ? url
          : host + `${url}`
        let queryStr = ''
        for (let key in options) {
          queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
        }
        return window.open(`${target}${queryStr}`);
      },
      callDownPage(url, options = {}) {
        let path = /\?$/.test(url) ? url : `${url}?`;
        for (let key in options) {
          if (key == 'pageIndex') {
            continue;
          }
          path += `&${key}=${options[key]}`
        }
        window.open(path);
      },
      openForExport(url, params) {
        const target = url;
        let queryStr = '';
        for (let key in params) {
          let value = params[key] == null || params[key] == undefined ? '' : params[key];
          queryStr += queryStr === '' ? `?${key}=${value}` : `&${key}=${value}`
        }
        window.open(`${target}${queryStr}`)
      },
      errorMessage(msg, duration) {
        this.$message({
          showClose: true,
          message: msg,
          dangerouslyUseHTMLString: true,
          type: 'error',
          duration: typeof (duration) === "undefined" ? 3000 : duration
        });
      },
      successMessage(msg, duration) {
        this.$message({
          showClose: true,
          message: msg,
          type: 'success',
          duration: typeof (duration) === "undefined" ? 3000 : duration
        });
      },
      warnMessage(msg, duration) {
        this.$message({
          showClose: true,
          message: msg,
          type: 'warning',
          duration: typeof (duration) === "undefined" ? 3000 : duration
        });
      }
    },
    mounted() {
      this.$data.$ucarConfig.autoClose = !!this.$route.query.autoClose
      this.$data.$ucarConfig.openerCallBack = this.$route.query.callback
      this.registryVm()
    },
    beforeRouteUpdate() {
      this.registryVm()
    }
  }
  if (obj.mixins) {
    obj.mixins.push(base)
  } else {
    obj.mixins = [base]
  }
  return obj
}

export const getCurrEnvContext = () => {
  let env = ''
  switch (window.runtime_env) {
    case 'development':
      env = '开发环境'
      break
    case 'test':
      env = '测试一环境'
      break
    case 'test2':
      env = '测试二环境'
      break
    case 'test3':
      env = '测试三环境'
      break
    case 'preProduct':
      env = '预生产环境'
      break
    case 'product':
      env = '生产环境'
      break
    default:
      env = ''
  }
  return env
}

export const getDynamicUrl = (url, host = '10101111.com') => {
  /*const envMap = {
    development: '',
    test: 'fcarcmttest01',
    test2: 'fcarcmttest02',
    test3: 'fcarcmttest03',
    preProduct: 'adminpre',
    prod: 'admin'
  }*/

  const envMap = {
    development: 'test',
    test: 'test',
    test2: 'test02',
    test3: 'test03',
    preProduct: 'pre',
    production: ''
  }

  // window.runtime_env 在build/webpack.dev.conf.js|build/webpack.prod.conf.js中定义, 旨在标注当前代码运行以及打包时的环境
  if (!window.runtime_env) return url
  if (window.runtime_env === 'preProduct' || window.runtime_env === 'production') {
    host = 'alspark.cn'
  }
  if (/10101111\.com/.test(url)) url = url.replace('10101111.com', host)

  return url.replace(new RegExp(`(.+).${host}`), `$1${envMap[window.runtime_env]}.${host}`)
}

export const genRandomString = len => {
  const text = "abcdefghijklmnopqrstuvwxyz0123456789";
  const rdmIndex = text => (Math.random() * text.length) | 0;
  let rdmString = "";
  for (; rdmString.length < len; rdmString += text.charAt(rdmIndex(text))) ;
  return rdmString;
};
