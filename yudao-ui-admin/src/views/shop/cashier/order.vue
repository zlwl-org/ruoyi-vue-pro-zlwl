<template>
  <div class="app-container">

    <el-row style="display: flex; height: 100%">
      <el-col style="width: 400px;border: 1px solid #ebebeb">
        <el-row style="border: 1px solid #ebebeb;padding: 10px">
          <el-descriptions class="margin-top" title="会员信息" :column="1" size="mini" border
          :content-style="{'text-align': 'center'}" :label-style="{'text-align': 'center', 'font-weight': 'bold'}">
            <template slot="extra">
<!--              <el-button type="danger" size="mini" @click="resetMember" v-if="!member">新建</el-button>-->
              <el-button type="danger" size="mini" @click="resetMember" v-if="member">散客</el-button>
              <el-button type="success" size="mini" @click="openMemberDialog">查询</el-button>
            </template>
            <el-descriptions-item v-if="!member" label="昵称">散客</el-descriptions-item>
            <el-descriptions-item v-if="member" label="昵称">{{  member.name || member.nickname || '无昵称' }}</el-descriptions-item>
            <el-descriptions-item v-if="member" label="储值余额">{{ member.balance + '  元' }}</el-descriptions-item>
            <el-descriptions-item v-if="member" label="手机">{{ member.mobile }}</el-descriptions-item>
          </el-descriptions>
          <el-row style="margin-top: 10px">
            <el-button type="primary" size="mini" @click="openRechargeDialog" v-if="member">充值</el-button>
          </el-row>
        </el-row>
        <!--   购物车     -->
        <el-row style="border: 1px solid #ebebeb;padding: 10px; height: 500px">
          <div class="block" style="margin-bottom: 10px">
            <el-link type="primary" :underline="false">{{ '结算清单( ' + cart.length + ' )' }}</el-link>
            <el-link type="primary" style="float: right;" @click="clearCart">清空</el-link>
          </div>
          <el-row v-for="item in cart" :key="item.id">
            <el-col style="flex-grow: inherit;">
              <div style="margin-bottom: 3px">
                <el-link :underline="false">{{ item.goodName }}</el-link>
                <el-link type="primary" style="float: right;">删除</el-link>
              </div>
              <div>
                <el-link :underline="false">{{ '¥  ' + item.goodPrice }}</el-link>
                <div style="float: right;">
                  <el-button icon="el-icon-minus" size="mini" style="margin-right: 5px"
                             :disabled.sync="item.amount === 1" @click="minusAmount(item)"
                  ></el-button>
                  <span style="margin-right: 15px;margin-left: 15px; width: 100px; text-align: center">{{
                      item.amount
                    }}</span>
                  <el-button icon="el-icon-plus" size="mini" style="margin-right: 5px" @click="plusAmount(item)"
                  ></el-button>
                </div>
              </div>
              <el-divider/>
            </el-col>
          </el-row>
        </el-row>
        <!--   购物车     -->
        <el-row style="border: 1px solid #ebebeb;padding: 10px">
          <div style="float: right;">
            <el-button type="primary" @click="submitOrder" :disabled="cart.length===0">下单<span>{{ handleTotal() }}</span></el-button>
          </div>
        </el-row>
      </el-col>

      <el-col style="flex-grow: inherit;border: 1px solid #ebebeb; margin-left: 5px">
        <!-- 搜索工作栏 -->
        <el-form :model="queryParams" ref="queryForm" :rules="query_rules" size="small" :inline="true"
                 v-show="showSearch"
                 label-width="68px" style="margin-top: 10px"
        >
          <el-form-item label="店铺" prop="branchId">
            <el-select v-model="queryParams.branchId" placeholder="">
              <el-option v-for="item in branches" :key="parseInt(item.id)" :label="item.name"
                         :value="parseInt(item.id)"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入商品名称" clearable
                      @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <!-- 列表 -->
        <el-table v-loading="loading" :data="list">
          <el-table-column label="商品名称" align="center" prop="name"/>
          <el-table-column label="售价(元)" align="center" prop="price"/>
          <el-table-column label="库存" align="center" prop="stock"/>
          <el-table-column label="促销" align="center" prop="promotion.name">
            <template slot-scope="scope">
              <el-tag size="medium">{{ scope.row.promotion.name }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-shopping-cart-2" @click="selectGood(scope.row)"> 选择
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                    @pagination="getList"
        />
      </el-col>
    </el-row>
    <el-row style="border: 1px solid #ebebeb">
      <shop-order ref="orderList" :mode="false" @settle="handleSettle"/>
    </el-row>

    <el-dialog title="会员列表" :visible.sync="member_dialog" width="70%" v-dialogDrag append-to-body>
      <cashier-member @memberSelected="memberSelected"/>
    </el-dialog>

    <el-dialog title="订单结算" :visible.sync="settle_dialog" width="70%" append-to-body @close="cancelSettle">
      <cashier-settle :order-id.sync="orderId" @cancel="cancelSettle"></cashier-settle>
    </el-dialog>

    <el-dialog title="会员充值" :visible.sync="recharge_dialog" width="500px" v-dialogDrag append-to-body>
      <el-form ref="recharge_form" :model="recharge_form" :rules="recharge_rules" label-width="80px">
        <el-form-item label="会员编号" prop="memberId">
          <el-input v-model="recharge_form.memberId" placeholder="请输入会员编号" readonly/>
        </el-form-item>
        <el-form-item label="会员" prop="member">
          <el-input v-model="recharge_form.member" placeholder="请输入会员" readonly/>
        </el-form-item>
        <el-form-item label="充值金额" prop="amount">
          <el-input v-model="recharge_form.amount" placeholder="请输入充值金额"/>
        </el-form-item>
        <el-form-item label="支付方式" prop="payType">
          <el-select v-model="recharge_form.payType" placeholder="请选择支付方式">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_RECHARGE_PAY_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRecharge">确 定</el-button>
        <el-button @click="recharge_dialog=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style>

</style>
<script>
import CashierMember from '@/views/shop/cashier/member'
import { listSimpleBranches } from '@/api/shop/branch'
import { getBranchGoodsPage } from '@/api/shop/branchGoods'
import { createOrder } from '@/api/shop/order'
import CashierSettle from '@/views/shop/cashier/settle'
import ShopOrder from '@/views/shop/order'
import CashierMemberInfo from '@/views/shop/cashier/memberInfo'
import { createRechargeOrder } from '@/api/shop/rechargeOrder'
import { getMember } from '@/api/shop/member'

export default {
  name: 'CashierOrder',
  components: {
    CashierMemberInfo,
    ShopOrder,
    CashierSettle,
    CashierMember
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
      this.$refs.orderList.getList();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.$refs['queryForm'].validate(valid => {
        if (!valid) {
          return
        }
        this.queryParams.pageNo = 1
        this.getList()
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    openMemberDialog() {
      this.member_dialog = true
    },
    memberSelected(data) {
      this.member = data
      this.member_dialog = false
    },
    handleTotal(prefix=false) {
      if (this.cart.length > 0) {
        let sum = 0
        this.cart.map(item => sum = sum + item.amount * item.goodPrice)
        return prefix? sum : ' ¥  ' + sum
      } else {
        return null
      }
    },
    clearCart() {
      this.cart = []
    },
    openRechargeDialog() {
      this.resetRechargeForm()
      this.recharge_form.memberId = this.member.id;
      this.recharge_form.member = this.member.name || this.member.nickname || '无昵称';
      this.recharge_dialog = true
    },
    selectGood(row) {
      let num = null
      if (this.cart.length !== 0) {
        for (let index = 0; index < this.cart.length; index++) {
          const item = this.cart[index]
          if (item.goodId === row.id) {
            num = index
            break
          }
        }
      }
      if (num !== null) {
        this.cart[num].amount = this.cart[num].amount + 1
        // this.$set(this.form_list[num], 'amount', parseInt(this.form_list[num].amount) + 1)
      } else {
        let good = {
          goodId: row.id,
          goodName: row.name,
          amount: 1,
          goodPrice: row.price,
          promotionId: row.promotion ? row.promotion.id : null,
        }
        this.cart.push(good)
      }
    },
    minusAmount(item) {
      item.amount = item.amount - 1
    },
    plusAmount(item) {
      item.amount = item.amount + 1
    },
    openSettlement(){
      this.settle_dialog = true
    },
    cancelSettle(){
      this.settle_dialog = false;
      this.reset();
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
      this.$refs["recharge_form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 添加的提交
        createRechargeOrder(this.recharge_form).then(response => {
          this.$modal.msgSuccess("充值成功");
          this.recharge_dialog = false
          getMember(this.member.id).then( response => {
            this.member = response.data
          })
        });
      });
    },
    resetMember(){
      this.member = null
    },
    resetRechargeForm() {
      this.recharge_form = {
        id: undefined,
        memberId: undefined,
        amount: undefined,
        rechargeId: undefined,
        rechargeName: undefined,
        payType: 'wx_pay',
        payStatus: 10,
      };
      this.resetForm("recharge_form");
    },

  }
}
</script>
