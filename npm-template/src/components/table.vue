<template v-on="getSelectedRow">
  <div>
    <el-row>
      <el-col :span="24" class="table-wrapper">
        <el-table height="596px;"
                  ref="singleTable"
                  @current-change="handleCurrentChange"
                  :data="pageData.dataList"
                  style="width: 100%; min-height: 426px;max-height:426px;"
                  :highlight-current-row="true" border
                  size="mini"
                  @sort-change="sortList"
                  :default-sort="{prop: 'createTime', order: 'descending'}"
                  @row-click="select"
                  @selection-change="select">

          <el-table-column v-if="isRadio" label="选择" width="50">
            <template slot-scope="scope">
              <el-radio class="radio" v-model="radio" :label="scope.row.id"
                        @change.native="updateSelectItem(scope.row)">&nbsp;
              </el-radio>
            </template>
          </el-table-column>
          <template v-for="item in colsDefProp">
            <el-table-column
              v-bind:prop="item.prop"
              v-bind:label="item.label"
              v-bind:width="item.width? item.width:100"
              v-bind:formatter="item.formatter ? item.formatter:(row, column, cellValue, index)=> { return cellValue; }"
            >
            </el-table-column>
          </template>
        </el-table>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24" class="top-15">
        <el-pagination
          @size-change="sizeChange"
          @current-change="currentChange"
          @prev-click="prevClick"
          @next-click="nextClick"
          :current-page.sync="pageData.pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageData.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total.sync="pageData.total">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        radio: null,
        selections: null
      }
    },
    props: {
      isRadio: {
        type: Boolean,
        required: false,
        default: true
      },
      pageData: {
        type: Object,
        required: true,
        default: {
          dataList: [],
          pageIndex: 1,
          pageSize: 10,
          total: 0
        }
      },
      colsDefProp: {
        type: Array,
        required: true,
        default: []
      }
    },
    methods: {
      updateSelectItem(item) {
        this.selections = item;
      },
      handleCurrentChange(val) {

      },
      sortList(sortObj) {
        this.$emit("sortList", sortObj);
      },
      sizeChange(size) {
        this.$emit("sizeChange", size);
      },
      currentChange(pageInd) {
        this.$emit("currentChange", pageInd);
      },
      prevClick(pageInd) {

      },
      nextClick(pageInd) {

      },
      select(rows) {
        this.selections = new Object(rows);
        this.radio = rows.id;
        this.$emit("rowSelect", this.selections);
      }
    },
    watch: {
      pageData: {
        immediate: true,
        handler(val) {
          this.selections = null;
          this.radio = null;
        }
      }
    },
    mounted() {
      console.log("mounted");
    },
    created() {
    },
    computed: {
      getSelectedRow: function () {
        this.$emit('getSelectedRow', this.selections);
      }

    }
  }


</script>

<style scoped>

</style>
