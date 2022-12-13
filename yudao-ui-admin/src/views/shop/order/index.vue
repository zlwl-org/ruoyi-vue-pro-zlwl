<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会员" prop="memberId">
        <el-input v-model="queryParams.memberId" placeholder="请输入会员" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="订单类型" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="请选择订单类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
<!--      <el-form-item label="订单交易号" prop="orderNo">-->
<!--        <el-input v-model="queryParams.orderNo" placeholder="请输入订单交易号" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="付款方式" prop="payType">
        <el-select v-model="queryParams.payType" placeholder="请选择付款方式" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_PAY_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="payStatus">
        <el-select v-model="queryParams.payStatus" placeholder="请选择支付状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_PAY_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
<!--      <el-form-item label="付款时间" prop="payTime">-->
<!--        <el-date-picker v-model="queryParams.payTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"-->
<!--                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />-->
<!--      </el-form-item>-->
      <el-form-item label="收银员" prop="cashier">
        <el-input v-model="queryParams.cashier" placeholder="请输入收银员" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="店铺" prop="branchId">
        <el-input v-model="queryParams.branchId" placeholder="请输入店铺编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row  :gutter="10" class="mb8">
      <el-col v-if="!mode" :span="1.5">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-col>
      <el-col  v-if="mode" :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['shop:order:create']">新增</el-button>
      </el-col>
      <el-col  v-if="mode" :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['shop:order:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="订单编号" align="center" prop="id" />
      <el-table-column label="会员" align="center" prop="member"/>
      <el-table-column label="订单类型" align="center" prop="orderType">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_ORDER_TYPE" :value="scope.row.orderType" />
        </template>
      </el-table-column>
      <el-table-column label="订单金额" align="center" prop="orderPrice" />
      <el-table-column label="实际应收" align="center" prop="price" />
      <!--      <el-table-column label="订单交易号" align="center" prop="orderNo" />-->
      <el-table-column label="订单状态" align="center" prop="orderStatus">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_ORDER_STATUS" :value="scope.row.orderStatus" />
        </template>
      </el-table-column>
      <el-table-column label="付款方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_PAY_TYPE" :value="scope.row.payType" />
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="payStatus">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_ORDER_PAY_STATUS" :value="scope.row.payStatus" />
        </template>
      </el-table-column>
      <el-table-column label="付款时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收银员" align="center" prop="cashier" />
<!--      <el-table-column label="余额实付金额" align="center" prop="balancePay" />-->
<!--      <el-table-column label="现金实付金额" align="center" prop="cashPay" />-->
      <el-table-column label="店铺" align="center" prop="branchId" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button  v-if="!mode" size="mini" type="text" icon="el-icon-edit" @click="handleSettle(scope.row)"
                     v-hasPermi="['shop:order:update']">结算</el-button>
          <el-button  v-if="mode" size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['shop:order:update']">修改</el-button>
          <el-button  v-if="mode" size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['shop:order:delete']">删除</el-button>
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
        <el-form-item label="订单类型" prop="orderType">
          <el-select v-model="form.orderType" placeholder="请选择订单类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="订单交易号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单交易号" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus">
          <el-radio-group v-model="form.orderStatus">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_STATUS)"
                      :key="dict.value" :label="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="付款方式" prop="payType">
          <el-select v-model="form.payType" placeholder="请选择付款方式">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_PAY_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="payStatus">
          <el-radio-group v-model="form.payStatus">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_ORDER_PAY_STATUS)"
                      :key="dict.value" :label="dict.value">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="付款时间" prop="payTime">
          <el-date-picker clearable v-model="form.payTime" type="date" value-format="timestamp" placeholder="选择付款时间" />
        </el-form-item>
        <el-form-item label="收银员" prop="cashier">
          <el-input v-model="form.cashier" placeholder="请输入收银员" />
        </el-form-item>
        <el-form-item label="商品总价" prop="price">
          <el-input v-model="form.price" placeholder="请输入商品总价" />
        </el-form-item>
        <el-form-item label="店铺优惠" prop="branchDiscount">
          <el-input v-model="form.branchDiscount" placeholder="请输入店铺优惠" />
        </el-form-item>
        <el-form-item label="订单减免" prop="orderDiscount">
          <el-input v-model="form.orderDiscount" placeholder="请输入订单减免" />
        </el-form-item>
        <el-form-item label="优惠券" prop="coupon">
          <el-input v-model="form.coupon" placeholder="请输入优惠券" />
        </el-form-item>
        <el-form-item label="积分抵扣" prop="point">
          <el-input v-model="form.point" placeholder="请输入积分抵扣" />
        </el-form-item>
        <el-form-item label="余额实付金额" prop="balancePay">
          <el-input v-model="form.balancePay" placeholder="请输入余额实付金额" />
        </el-form-item>
        <el-form-item label="现金实付金额" prop="cashPay">
          <el-input v-model="form.cashPay" placeholder="请输入现金实付金额" />
        </el-form-item>
        <el-form-item label="店铺编号" prop="branchId">
          <el-input v-model="form.branchId" placeholder="请输入店铺编号" />
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
import { createOrder, updateOrder, deleteOrder, getOrder, getOrderPage, exportOrderExcel } from "@/api/shop/order";
import { getMemberByUser } from '@/api/shop/member'

export default {
  name: "ShopOrder",
  props: {
    mode: {
      type: Boolean,
      default: true
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
      // 门店订单列表
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
        orderType: null,
        orderNo: null,
        orderStatus: null,
        payType: null,
        payStatus: null,
        payTime: [],
        cashier: null,
        branchId: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        branchId: [{ required: true, message: "店铺编号不能为空", trigger: "blur" }],
      },
      memberList: [],
    };
  },
  created() {
    getMemberByUser().then(response => {
      this.memberList = response.data
      this.getList();
    })

    if (!this.mode){
      this.showSearch = false
    }
  },
  methods: {
    /** 查询列表 */
    getList() {
      console.log(this.memberList)
      this.loading = true;
      // 执行查询
      getOrderPage(this.queryParams).then(response => {
        let data = response.data.list;
        data.forEach(item => {
          if (!item.memberId) {
            item.member = '散客';
          } else {
            for (let member of this.memberList){
              if (member.id === item.memberId) {
                item.member = member.name || member.nickname || member.mobile || member.id;
                break
              }
            }
          }
        });
        this.list = data;
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
        orderType: undefined,
        orderNo: undefined,
        orderStatus: undefined,
        payType: undefined,
        payStatus: undefined,
        payTime: undefined,
        cashier: undefined,
        price: undefined,
        branchDiscount: undefined,
        orderDiscount: undefined,
        coupon: undefined,
        point: undefined,
        balancePay: undefined,
        cashPay: undefined,
        branchId: undefined,
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
      this.title = "添加门店订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店订单";
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
          updateOrder(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createOrder(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除门店订单编号为"' + id + '"的数据项?').then(function() {
          return deleteOrder(id);
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
      this.$modal.confirm('是否确认导出所有门店订单数据项?').then(() => {
          this.exportLoading = true;
          return exportOrderExcel(params);
        }).then(response => {
          this.$download.excel(response, '门店订单.xls');
          this.exportLoading = false;
        }).catch(() => {});
    },
    handleSettle(row){
      this.$emit("settle", row.id);
    },
    getMemberList(){
      getMemberByUser().then(response => {
        this.memberList = response.data
      })
    },
  }
};
</script>
