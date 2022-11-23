<template>
  <div class="app-container">
    <el-descriptions :column="2" border style="margin-bottom: 18px">
      <el-descriptions-item label="表单编号" >{{ item.id }}</el-descriptions-item>
      <el-descriptions-item label="店铺" >{{ item.branchName }}</el-descriptions-item>
      <el-descriptions-item label="创建者" >{{ item.creator }}</el-descriptions-item>
      <el-descriptions-item label="创建时间" >{{ parseTime(item.createTime) }}</el-descriptions-item>
    </el-descriptions>

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="出入库" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择出入库类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_STOCK_TYPE)"
                     :key="dict.value" :label="dict.label" :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品编号" prop="productId">
        <el-input v-model="queryParams.productId" placeholder="请输入商品编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="出入库" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_STOCK_TYPE" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="商品" align="center" prop="productName" />
      <el-table-column label="数量" align="center" prop="amount" />

    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>


  </div>
</template>

<script>
import { createBranchStockItem, updateBranchStockItem, deleteBranchStockItem, getBranchStockItem, getBranchStockItemPage, exportBranchStockItemExcel } from "@/api/shop/branchStockItem";
import item from '@/layout/components/Sidebar/Item'

export default {
  name: "DetailBranchStock",
  props: {
    item: Object,
    default () {
      return {}
    },
  },
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 门店出入库明细列表
      list: [],
      // 弹出层标题
      title: "",
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
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stockId: [{ required: true, message: "库存编号不能为空", trigger: "blur" }],
        type: [{ required: true, message: "出入库类型不能为空", trigger: "change" }],
        branchId: [{ required: true, message: "店铺编号不能为空", trigger: "blur" }],
        productId: [{ required: true, message: "商品编号不能为空", trigger: "blur" }],
        amount: [{ required: true, message: "数量不能为空", trigger: "blur" }],
      },
      data: {},
    };
  },
  created() {
    console.log(this.item)
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      this.queryParams.stockId = this.item.id;
      getBranchStockItemPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        stockId: undefined,
        type: undefined,
        branchId: undefined,
        productId: undefined,
        amount: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有门店出入库明细数据项?').then(() => {
        this.exportLoading = true;
        return exportBranchStockItemExcel(params);
      }).then(response => {
        this.$download.excel(response, '门店出入库明细.xls');
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
