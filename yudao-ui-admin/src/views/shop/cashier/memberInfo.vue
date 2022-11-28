<template>
  <div class="app-container">

    <el-row style="display: flex; height: 100%">
      <el-col style="width: 450px;border: 1px solid #ebebeb">
        <el-row style="border: 1px solid #ebebeb;padding: 10px">
          <!--    会员信息    -->
          <el-descriptions class="margin-top" title="会员信息" :column="1" size="mini" border>
            <template slot="extra">
              <el-button type="warning" size="small" @click="resetMember" v-if="member">散客</el-button>
              <el-button type="warning" size="small" @click="openRechargeDialog" v-if="member">充值</el-button>
              <el-button type="success" size="small" @click="openMemberDialog">查询</el-button>
            </template>
            <el-descriptions-item v-if="!member" label="昵称">散客</el-descriptions-item>
            <el-descriptions-item v-if="member" label="昵称">{{  member.name || member.nickname || member.mobile || member.id }}</el-descriptions-item>
            <el-descriptions-item v-if="member" label="储值余额">{{ member.balance + '  元' }}</el-descriptions-item>
            <el-descriptions-item v-if="member" label="手机">{{ member.mobile }}</el-descriptions-item>
          </el-descriptions>

        </el-row>
      </el-col>
    </el-row>
    <el-dialog title="会员列表" :visible.sync="member_dialog" width="70%" v-dialogDrag append-to-body>
      <cashier-member @memberSelected="memberSelected"/>
    </el-dialog>
  </div>
</template>
<style>

</style>
<script>

import { listSimpleBranches } from '@/api/shop/branch'
import { getBranchGoodsPage } from '@/api/shop/branchGoods'
import { createBranchStock, updateBranchStock } from '@/api/shop/branchStock'
import { createOrder } from '@/api/shop/order'
import CashierSettle from '@/views/shop/cashier/settle'
import ShopOrder from '@/views/shop/order'

export default {
  name: 'CashierMemberInfo',
  components: {
  },
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
      // 会员账户流水列表
      list: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        branchId: null,
        createTime: []
      },
      // 表单参数
      form: {},
      recharge_form: {},
      // 表单校验
      rules: {
        action: [{ required: true, message: '发生方式不能为空', trigger: 'change' }],
        balance: [{ required: true, message: '充值余额变动不能为空', trigger: 'blur' }],
        gift: [{ required: true, message: '赠送余额变动不能为空', trigger: 'blur' }],
        point: [{ required: true, message: '积分变动不能为空', trigger: 'blur' }],
        growth: [{ required: true, message: '成长值变动不能为空', trigger: 'blur' }]
      },
      recharge_rules: {
        rechargeAmount: [{ required: true, message: '充值金额不能为空', trigger: 'blur' }],
        payType: [{ required: true, message: '支付方式不能为空', trigger: 'change' }],
        status: [{ required: true, message: '支付状态不能为空', trigger: 'blur' }]
      },
      query_rules: {
        branchId: [{ required: true, message: '店铺不能为空', trigger: 'blur' }]
      },
      member: null,
      member_dialog: false,
      settle_dialog: false,
      recharge_dialog: false,
      cart: [],
      branches: [],
      orderId: null,
    }
  },
  created() {
    // this.getList()
    listSimpleBranches().then(response => {
      this.branches = response.data
      // this.getList();
    })
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true
      // 执行查询
      getBranchGoodsPage(this.queryParams).then(response => {
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
      this.cart = [];
      this.member = null;
      this.getList();
    },
    /** 搜索按钮操作 */

    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },


    resetMember(){
      this.member = null
    }
    ,
    openRechargeDialog() {
      this.recharge_dialog = true
    },
    recharge() {

    },

    openSettlement(){
      this.settle_dialog = true
    },
    cancelSettle(){
      this.settle_dialog = false;
      this.reset();
    },
    submitSettle(){

    },
    submitOrder(){
      let data = {
        memberId: this.member ? this.member.id : null,
        orderType: 'cashier',
        cashier: '',
        price: this.handleTotal(true),
        branchId: this.queryParams.branchId,
        items: this.cart
      }
      createOrder(data).then(response => {
        this.$modal.msgSuccess("下单成功");
        this.orderId = response.data;
        // 进入订单结算
        this.settle_dialog = true;
      });
    },
    handleSettle(orderId){
      this.orderId = orderId;
      this.settle_dialog = true;
    },
    submitRecharge(){

    },
    openMemberDialog() {
      this.member_dialog = true
    },

    memberSelected(data) {
      this.member = data
      this.member_dialog = false
    },
  }
}
</script>
