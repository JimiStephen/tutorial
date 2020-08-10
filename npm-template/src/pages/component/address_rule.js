export const createRules = (areaNull, cityIsSelectable, areaIsSelectable, showArea, showAddress) => {
  let rules = {
    province: [
      {required: true, message: "请选择省", trigger: ['blur', 'change']}
    ],
    city: [
      {
        required: cityIsSelectable,
        message: "请选择市",
        trigger: ['blur', 'change']
      }
    ],
    area: [
      {
        required: areaNull && showArea && areaIsSelectable,
        message: "请选择区县",
        trigger: ['blur', 'change']
      }
    ],
    address: [
      {
        required: showAddress,
        message: "请输入详细地址",
        trigger: ["blur", 'change']
      },
      {max: 100, message: '长度在100个字符以内', trigger: ['blur', 'change']}
    ],
  }
  return rules;
}

export default createRules()
