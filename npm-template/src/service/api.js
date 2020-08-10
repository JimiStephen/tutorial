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
  url = '/fcarcmt' + url
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
            location.assign(getDynamicUrl('//fcarcmtadmin.10101111.com/#/'))
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

export const getImageRPCUrl = '/fcarcmt/api/upload/getImageWithRPCMode.do_'
export const getImageInternetUrl = '/fcarcmt/api/upload/getImageWithInternetMode.do_'
export const uploadImageRPCUrl = '/fcarcmt/api/upload/uploadWithRPCMode.do_'

export const getImageUrlWithRPCMode = (filename) => {
  var host = window.location.protocol + '//' + window.location.host
  return host + getImageRPCUrl + '?' + 'fileName=' + filename
}

export const getImageUrlWithInternetMode = (fileUrl) => {
  var host = window.location.protocol + '//' + window.location.host
  return host + getImageInternetUrl + '?' + 'fileUrl=' + fileUrl
}

export default {
  getCustomerList: query => get('/api/customer/list', query),
  getCustomer: id => get(`/api/customer/${id}`),
  addCustomer: body => post('/api/customer/new', body),
  updateCustomer: body => post('/api/customer/update', body),
  removeCustomer: body => post('/api/customer/delete', body),
  getMeetingAccount: query => get('/api/meeting/account/list', query),
  addMeetingAccount: body => post('/api/meeting/account/new', body),
  batchRemoveMeetingAccount: body => post('/api/meeting/account/remove', body),
  freezeAccount: body => post('/api/meeting/account/freeze', body),
  unfreezeAccount: body => post('/api/meeting/account/unfreeze', body),
  getInvitationCode: query => get('/api/invitation-code/list', query),
  // 门店准入管理start
  getAccessStorePage: body => request('/api/access/store/getAccessStorePage.do_', body, 'post'),
  getstopApplyAdmitStore: body => request('/api/access/store/stopApplyAdmitStore.do_', body, 'post'),
  getcheckApplyCanBeStop: query => request('/api/access/store/checkApplyCanBeStop.do_', query),
  // 门店准入管理end
  // 新建门店start
  getDistributorList4AccessStoreAdmit: query => request('/api/distributor/base/getDistributorList4AccessStoreAdmit.do_', query),
  getServiceStorePage: body => request('/api/distributor/service/getServiceStorePage.do_', body, 'post'),
  getapplyAdmitStore: body => request('/api/access/store/applyAdmitStore.do_', body, 'post'),
  getreApplyAdmitStore: body => request('/api/access/store/reApplyAdmitStore.do_', body, 'post'),
  // 新建门店end
  // 退出门店start
  getServiceStoreList: body => request('/api/distributor/service/getServiceStoreList.do_', body, 'post'),
  getDistributorList4AccessStoreExit: query => request('/api/distributor/base/getDistributorList4AccessStoreExit.do_', query),
  getapplyExitStore: body => request('/api/access/store/applyExitStore.do_', body, 'post'),
  getDataForExitStore: body => request('/api/access/store/getDataForExitStore.do_', body, 'post'),
  // 退出门店end
  getAccountList: query => request('/api/account/list.do_', query, 'post'),
  getAccountDistributorInfoList: query => request('/api/account/distributorInfoList.do_', query),
  addAccount: body => request('/api/account/add.do_', body, 'post', true),
  getAccount: body => request('/api/account/getaccount.do_', body, 'get'),
  getAccountForDiffPage: body => request('/api/account/getAccountForDiffPage.do_', body, 'get'),
  updateAccountStatus: body => request('/api/account/updateaccountstatus.do_', body, 'get'),
  resetPassword: body => request('/api/account/resetpwd.do_', body, 'get'),
  updateAccount: body => request('/api/account/update.do_', body, 'post', true),
  deleteAccount: query => request('/api/account/delete.do_', query),
  deleteSecurityTelephone: query => request('/api/account/deletesecuritytelephone.do_', query),
  getDistributorOpenedBusinessLine: body => request('/api/account/getDistributorOpenedBusinessLine.do_', body, 'get'),
  getDistributorBusinessLineWithStatus: body => request('/api/account/getDistributorBusinessLineWithStatus.do_', body, 'post'),
  getDistributorBusInfo: body => request('/api/account/getdistributorbusinfo.do_', body, 'post', true),
  getAccountHistoryList: body => request('/api/account/getAccountHistoryList.do_', body, 'get'),
  getBizGradeList: query => request('/api/paramconfig/bizGrade/list.do_', query, 'post'),
  addBizGrade: body => request('/api/paramconfig/bizGrade/add.do_', body, 'post'),
  getBizGrade: query => request('/api/paramconfig/bizGrade/get.do_', query),
  updateBizGrade: body => request('/api/paramconfig/bizGrade/update.do_', body, 'post'),
  getBizChangeReasonList: query => request('/api/paramconf/bizChangeReason/list.do_', query),
  addBizChangeReason: body => request('/api/paramconf/bizChangeReason/add.do_', body),
  getBizChangeReason: query => request('/api/paramconf/bizChangeReason/get.do_', query),
  updateBizChangeReason: query => request('/api/paramconf/bizChangeReason/update.do_', query),
  addDistributor: body => request('/api/distributorInfo/add.do_', body, 'post'),
  addDistributorService: body => request('/api/distributorInfo/addDistributorService.do_', body, 'post'),
  getDistributorInfoList: body => request('/api/distributorInfo/distributorInfoList.do_', body, 'post'),
  getBankHistoryList: query => request('/api/distributorInfo/getBankHistoryList.do_', query),
  getDistributorInfo: query => request('/api/distributorInfo/distributorInfo.do_', query),
  getDistriInfoById: query => request('/api/distributorInfo/getDistriInfoById.do_', query),
  getBusinessLevel: query => request('/api/distributorInfo/getBusinessType.do_', query),
  adjustBusinessLevel: body => request('/api/distributorInfo/adjustBusinessLevel.do_', body, 'post'),
  queryDistrByCityCode: query => request('/api/distributorInfo/queryDistrByCityCode.do_', query),
  getLevelHistoryList: body => request('/api/distributorInfo/getLevelHistoryList.do_', body, 'post'),

  getProvinceCityDistrData: query => request('/api/distributorInfo/getProvinceCityDistrData.do_', query),
  getGbPrefecturesData: query => request('/api/gbPrefecture/getProvinceCityDistrictData.do_', query),
  getWorker: body => request('/api/distributorInfo/getWorker.do_', body, 'post'),
  getDeptByCityId: body => request('/api/distributorInfo/getDeptByCityId.do_', body, 'post'),
  getCityById: query => request('/api/distributorInfo/getCityById.do_', query),
  getManufacturerList: query => request('/api/distributorInfo/getManufacturerList.do_', query),
  updateDistributorInfo: body => request('/api/distributorInfo/update.do_', body, 'post'),
  getDeptListByType: body => request('/api/distributorInfo/getDeptListByType.do_', body, 'get'),
  getLoginMessage: query => request('/api/distributorInfo/getEmpInfo.do_', query),
  verifyBankInfo: query => request('/api/distributorInfo//verifyBankInfo.do_', query, 'post'),
  commonExp: query => request('/api/base/commonFileExport.do_', query),
  distributorServiceList: body => request('/api/business/distributorServiceList.do_', body, 'post'),
  updateBusinessStatus: body => request('/api/business/updateBusinessStatus.do_', body),
  getBusinessOpHisList: body => request('/api/business/businessOperateHisList.do_', body),
  updateBusinessLimit: body => request('/api/business/updateBusinessLimit.do_', body),
  getServiceType4ComboBox: body => request('/api/comboBox/getServiceType4ComboBox.do_', body),
  getBusinessStatus4ComboBox: body => request('/api/comboBox/getBusinessStatus4ComboBox.do_', body),
  getGrade4ComboBox: body => request('/api/comboBox/getGrade4ComboBox.do_', body),
  getChangeReason4ComboBox: body => request('/api/comboBox/getBizChangeReason4ComboBox.do_', body),
  getCity4ComboBox: body => request('/api/comboBox/getCity4ComboBox.do_', body),
  getVersionList: query => request('/api/version/list.do_', query, 'post'),
  addVersion: body => request('/api/version/add.do_', body, 'post'),
  getVersion: query => request('/api/version/get.do_', query),
  updateVersion: body => request('/api/version/update.do_', body, 'post'),
  deleteVersion: query => request('/api/version/delete.do_', query),
  getBusinessProductList: query => request('/api/business/getBusinessProductList.do_', query),
  updateBusinessProductStatus: body => request('/api/business/updateBusinessProductStatus.do_', body, 'post'),
  getOperateServiceTypes: query => request('/api/business/getOperateServiceTypes.do_', query),

  // 档案管理
  checkDistributorModify: body => request('/api/distributor/base/checkDistributorModify.do_', body),
  getDistributorBase: body => request('/api/distributor/base/getBaseModify.do_', body),
  checkRegistrationCode: body => request('/api/distributor/base/checkRegistrationCode.do_', body, 'get', false, null, true),
  checkPrincipal: body => request('/api/distributor/base/checkPrincipal.do_', body, 'get', false, null, true),
  checkDistributor: body => request('/api/distributor/base/checkDistributor.do_', body, 'post', true),
  getDistributorService: body => request('/api/distributor/service/getServiceModify.do_', body),
  getInitConsigncarInfo: body => request('/api/distributor/service/getInitConsigncarInfo.do_', body),
  getDistributorStoreList: body => request('/api/distributor/service/getStoreList.do_', body),
  saveDistributor: body => request('/api/distributor/base/saveDistributor.do_', body, 'post', true),
  getDistributorRetailList: (query) => request('/api/distributor/base/getDistributorRetailList.do_', query, 'get', false, null, true),

  // 准入管理start
  getFreeLoanDistributionList: (query) => request('/api/freeLoanDistribution/list.do_', query),
  updateApprovalInfo: (query) => request('/api/freeLoanDistribution/updateApprovalInfo.do_', query, 'post', true),
  getOperateRecordList: (query) => request('/api/operateRecord/list.do_', query),
  getAccessApplyDetail: (query) => request('/api/freeLoanDistribution/getAccessApplyDetail.do_', query),
  // 准入管理end

  // 上传系统获得基本的url
  getBaseUrl: body => request('/api/fileUpload/getBaseUrl.do_', body),
  // 准入申请start
  getDistributorApplyEditInfo: (query) => request('/api/applyEdit/applyInfo.do_', query), // 请求准入申请的基础信息
  updateDistributorApplyEdit: (body) => request('/api/applyEdit/saveApplyInfo.do_', body, 'post', true), // 提交保存准入申请信息
  // 准入申请end

  // 运营管理
  getOperationInfoManagelist: query => request('/api/operationInfoManage/list.do_', query),
  addOperationInfoManage: body => request('/api/operationInfoManage/add.do_', body, 'post'),
  addAndPublishOperationInfoManage: body => request('/api/operationInfoManage/addAndPublish.do_', body, 'post'),
  modifyOperationInfoManage: body => request('/api/operationInfoManage/modify.do_', body, 'post'),
  updateSetTop: body => request('/api/operationInfoManage/updateSetTop.do_', body, 'post'),
  countSetTop: body => request('/api/operationInfoManage/countSetTop.do_', body, 'post'),
  modifyAndPublishOperationInfoManage: body => request('/api/operationInfoManage/modifyAndPublish.do_', body, 'post'),
  undistributedOperationInfoManage: id => request('/api/operationInfoManage/undistributed.do_', id),
  publishOperationInfoManage: id => request('/api/operationInfoManage/publish.do_', id),
  getInfoOperationInfoManage: id => request('/api/operationInfoManage/getInfo.do_', id),
  delOperationInfoManage: id => request('/api/operationInfoManage/del.do_', id),
  getOperationDisplayManagelist: query => request('/api/operationDisplayManage/list.do_', query),
  addOperationDisplayManage: body => request('/api/operationDisplayManage/add.do_', body, 'post'),
  addAndPublishOperationDisplayManage: body => request('/api/operationDisplayManage/addAndPublish.do_', body, 'post'),
  modifyOperationDisplayManage: body => request('/api/operationDisplayManage/modify.do_', body, 'post'),
  modifyAndPublishOperationDisplayManage: body => request('/api/operationDisplayManage/modifyAndPublish.do_', body, 'post'),
  undistributedOperationDisplayManage: id => request('/api/operationDisplayManage/undistributed.do_', id),
  publishOperationDisplayManage: id => request('/api/operationDisplayManage/publish.do_', id),
  getDisplayOperationDisplayManage: id => request('/api/operationDisplayManage/getDisplay.do_', id),
  delOperationDisplayManage: id => request('/api/operationDisplayManage/del.do_', id),
  isHaveHot: body => request('/api/operationDisplayManage/isHaveHot.do_'),
  getOperationFeedback: query => request('/api/operation/feedback/list.do_', query, 'post'),
  getInitProblem: query => request('/api/operation/problem/init.do_'),
  getOperationProblem: query => request('/api/operation/problem/get.do_', query),
  getOperationProblemList: query => request('/api/operation/problem/list.do_', query, 'post'),
  addOperationProblem: body => request('/api/operation/problem/save.do_', body, 'post'),
  removeOperationProblem: body => request('/api/operation/problem/delete.do_', body, 'post'),
  publishOperationProblem: body => request('/api/operation/problem/publish.do_', body, 'post'),
  cancelPublishOperationProblem: body => request('/api/operation/problem/cancelPublish.do_', body, 'post'),
  getInitOperationGuide: query => request('/api/operation/guide/init.do_'),
  getOperationGuide: query => request('/api/operation/guide/get.do_', query),
  getOperationGuideList: query => request('/api/operation/guide/list.do_', query),
  addOperationGuide: body => request('/api/operation/guide/save.do_', body, 'post'),
  publishOperationGuide: body => request('/api/operation/guide/publish.do_', body, 'post'),
  cancelPublishOperationGuide: body => request('/api/operation/guide/cancelPublish.do_', body, 'post'),
  deleteOperationGuideAttachment: body => request('/api/operation/guide/deleteAttachment.do_', body, 'post'),

  // 地区
  getProvinceList: query => request('/api/province/list.do_', query),
  getProvinces: query => request('/api/province/select.do_', query),
  deleteProvince: body => request('/api/province/delete.do_', body),
  saveProvince: body => request('/api/province/save.do_', body, 'post'),
  validateProvince: body => request('/api/province/validate.do_', body),
  isExistsCity: body => request('/api/province/isExistsCity.do_', body),
  // 银行
  getBankList: query => request('/api/bank/list.do_', query),
  saveBank: body => request('/api/bank/save.do_', body, 'post'),
  validateBank: body => request('/api/bank/validate.do_', body),
  // 城市   这是查询本地数据库的 方法
  getCityList: query => request('/api/city/list.do_', query),
  saveCity: body => request('/api/city/save.do_', body, 'post'),
  getCities: query => request('/api/city/select.do_', query),
  getCitiesByParam: query => request('/api/city/query.do_', query),
  deleteCity: body => request('/api/city/delete.do_', body),
  validateCity: body => request('/api/city/validate.do_', body),
  isExistsDistrict: body => request('/api/city/isExistsDistrict.do_', body),
  // 区县  这是查询本地数据库的 方法
  getDistrictList: query => request('/api/district/list.do_', query),
  saveDistrict: body => request('/api/district/save.do_', body, 'post'),
  getDistricts: query => request('/api/district/select.do_', query),
  deleteDistrict: body => request('/api/district/delete.do_', body),
  validateDistrict: body => request('/api/district/validate.do_', body),
  // 字典
  getDictionaryTypeList: query => request('/api/dictionary/typelist.do_', query),
  saveDictionaryType: body => request('/api/dictionary/savetype.do_', body, 'post'),
  validateDictionaryType: body => request('/api/dictionary/validatetype.do_', body),
  deleteDictionaryType: body => request('/api/dictionary/deletetype.do_', body),
  getDictionaryValueList: query => request('/api/dictionary/valuelist.do_', query),
  saveDictionaryValue: body => request('/api/dictionary/savevalue.do_', body, 'post'),
  validateDictionaryValue: body => request('/api/dictionary/validatevalue.do_', body),
  deleteDictionaryValue: body => request('/api/dictionary/deletevalue.do_', body),
  queryDictionaryValueByType: query => request('/api/dictionary/querydictionaryvaluebytype.do_', query),
  // 获取图片数据
  getImageBytes: fileName => request(getImageRPCUrl, fileName),
  // 获取档案操作历史记录
  getOperateHisList: query => request('/api/distributor/operateHis/getOperateHisList.do_', query),
  // 获主档案页面历史记录
  getDistributorBaseHis: query => request('/api/distributor/base/getDistributorBaseHis.do_', query),
  // 获取业务线历史记录
  getDistributorServiceHis: query => request('/api/distributor/serviceHis/getDistributorServiceHis.do_', query),
  getDistributorDefendHis: query => request('/api/distributor/serviceHis/getDistributorDefendHis.do_', query),
  getDistributorInviterHis: query => request('/api/distributor/serviceHis/getDistributorInviterHis.do_', query),
  // 潜在经销商
  getPotentialList: body => request('/api/potential/potentialList.do_', body, 'post'),
  // 新车分销商机转化
  getDistributorConversionList: body => request('/api/marketing/distributorConversionList.do_', body, 'post'),
  // 推广码管理
  getDistributorPromotionList: body => request('/api/marketing/distributorPromotionList.do_', body, 'post'),
  getDistributorPromotionDetail: body => request('/api/marketing/distributorPromotionDetail.do_', body, 'post'),
  getDistributorPromotionHistoryList: body => request('/api/marketing/distributorPromotionHistoryList.do_', body, 'post'),
  saveDistributorPromotion: body => request('/api/marketing/saveDistributorPromotion.do_', body, 'post'),
  // 车辆分销留资管理
  getMaterialPotentialList: body => request('/api/material/distributorPotentialList.do_', body, 'post'),
  staffQuery: body => request('/api/employee/getEmpsByNameOrNo.do_', body, 'post'),
  updateAndFollowup: body => request('/api/material/updateAndFollowup.do_', body, 'post', true),
  sendApplyInviteMsg: body => request('/api/material/sendApplyInviteMsg.do_', body, 'post', true),
  changeFollowUpCompany: body => request('/api/material/changeFollowUpCompany.do_', body, 'post', true),
  assignFollowUpPerson: body => request('/api/material/assignFollowUpPerson.do_', body, 'post'),
  followUpHistorySearch: body => request('/api/material/followUpHistorySearch.do_', body, 'post'),
  // 400电话管理
  getHotlineList: body => request('/api/material/hotlineList.do_', body, 'post'),
  saveHotline: body => request('/api/material/saveHotline.do_', body, 'post'),
  delHotline: body => request('/api/material/delHotline.do_', body),

  getRegistrationCodeByDistributorCode: query => request('/api/distributorInfo/getRegistrationCode.do_', query),
  updateDistributorCreditCode: query => request('/api/distributorInfo/updateDistributorCreditCode.do_', query),

  // 国标省市区
  getAreaInfo4ComboBox: body => request('/api/comboBox/getAreaInfo4ComboBox.do_', body),

  // 保证金配置-城市等级配置
  getCapitalDepositRulePage: body => request('/api/capital/getCapitalDepositRulePage.do_', body, 'post'),
  getCapitalDepositRuleHisPage: body => request('/api/capital/getCapitalDepositRuleHisPage.do_', body, 'post'),
  getCapitalDepositRuleDetail: body => request('/api/capital/getCapitalDepositRuleDetail.do_', body, 'post'),
  saveCapitalDepositRule: body => request('/api/capital/saveCapitalDepositRule.do_', body, 'post'),

  // 保证金配置-门店保证金规配置
  getCapitalDepositStoreRuleList: body => request('/api/capital/getCapitalDepositStoreRuleList.do_', body, 'post'),
  saveCapitalDepositStoreRuleList: body => request('/api/capital/saveCapitalDepositStoreRuleList.do_', body, 'post', true),

  // 宝沃解约单
  judgeTerminateCondition: body => request('/api/business/judgeTerminateCondition.do_', body),
  initTerminateInfo: body => request('/api/business/initTerminateInfo.do_', body),
  createTerminate: body => request('/api/business/createTerminate.do_', body, 'post'),
  getTerminateList: body => request('/api/business/getTerminateList.do_', body, 'post'),
  getTerminateInfo: query => request('/api/business/getTerminateInfoById.do_', query),

  // 业务线（二手车/维保/租车）
  changeCustomerManager: body => request('/api/business/mult/changeCustomerManager.do_', body),
  queryBusinessList: body => request('/api/business/mult/queryBusinessList.do_', body, 'post'),
  // 销售部门详情列表查询
  querySuggestSalesPointList: body => request(
    '/api/distributorAgentPoint/querySuggestSalesPointList.do_', body, 'post', false, '', true),

  // 可挂靠门店列表查询接口
  queryAffiliatedStoreList: body => request('/api/distributorAgentPoint/queryAffiliatedStoreList.do_', body, 'post'),

  // 销售部门详情列表查询
  querySuggestDistributorInfoList: body => request(
    '/api/distributorInfo/querySuggestDistributorInfoList.do_', body, 'post', false, '', true),

  // 门店代办点挂靠关系详情列表查询
  queryAffiliatedList: body => request('/api/distributorAgentPoint/list.do_', body, 'post'),

  // 代办点挂靠信息历史记录
  queryAffiliatedHisList: body => request('/api/distributorAgentPoint/queryAffiliatedHisList.do_', body, 'post'),

  // 创建销售部门(代办点)挂靠关系
  insertAffiliatedAgentPointInfo: body => request('/api/distributorAgentPoint/insertAffiliatedAgentPointInfo.do_', body, 'post'),

  // 修改销售部门(代办点)挂靠关系
  modifyAffiliatedAgentPointInfo: body => request('/api/distributorAgentPoint/modifyAffiliatedAgentPointInfo.do_', body, 'post'),

  // 门店管理-创建门店时，获取租车、维保、委外业务线至少一条业务线已开通的经销商
  getDistributorListWhenCreateStore: query => request('/api/distributorInfo/getDistributorListWhenCreateStore.do_', query),
  // 根据经销商标识获得当前登录账号有权限的该经销商业务线
  getAuthorizedServiceTypeList: query => request('/api/distributorInfo/getAuthorizedServiceTypeList.do_', query),
  bMapPlaceSearch: query => request('/api/common/bMap/placeSearch.do_', query),

  // 门店管理
  queryStoreList: body => request('/api/distributor/store/list.do_', body, 'post'),
  // 门店业务线申请退出
  applyExitStoreBusiness: body => request('/api/distributor/storeBiz/applyExitStoreBusiness.do_', body, 'post'),
  // 门店业务线详情信息
  getStoreBusinessDetail: body => request('/api/distributor/store/getStoreBusinessDetail.do_', body, 'get'),
  // 门店操作历史信息列表
  getStoreBusinessOperateHistoryList: body => request('/api/distributor/storeBiz/getStoreBusinessOperateHistoryList.do_', body, 'get'),
  // 维保门店列表查询
  queryStoreBusinessDetailList: body => request('/api/distributor/store/storeBusinessDetailList.do_', body, 'post'),
  getStoryHistoryList: body => request('/api/distributor/storeHistory/storeHistoryList.do_', body, 'post'),
  checkModify: body => request('/api/distributor/store/checkStoreModify.do_', body, 'get', false, null, true),

  // 展车模块接口
  exhibitionCarList: body => request('/api/accessStoreExhibitionCar/list.do_', body, 'post', true),
  // getStoreList: body => request('/api/accessStoreExhibitionCar/getStoreList.do_',body,'post'),
  getExhibitionCarDistributors: body => request('/api/accessStoreExhibitionCar/getExhibitionCarDistributors.do_', body, 'post'),
  getStoreBaseInfo: body => request('/api/accessStoreExhibitionCar/getStoreBaseInfo.do_', body, 'post'),
  applyExibitionCar: body => request('/api/accessStoreExhibitionCar/applyExibitionCar.do_', body, 'post', true),
  returnExibitionCar: body => request('/api/accessStoreExhibitionCar/returnExibitionCar.do_', body, 'post', true),
  getExhibitionCarDetailInfo: body => request('/api/accessStoreExhibitionCar/getExhibitionCarDetailInfo.do_', body, 'post'),
  stopExibitionCar: body => request('/api/accessStoreExhibitionCar/stopExibitionCar.do_', body, 'post'),
  getDetailInfoByVersion: body => request('/api/accessStoreExhibitionCar/getDetailInfoByVersion.do_', body, 'post'),
  checkExhibitionCarApplyCanBeStop: body => request('/api/accessStoreExhibitionCar/checkExhibitionCarApplyCanBeStop.do_', body, 'post'),
  // 创建部门一系列接口
  getUnhandledStoreDeptPage: body => request('/api/task/store/getUnhandledStoreDeptPage.do_', body, 'post'),
  getHandledStoreDeptPage: body => request('/api/task/store/getHandledStoreDeptPage.do_', body, 'post'),
  getStoreDeptInfo: body => request('/api/task/store/getStoreDeptInfo.do_', body, 'post'),
  getSaveStoreDept: body => request('/api/task/store/saveStoreDept.do_', body, 'post', true),
  getEmployeeByQuery: body => request('/api/remote/amp/getEmployeeByQuery.do_', body, 'post'),
  getSubDeptsById: body => request('/api/remote/amp/getSubDeptsById.do_', body, 'post'),
  getBusinessTypeList: body => request('/api/remote/amp/getBusinessTypeList.do_', body, 'post'),
  submitStoreDept: body => request('/api/task/store/submitStoreDept.do_', body, 'post', true),
  // 留资跟进子公司设置模块接口
  getSubsidiaryList: body => request('/api/materialFollowupCompany/list.do_', body, 'post'),
  getSubsidiaryDetail: body => request('/api/materialFollowupCompany/detail.do_', body, 'post', true),
  addSubsidiary: body => request('/api/materialFollowupCompany/insert.do_', body, 'post'),
  modifySubsidiary: body => request('/api/materialFollowupCompany/update.do_', body, 'post'),
  deleteSubsidiary: body => request('/api/materialFollowupCompany/deleteById.do_', body, 'post')
}

export const account = {
  getFuncMenuTree: body => request('/api/account/getFuncMenuTree.do_', body, 'post', true),
  getFuncMenuTreeForAdmin: body => request('/api/account/getFuncMenuTreeForAdmin.do_', body, 'post', true),
  getFuncMenuTreeForDetailPage: body => request('/api/account/getFuncMenuTreeForDetailPage.do_', body, 'post', true)
}

export const distributorBranch = {
  branchTree: body => request('/api/distributorBranch/tree.do_', body, 'get'),
  getStoreDetail: body => request('/api/distributorBranch/getStoreDetail.do_', body, 'get'),
  getSaleoutletDetail: body => request('/api/distributorBranch/getSaleoutletDetail.do_', body, 'get'),
  addSaleoutlet: body => request('/api/distributorBranch/addSaleoutlet.do_', body, 'post'),
  updateSaleoutlet: body => request('/api/distributorBranch/updateSaleoutlet.do_', body, 'post'),
  checkAccountExist: body => request('/api/distributorBranch/checkAccountExist.do_', body, 'post'),
  deleteSaleoutlet: body => request('/api/distributorBranch/deleteSaleoutlet.do_', body, 'post')
}

export const appShareInfo = {
  getAppShareInfoList: query => request('/api/appShareInfo/list.do_', query, 'post'),
  addAppShareInfo: body => request('/api/appShareInfo/add.do_', body, 'post'),
  getAppShareInfo: query => request('/api/appShareInfo/get.do_', query),
  updateAppShareInfo: body => request('/api/appShareInfo/update.do_', body, 'post'),
  deleteAppShareInfo: query => request('/api/appShareInfo/delete.do_', query),
  enableAppShareInfo: query => request('/api/appShareInfo/enable.do_', query),
  disableAppShareInfo: query => request('/api/appShareInfo/disable.do_', query)
}
export const bondReturn = {
  orderList: (query) => request('/api/bondreturn/list.do_', query, 'post', true),
  addOrder: (body) => request('/api/bondreturn/add.do_', body, 'post', true),
  updateOrder: (body) => request('/api/bondreturn/update.do_', body, 'post', true),
  getOrder: (body) => request('/api/bondreturn/get.do_', body, 'get'),
  getBondDistributor: (body) => request('/api/bondreturn/getBondDistributor.do_', body, 'post'),
  getBondServiceStores: (body) => request('/api/bondreturn/getBondServiceStores.do_', body, 'post'),
  cancelOrder: (body) => request('/api/bondreturn/cancel.do_', body, 'post', true),
  orderHistory: (query) => request('/api/bondreturn/orderHistory.do_', query, 'post', true)
}

export const permission = {
  getPermissionForButton: (query) => {
    return request('/fcar/admin/common/security/getUserButtonAuth.do_', query).then(re => {
      return Promise.resolve(re.data.split(','))
    })
  }
}
