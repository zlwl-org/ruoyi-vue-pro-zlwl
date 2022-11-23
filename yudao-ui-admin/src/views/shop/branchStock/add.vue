<template>
  <div class="app-container">

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
        >新增
        </el-button>
      </el-col>
    </el-row>

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
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-edit" v-if="!scope.row.edit" @click="handleEdit(scope.row)">
                修改
              </el-button>
              <div v-else>
                <el-button size="mini" type="text" icon="el-icon-edit" @click="handleConform(scope.row)">
                  确认
                </el-button>
              </div>
              <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.$index)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="8">
        <span>商品列表</span>
        <el-button type="text" size="mini" @click="() => getProductList()">
          刷新
        </el-button>
        <el-tree :data="product_list" node-key="id" default-expand-all :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ data.name }}</span>
        <span>
          <el-button :disabled="data.type === 'out'" type="text" size="mini" @click="() => stock_in(data)">
            入库
          </el-button>
          <el-button :disabled="data.type === 'in'" type="text" size="mini" @click="() => stock_out(data)">
            出库
          </el-button>
        </span>
      </span>
        </el-tree>
      </el-col>
    </el-row>
    <el-row :gutter="10" class="mb8" style="margin-top: 15px">
      <el-col :span="1.5">
        <el-button type="primary" plain  size="mini" @click="submitForm">
          提交表单
        </el-button>
      </el-col>
    </el-row>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="出入库类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择出入库类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_STOCK_TYPE)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入数量"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
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
  name: 'AddBranchStock',
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
    /** 新增按钮操作 */
    handleAdd() {
      this.list.forEach((item, index) => {
        console.log(item)
      })
    },
    /** 修改按钮操作 */
    handleEdit(row) {
      row.edit = true;
    },
    handleConform(row) {
      row.edit = false;
      this.$notify({
        title: "Success",
        message: "编辑成功",
        type: "success",
        duration: 2000,
      });
    },
    /** 提交按钮 */
    submitForm() {
      let data = {
        branchId: 1,
        list: this.list
      }
      createBranchStock(data).then(response => {
        this.$modal.msgSuccess("新增成功");
        this.open = false;
        this.getList();
      });
    },
    /** 删除按钮操作 */
    handleDelete(index) {
      this.list.splice(index, 1)
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
    stock_in(data) {
      //改变tree
      // this.product_list.forEach( (item,index) => {
      //   if (item.productId === data.id) {
      //     item.status = 'in'
      //     // this.$set(this.product_list[index], 'type', 'in')
      //   }
      // })

      //改变table
      let num = null
      if (this.list.length !== 0) {
        for (let index = 0; index < this.list.length; index++){
          const item = this.list[index]
          if (item.productId === data.id) {
            num = index
            break
          }
        }
      }
      if (num !== null) {
        if (this.list[num].type !== 'in'){
          this.$notify({
            title: "错误",
            message: "商品已添加出库",
            type: "error",
            duration: 3000,
          });
          return
        }
        this.$set(this.list[num], 'amount', parseInt(this.list[num].amount) + 1)
      } else {
        let good = {
          productId: data.id,
          name: data.name,
          amount: 1,
          type: 'in',
          edit: false
        }
        this.list.push(good)
      }
    },
    stock_out(data) {
      //改变table
      let num = null
      if (this.list.length !== 0) {
        for (let index = 0; index < this.list.length; index++){
          const item = this.list[index]
          if (item.productId === data.id) {
            num = index
            break
          }
        }
      }
      if (num !== null) {
        if (this.list[num].type !== 'out'){
          this.$notify({
            title: "错误",
            message: "商品已添加入库",
            type: "error",
            duration: 3000,
          });
          return
        }
        this.$set(this.list[num], 'amount', parseInt(this.list[num].amount) + 1)
      } else {
        let good = {
          productId: data.id,
          name: data.name,
          amount: 1,
          type: 'out',
          edit: false
        }
        this.list.push(good)
      }
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
    //勾选时获得勾选数据
    selsChange(sels) {
      this.sels = sels
    },

    blurEvent(row) {
      this.$message({
        message: '修改成功',
        type: 'success',
        duration: 1000
      })
    }
  }
}
</script>
