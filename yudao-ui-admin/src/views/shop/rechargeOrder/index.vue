<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会员编号" prop="memberId">
        <el-input v-model="queryParams.memberId" placeholder="请输入会员编号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="充值金额" prop="amount">
        <el-input v-model="queryParams.amount" placeholder="请输入充值金额" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="支付方式" prop="payType">
        <el-select v-model="queryParams.payType" placeholder="请选择支付方式" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_RECHARGE_PAY_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="payStatus">
        <el-select v-model="queryParams.payStatus" placeholder="请选择支付状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.PAY_ORDER_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="支付时间" prop="payTime">
        <el-date-picker v-model="queryParams.payTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
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
                   v-hasPermi="['shop:recharge-order:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['shop:recharge-order:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="订单编号" align="center" prop="id" />
      <el-table-column label="会员" align="center" prop="memberId" :formatter="userNicknameFormat"/>
      <el-table-column label="充值金额" align="center" prop="amount" />
      <el-table-column label="充值活动" align="center" prop="rechargeName" />
      <el-table-column label="支付方式" align="center" prop="payType">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_RECHARGE_PAY_TYPE" :value="scope.row.payType" />
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="payStatus">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.PAY_ORDER_STATUS" :value="scope.row.payStatus" />
        </template>
      </el-table-column>
      <el-table-column label="支付时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"-->
<!--                     v-hasPermi="['shop:recharge-order:update']">修改</el-button>-->
<!--          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"-->
<!--                     v-hasPermi="['shop:recharge-order:delete']">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员" prop="memberId">
          <el-select v-model="form.memberId" placeholder="请输入会员编号" clearable style="width: 100%">
            <el-option v-for="item in memberList" :key="parseInt(item.id)" :label="item.nickname + ' ' + item.mobile" :value="parseInt(item.id)" />
          </el-select>
        </el-form-item>
        <el-form-item label="充值金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入充值金额" />
        </el-form-item>
<!--        <el-form-item label="充值活动" prop="rechargeId">-->
<!--          <el-select v-model="form.rechargeId" placeholder="请输入充值活动编号" clearable style="width: 100%">-->
<!--            <el-option v-for="item in rechargeList" :key="parseInt(item.id)" :label="item.name" :value="parseInt(item.id)" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="支付方式" prop="payType">
          <el-select v-model="form.payType" placeholder="请选择支付方式">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_RECHARGE_PAY_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="payStatus">
          <el-radio-group v-model="form.payStatus">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.PAY_ORDER_STATUS)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
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
import { createRechargeOrder, updateRechargeOrder, deleteRechargeOrder, getRechargeOrder, getRechargeOrderPage, exportRechargeOrderExcel } from "@/api/shop/rechargeOrder";
import { getAllRecharge } from '@/api/shop/recharge'
import { getMemberByUser } from '@/api/shop/member'

export default {
  name: "RechargeOrder",
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
      // 充值订单列表
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
        amount: null,
        rechargeId: null,
        rechargeName: null,
        payType: null,
        payStatus: null,
        payTime: [],
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        memberId: [{ required: true, message: "会员编号不能为空", trigger: "blur" }],
        amount: [{ required: true, message: "充值金额不能为空", trigger: "blur" }],
        payType: [{ required: true, message: "支付方式不能为空", trigger: "change" }],
        payStatus: [{ required: true, message: "支付状态不能为空", trigger: "blur" }],
      },
      rechargeList: [],
      memberList: [],
    };
  },
  created() {
    // this.getRechargeList();
    this.getMemberList()
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getRechargeOrderPage(this.queryParams).then(response => {
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
        amount: undefined,
        rechargeId: undefined,
        rechargeName: undefined,
        payType: 'wx_pay',
        payStatus: 10,
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
      this.title = "添加充值订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getRechargeOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充值订单";
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
          updateRechargeOrder(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createRechargeOrder(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除充值订单编号为"' + id + '"的数据项?').then(function() {
          return deleteRechargeOrder(id);
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
      this.$modal.confirm('是否确认导出所有充值订单数据项?').then(() => {
          this.exportLoading = true;
          return exportRechargeOrderExcel(params);
        }).then(response => {
          this.$download.excel(response, '充值订单.xls');
          this.exportLoading = false;
        }).catch(() => {});
    },
    getRechargeList(){
      getAllRecharge().then(response => {
        this.rechargeList = response.data
      })
    },
    getMemberList(){
      getMemberByUser().then(response => {
        this.memberList = response.data
      })
    },
    // 用户昵称展示
    userNicknameFormat(row, column) {
      for (const member of this.memberList) {
        if (row.memberId === member.id) {
          return member.nickname || member.mobile;
        }
      }
      return '未知【' + row.salesman + '】';
    },
  }
};
</script>
