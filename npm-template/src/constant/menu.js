export default [

  {
    // 目录或具体路由的标题
    title: '个人客户管理',
    // icon根据element-ui的icon官方文档选择
    icon: 'el-icon-service',
    // path以_开头则表示这是一个目录path并不是路由的路径
    path: '_customer',
    // subMenus 代表子级菜单, 其中元素的字段名除icon外与父级相同
    subMenus: [
      {
        title: '客户信息',
        path: '/customer/list'
      },
      {
        title: '新建客户',
        path: '/customer/info',
        // hidden=true 代表不会在菜单中显示
        hidden: true
      },
      {
        title: '客户信息',
        // 动态路由示例 :id 为传入参数
        path: '/customer/info/:id',
        hidden: true
      }
    ]
  },
  {
    title: '角色&权限',
    icon: 'el-icon-setting',
    path: '_role_menu',
    subMenus: [
      {
        title: '角色管理',
        path: '/system/role/list'
      },
      {
        title: '权限管理',
        path: '/system/menu/list',
        hidden: false
      },
      {
        title: '角色权限',
        path: '/system/role/auth/:id',
        hidden: true
      },
      {
        path: '/system/role/assign/:id',
        title: '员工分配列表',
        hidden: true
      },
      {
        path: '/system/role/filter/:id',
        title: '员工(权限|角色)筛选列表',
        hidden: true
      }
    ]
  },
  {
    title: '账号管理',
    icon: 'el-icon-view',
    path: '_system/account',
    subMenus: [
      {
        title: '修改密码',
        path: '/system/account/account'
      }
    ]
  },
  {
    title: '部门管理',
    icon: 'el-icon-view',
    path: '_system/department',
    subMenus: [
      {
        title: '部门列表',
        path: '/system/department/list'
      },
      {
        title: '新建子部门',
        path: '/system/department/info',
        hidden: true
      },
      {
        title: '修改信息',
        path: '/system/department/info/:id',
        hidden: true
      },
      {
        title: '修改上级部门',
        path: '/system/department/assign/:id',
        hidden: true
      }
    ]
  },
  {
    title: '员工管理',
    icon: 'el-icon-view',
    path: '_system/employee',
    subMenus: [
      {
        title: '员工列表',
        path: '/system/employee/list'
      },
      {
        title: '修改信息',
        path: '/system/employee/info/:id',
        hidden: true
      },
      {
        title: '分配权限',
        path: '/system/employee/auth/:id',
        hidden: true
      },
      {
        title: '分配部门',
        path: '/system/employee/department/:id',
        hidden: true
      }
    ]
  },
  {
    title: '邀请码管理',
    icon: 'el-icon-mobile-phone',
    path: '_invitation-code',
    subMenus: [
      {
        title: '邀请码信息',
        path: '/invitation-code/list'
      }
    ]
  },
  {
    title: '会议账号管理',
    icon: 'el-icon-date',
    path: '_meeting',
    subMenus: [
      {
        title: '会议账号',
        path: '/meeting/account'
      }
    ]
  },
  {
    title: '用户管理',
    icon: 'el-icon-view',
    path: '_user',
    subMenus: [
      {
        title: '用户列表',
        path: '/user/list'
      },
      {
        title: '新建信息',
        path: '/user/info',
        hidden: true
      },
      {
        title: '修改信息',
        path: '/user/info/:id',
        hidden: true
      }
    ]
  },
  {
    title: '准入管理',
    icon: 'el-icon-menu',
    path: '_access-apply',
    subMenus: [
      {
        title: '宝沃新零售',
        path: '/access-apply/free-loan-distribution/list'
      },
      {
        title: '准入审核单详情',
        path: '/access-apply/free-loan-distribution/info/:id/:serviceType/:serviceHistoryId/:applyNo/:infoType/:operateType/:operateId',
        hidden: true
      }
    ]

  },
  {
    title: '经销商信息管理',
    icon: 'el-icon-view',
    path: '_distributor',
    subMenus: [
      {
        title: '经销商档案管理',
        path: '/distributor/list'
      },
      {
        title: '新建经销商信息',
        path: '/distributor/distributorMessage/createDistributorPerson',
        hidden: true
      },
      {
        title: '修改经销商信息',
        path: '/distributor/distributorMessage/modifyDistributorPerson/:id',
        hidden: true
      },
      {
        title: '申请准入',
        path: '/distributor/applyAccess/applyAccessMessage/:id/:serviceType',
        hidden: true
      },
      {
        title: '新增服务类型',
        path: '/distributor/addServiceType/addDistributor/:id',
        hidden: true
      },
      {
        title: '宝沃新零售-个人',
        path: '/distributor/freeLoan'
      },
      {
        title: '宝沃新零售-机构',
        path: '/distributor/freeLoan_org'
      },
      {
        title: '银行',
        path: '/distributor/bank'
      },
      {
        title: '其他信息',
        path: '/distributor/others_info'
      },
      {
        title: '城市',
        path: '/distributor/city'
      },
      {
        title: '邀请人信息',
        path: '/distributor/inviter'
      },
      {
        title: '维护人信息',
        path: '/distributor/maintenance'
      }

      ,
      {
        title: '金融分销-个人',
        path: '/distributor/finance_person'
      }
      ,
      {
        title: '金融分销-机构',
        path: '/distributor/finance_org'
      }
      ,
      {
        title: '授信信息',
        path: '/distributor/credit_info'
      },
      {
        title: '业务线信息',
        path: '/distributor/business_line'
      }
      , {
        title: '申请准入',
        path: '/distributor/application_access'
      }, {
        title: '业务线',
        path: '/distributor/bussess_info',
        hidden: true
      },
      {
        title: '经销商新增操作',
        path: '/distributor/distributor_option',
        hidden: true
      },
    ]
  },
  {
    title: '业务线管理',
    icon: 'el-icon-tickets',
    path: '_business',
    subMenus: [
      {
        title: '业务线查询',
        path: '/business/businessLineList'
      },
      {
        title: '业务线状态变更历史记录',
        path: '/business/opHisList/:jsonStr',
        hidden: true
      }
    ]
  },
  {
    title: '分支机构查询',
    icon: 'el-icon-tickets',
    path: '_department',
    subMenus: [
      {
        title: '代办点挂靠关系查询',
        path: '/department/agentPointList'
      }
    ]
  },
  {
    title: '账户管理',
    icon: 'el-icon-view',
    path: '_account',
    subMenus: [
      {
        title: '账号列表',
        path: '/account/list'
      },
      {
        title: '新建账号',
        path: '/account/baseinfo',
        hidden: true
      }
    ]
  },
  {
    title: 'OPEN API管理',
    icon: 'el-icon-view',
    path: '_openapi',
    subMenus: [
      {
        title: '版本信息',
        path: '/version/appVersionList'
      },
      {
        title: 'APP分享',
        path: '/version/appShareList'
      }
    ]
  },
  {
    // 目录或具体路由的标题
    title: '运营管理',
    // icon根据element-ui的icon官方文档选择
    icon: 'el-icon-service',
    // path以_开头则表示这是一个目录path并不是路由的路径
    path: '_operation',
    // subMenus 代表子级菜单, 其中元素的字段名除icon外与父级相同
    subMenus: [
      {
        title: '资讯管理',
        path: '/operation/infomanage/list'
      },

      {
        title: '展示位管理',
        path: '/operation/displaymanage/list'
      },
      {
        title: '意见反馈',
        path: '/operation/feedback/feedback'
      },
      {
        title: '常见问题',
        path: '/operation/faqs/problem'
      },
      {
        title: '新建问题',
        path: '/operation/faqs/info',
        hidden: true
      },
      {
        title: '修改问题',
        path: '/operation/faqs/info/:id',
        hidden: true
      },
      {
        title: '操作指南',
        path: '/operation/guide/guide'
      },
      {
        title: '修改操作指南',
        path: '/operation/guide/guide/info/:id',
        hidden: true
      }
    ]
  },

  {
    title: '基础信息配置',
    icon: 'el-icon-location',
    path: '_info',
    subMenus: [
      {
        title: '省',
        path: '/province/list'
      },
      {
        title: '银行列表',
        path: '/bank/list'
      },
      {
        title: '市',
        path: '/city/list'
      },
      {
        title: '区县',
        path: '/district/list'
      },
    ]
  }
]
