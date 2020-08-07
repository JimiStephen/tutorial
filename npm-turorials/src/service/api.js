import axios from 'axios'
import qs from 'qs'
import vue from 'vue'
import {cookies, getDynamicUrl} from '@/utils'
import {Loading} from 'element-ui'

const baseUrl = getDynamicUrl('')
let requestCount = 0
export const request = (url, body, type = 'get', isJson = false, baseUrlRewrite, disableLoading = false) => {
    let loadingInstance
    if (!disableLoading) {
        requestCount++
        loadingInstance = Loading.service({
            lock: true,
            background: 'rgba(0, 0, 0, 0.7)',
            customClass: 'el-loading-service-ex'
        })
    }
    url = '/npm-turorials' + url
    const query = {
        url: `${baseUrlRewrite || baseUrl}${url}`,
        method: type,
        withCredentials: true,
        timeout: 30000
    }
    if (type === 'get') {
        query.params = body
    } else {
        query.data = isJson ? body : qs.stringify(body)
    }
    return axios.request(query)
        .then(res => {
            return new Promise((resolve, reject) => {
                if (!res.data) {
                    reject(new Error('服务器响应超时'))
                    return
                }
                if (res.data.status === 0) {
                    resolve(res.data.re)
                } else {
                    res.data.message = res.data.msg
                    reject(res.data)
                }
            })
        }, e => {
            if (!e.response) {
                return Promise.reject(new Error('服务器响应超时'))
            }
            switch (e.response.status) {
                case 401: // 未登录跳转至登录页
                    if (cookies.getCookie('ucarincLogoutUrl')) {
                        const ucarincLogoutUrl = cookies.getCookie('ucarincLogoutUrl')
                        location.href = ucarincLogoutUrl
                        cookies.delCookie('ucarincLogoutUrl')
                    } else {
                        top.window.postMessage({
                            type: 'NO_LOGIN',
                            msg: '401'
                        }, '*')
                        location.assign(getDynamicUrl('//npm-turorialsadmin.10101111.com/#/'))
                    }
                    return Promise.reject(new Error('登录超时，请重新登录'))
                case 403: // 无权限操作
                    top.window.postMessage({
                        type: 'NO_PERMISSION',
                        msg: '403'
                    }, '*')
                    return Promise.reject(new Error('无权访问此资源'))
                default:
                    break
            }
            return Promise.reject(e.response)
        })
        .catch(e => {
            if (isShowErrorInfo(e)) {
                vue.prototype.$message.error(e && e.message ? e.message : '系统错误')
            }
            return Promise.reject(e)
        }).finally(() => {
            if (!disableLoading) {
                requestCount--
                if (!requestCount) {
                    if (!disableLoading) {
                        loadingInstance.close()
                    }
                }
            }
        })
}

export const isShowErrorInfo = (e) => {
    if (e.code && e.code !== '002' && e.code !== '003') {
        return true
    } else {
        return false
    }
}

export const removeTrimEnd = (str, end) => {
    str = (str.substring(str.length - 1) == end) ? str.substring(0, str.length - 1) : str
    return str
}

export const getImageRPCUrl = '/npm-turorials/api/upload/getImageWithRPCMode.do_'
export const getImageInternetUrl = '/npm-turorials/api/upload/getImageWithInternetMode.do_'
export const uploadImageRPCUrl = '/npm-turorials/api/upload/uploadWithRPCMode.do_'

export const getImageUrlWithRPCMode = (filename) => {
    var host = window.location.protocol + '//' + window.location.host
    return host + getImageRPCUrl + '?' + 'fileName=' + filename
}

export const getImageUrlWithInternetMode = (fileUrl) => {
    var host = window.location.protocol + '//' + window.location.host
    return host + getImageInternetUrl + '?' + 'fileUrl=' + fileUrl
}

export default {
    getCustomerList: query => get('/api/customer/list', query)
};