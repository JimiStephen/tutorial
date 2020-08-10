export const ILLEGAL_CHARACTERS = {
  type: "pattern",
  pattern: /^[^~!@#￥%\.&\*?\[\]\{\}\^\&\*<>\\\/'"]*$/g,
  message: "请勿输入非法字符",
  trigger: ["blur", "change"]
};

export const ILLEGAL_CHARACTERS_ADDRESS = {
  type: "pattern",
  pattern: /^[^~!@￥%\.&\*?\[\]\{\}\^\&\*<>\\\/'"]*$/g,
  message: "请勿输入非法字符",
  trigger: ["blur", "change"]
};

export const PHONE_NUMBER_RULE = {
  type: 'pattern',
  pattern: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/g,
  message: '请输入正确的电话号码(11位数字)',
  trigger: ['blur', 'change']
};

export const EMAIL_RULE = {
  type: 'pattern',
  pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
  message: '请输入正确的邮箱地址',
  trigger: ['blur', 'change']
};

export const IDENTITY_CARD_RULE = {
  type: 'pattern',
  pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/g,
  message: '请输入正确的身份号',
  trigger: ['blur', 'change']
};
