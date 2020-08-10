<template>
  <el-form ref="addressForm" :model="addressForm" :rules="rules" :disabled="!allowModify" :inline="true"
           :validate-on-rule-change="false" size="mini">
    <el-form-item prop="province" style="width: 140px; margin-right: 0px;">
      <el-select v-model="addressForm.province" @change="changeProvince" clearable filterable
                 placeholder="省">
        <el-option v-for="item in provinceList" :key="String(item.id)" :label="item.name"
                   :value="String(item.id)"/>
      </el-select>
    </el-form-item>
    <el-form-item prop="city" style="width: 140px; margin-right: 0px;">
      <el-select v-model="addressForm.city" @change="changeCity" clearable filterable :disabled="!cityIsSelectable"
                 placeholder="市">
        <el-option v-for="item in cityList" :key="String(item.id)" :label="item.name" :value="String(item.id)"/>
      </el-select>
    </el-form-item>
    <el-form-item prop="area" style="width: 140px; margin-right: 0px;" v-if="showArea">
      <el-select v-model="addressForm.area" @change="changeArea" clearable filterable :disabled="!areaIsSelectable"
                 placeholder="区县">
        <el-option v-for="item in areaList" :key="String(item.id)" :label="item.name"
                   :value="String(item.id)"/>
      </el-select>
    </el-form-item>
    <el-form-item prop="address" v-if="showAddress">
      <el-input v-model.trim="addressForm.address" style="width: 250px" clearable
                placeholder="详细地址" maxlength="100"/>
    </el-form-item>
  </el-form>
</template>

<script>
    import {createRules} from './address_rule.js'

    export default {
        data() {
            return {
                addressForm: {
                    province: '',
                    city: '',
                    area: '',
                    address: '',
                },
            }
        },
        props: {
            allowModify: {
                type: Boolean,
                default: true
            },
            notNull: {
                type: Boolean,
                default: false
            },
            areaNull: {
                type: Boolean,
                default: true
            },
            showArea: {
                type: Boolean,
                default: true
            },
            showAddress: {
                type: Boolean,
                default: true
            },
            areaInfoList: {
                type: Array,
                default: function () {
                    return [];
                }
            },
        },
        computed: {
            // 省：
            provinceList: function () {
                return this.areaInfoList.filter(function (e) {
                    return e.type === 1;
                });
            },
            // 市：
            cityList: function () {
                let province = this.addressForm.province;
                return this.areaInfoList.filter(function (e) {
                    return e.type === 2 && e.parentId == province;
                });
            },
            // 区县：
            areaList: function () {
                let city = this.addressForm.city;
                return this.areaInfoList.filter(function (e) {
                    return e.type === 3 && e.parentId == city;
                });
            },
            cityIsSelectable: function () {
                return !this.addressForm.province || (this.addressForm.province && this.cityList.length > 0);
            },
            areaIsSelectable: function () {
                return (!this.addressForm.city && this.cityIsSelectable) || (this.addressForm.city && this.areaList.length > 0);
            },
            rules: function () {
                return this.allowModify && this.notNull ? createRules(this.areaNull, this.cityIsSelectable, this.areaIsSelectable, this.showArea,
                    this.showAddress) :
                    {};
            },
        },
        watch: {
            cityIsSelectable: function (newVal, oldVal) {
                if (!newVal) {
                    this.$refs.addressForm.fields.forEach(field => {
                        if (field.prop === 'city' || field.prop === 'area') {
                            field.resetField();
                        }
                    });
                }
            },
            areaIsSelectable: function (newVal, oldVal) {
                if (!newVal) {
                    this.$refs.addressForm.fields.forEach(field => {
                        if (field.prop === 'area') {
                            field.resetField();
                        }
                    });
                }
            }
        },
        methods: {
            setData(data) {
                this.addressForm = data;
            },
            setProvinceIfEmpty(province) {
                if (!this.addressForm.province) {
                    this.addressForm.province = province;
                }
            },
            setCityIfEmpty(city) {
                if (!this.addressForm.city) {
                    this.addressForm.city = city;
                }
            },
            setAreaIfEmpty(area) {
                if (!this.addressForm.area) {
                    this.addressForm.area = area;
                }
            },
            setAddressIfEmpty(address) {
                if (!this.addressForm.address) {
                    this.addressForm.address = address;
                }
            },
            getData() {
                let address = Object.assign({}, this.addressForm);
                if (!this.showArea) {
                    delete address['area'];
                }
                if (!this.showAddress) {
                    delete address['address'];
                }
                return address;
            },
            clearValidate() {
                this.$refs.addressForm.clearValidate()
            },
            validate() {
                let ret = false;
                this.$refs.addressForm.clearValidate()
                this.$refs.addressForm.validate(v => {
                    ret = v
                });
                return ret;
            },
            // 选择省份
            changeProvince(code) {
                this.addressForm.city = '';
                this.addressForm.area = '';
                this.$emit('changeCity', code, '');
            },
            // 选择城市
            changeCity(code) {
                this.addressForm.area = '';
                this.$emit('changeCity', this.addressForm.province, code);
            },
            // 选择县区
            changeArea(code) {
                this.$emit('changeArea', this.addressForm.province, this.addressForm.area, code);
            }
        },
        mounted() {
            this.$emit('addressRendered');
        }
    }
</script>
