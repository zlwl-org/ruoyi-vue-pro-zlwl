<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会员编号" prop="memberId">
        <el-input v-model="queryParams.memberId" placeholder="请输入会员编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单编号" prop="orderId">
        <el-input v-model="queryParams.orderId" placeholder="请输入订单编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="商品编号" prop="goodId">
        <el-input v-model="queryParams.goodId" placeholder="请输入商品编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="商品名称" prop="goodName">
        <el-input v-model="queryParams.goodName" placeholder="请输入商品名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="商品售价" prop="goodPrice">
        <el-input v-model="queryParams.goodPrice" placeholder="请输入商品售价" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="数量" prop="amount">
        <el-input v-model="queryParams.amount" placeholder="请输入数量" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_ITEM_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['shop:order-item:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['shop:order-item:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="明细编号" align="center" prop="id" />
      <el-table-column label="会员编号" align="center" prop="memberId" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="商品编号" align="center" prop="goodId" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="商品售价" align="center" prop="goodPrice" />
      <el-table-column label="数量" align="center" prop="amount" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="减免金额" align="center" prop="discount" />
      <el-table-column label="实际金额" align="center" prop="realPrice" />
      <el-table-column label="促销活动编号" align="center" prop="promotionId" />
      <el-table-column label="促销活动名称" align="center" prop="promotionName" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_ORDER_ITEM_TYPE" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['shop:order-item:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['shop:order-item:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员编号" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入会员编号" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="商品编号" prop="goodId">
          <el-input v-model="form.goodId" placeholder="请输入商品编号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodName">
          <el-input v-model="form.goodName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品售价" prop="goodPrice">
          <el-input v-model="form.goodPrice" placeholder="请输入商品售价" />
        </el-form-item>
        <el-form-item label="数量" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="减免金额" prop="discount">
          <el-input v-model="form.discount" placeholder="请输入减免金额" />
        </el-form-item>
        <el-form-item label="实际金额" prop="realPrice">
          <el-input v-model="form.realPrice" placeholder="请输入实际金额" />
        </el-form-item>
        <el-form-item label="促销活动编号" prop="promotionId">
          <el-input v-model="form.promotionId" placeholder="请输入促销活动编号" />
        </el-form-item>
        <el-form-item label="促销活动名称" prop="promotionName">
          <el-input v-model="form.promotionName" placeholder="请输入促销活动名称" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_ITEM_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createOrderItem, updateOrderItem, deleteOrderItem, getOrderItem, getOrderItemPage, exportOrderItemExcel } from "@/api/shop/orderItem";

export default {
  name: "OrderItem",
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
      // 门店订单明细列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        memberId: null,
        orderId: null,
        goodId: null,
        goodName: null,
        goodPrice: null,
        amount: null,
        createTime: [],
        type: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [{ required: true, message: "订单编号不能为空", trigger: "blur" }],
        goodId: [{ required: true, message: "商品编号不能为空", trigger: "blur" }],
        goodPrice: [{ required: true, message: "商品售价不能为空", trigger: "blur" }],
        amount: [{ required: true, message: "数量不能为空", trigger: "blur" }],
        discount: [{ required: true, message: "减免金额不能为空", trigger: "blur" }],
        realPrice: [{ required: true, message: "实际金额不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getOrderItemPage(this.queryParams).then(response => {
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
        memberId: undefined,
        orderId: undefined,
        goodId: undefined,
        goodName: undefined,
        goodPrice: undefined,
        amount: undefined,
        discount: undefined,
        realPrice: undefined,
        promotionId: undefined,
        promotionName: undefined,
        type: undefined,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加门店订单明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrderItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店订单明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateOrderItem(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createOrderItem(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除门店订单明细编号为"' + id + '"的数据项?').then(function() {
          return deleteOrderItem(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有门店订单明细数据项?').then(() => {
          this.exportLoading = true;
          return exportOrderItemExcel(params);
        }).then(response => {
          this.$download.excel(response, '门店订单明细.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
