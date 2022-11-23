<template>
  <div class="app-container">

    <el-row :gutter="10">
      <el-col :span="16">
        <!-- 列表 -->
        <el-table v-loading="loading" :data="list" @selection-change="selsChange">
          <el-table-column label="出入库" align="center" prop="type">
            <template slot-scope="scope">
              <dict-tag :type="DICT_TYPE.SHOP_STOCK_TYPE" :value="scope.row.type"/>
            </template>
          </el-table-column>
          <el-table-column label="商品编号" align="center" prop="productId"/>
          <el-table-column label="商品名称" align="center" prop="name"/>
          <el-table-column label="数量" align="center" prop="amount">
            <template slot-scope="scope">
              <el-input v-if="scope.row.edit" v-model="scope.row.amount"></el-input>
              <span v-else>{{ scope.row.amount }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>


  </div>
</template>
<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
<script>
import {
  createBranchStockItem,
  updateBranchStockItem,
  deleteBranchStockItem,
  getBranchStockItem,
  getBranchStockItemPage,
  exportBranchStockItemExcel
} from '@/api/shop/branchStockItem'
import { getProductPage, getProducts } from '@/api/shop/product'
import { createBranchStock } from '@/api/shop/branchStock'

export default {
  name: 'DetailBranchStock',
  props: {
    info: {
      type: Object,
      default: null
    }
  },
  components: {},
  data() {
    return {
      // 遮罩层
      loading: false,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 门店出入库明细列表
      list: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        stockId: null,
        type: null,
        branchId: null,
        productId: null,
        amount: null,
        createTime: []
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stockId: [{ required: true, message: '库存编号不能为空', trigger: 'blur' }],
        type: [{ required: true, message: '出入库类型不能为空', trigger: 'change' }],
        branchId: [{ required: true, message: '店铺编号不能为空', trigger: 'blur' }],
        productId: [{ required: true, message: '商品编号不能为空', trigger: 'blur' }],
        amount: [{ required: true, message: '数量不能为空', trigger: 'blur' }]
      },
      product_list: []
    }
  },
  created() {
    // this.getList();
    this.getProductList()
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true
      // 执行查询
      getBranchStockItemPage(this.queryParams).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        this.loading = false
      })
    },
    /** 取消按钮 */
    cancel() {
      this.open = false
      this.reset()
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        stockId: undefined,
        type: undefined,
        branchId: undefined,
        productId: undefined,
        amount: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = { ...this.queryParams }
      params.pageNo = undefined
      params.pageSize = undefined
      this.$modal.confirm('是否确认导出所有门店出入库明细数据项?').then(() => {
        this.exportLoading = true
        return exportBranchStockItemExcel(params)
      }).then(response => {
        this.$download.excel(response, '门店出入库明细.xls')
        this.exportLoading = false
      }).catch(() => {
      })
    },
    getProductList() {
      getProducts().then(response => {
        let data = JSON.parse(JSON.stringify(response.data))
        // data.forEach(item => {
        //   item['status'] = ''
        // })
        this.product_list = data
      })
    },
  }
}
</script>
