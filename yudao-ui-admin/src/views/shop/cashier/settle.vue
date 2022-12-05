<template>
  <div class="app-container">
    <el-button type="danger" v-if="order.orderStatus !== 'canceled'" style="float:right;" @click="cancelOrder">取消订单</el-button>
    <el-descriptions title="订单信息" :column="2" border :content-style="contentStyle" :label-style="labelStyle">
      <el-descriptions-item label="订单编号">{{ order.id }}</el-descriptions-item>
      <el-descriptions-item label="创建时间">{{ parseTime(order.createTime) }}</el-descriptions-item>
      <el-descriptions-item label="订单金额">{{ order.price + '  元' }}</el-descriptions-item>
      <el-descriptions-item label="支付状态">{{ this.getDictDataLabel(DICT_TYPE.SHOP_ORDER_PAY_STATUS, order.payStatus) }}</el-descriptions-item>
      <el-descriptions-item label="已付金额">{{ (order.balancePay + order.cashPay) + '  元' }}</el-descriptions-item>
      <el-descriptions-item label="订单状态">{{ this.getDictDataLabel(DICT_TYPE.SHOP_ORDER_STATUS, order.orderStatus) }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <h3 v-if="!order.memberId" style="font-size: 16px;font-weight: bold;color: #303133">顾客信息 <span style="color: red"> 散客</span></h3>

    <el-descriptions v-if="order.member" title="会员信息" :column="2" border :content-style="contentStyle" :label-style="labelStyle">
      <el-descriptions-item label="昵称">{{ order.member.nickname || '无' }}</el-descriptions-item>
      <el-descriptions-item label="储值余额">{{ order.member.balance + '  元' }}</el-descriptions-item>
      <el-descriptions-item label="手机">{{ order.member.mobile }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <!-- 列表 -->
    <h3 v-if="!order.memberId" style="font-size: 16px;font-weight: bold;color: #303133">订单明细</h3>

    <el-table :data="order.items">
      <el-table-column label="商品编号" align="center" prop="goodId" />
      <el-table-column label="商品名称" align="center" prop="goodName" />
      <el-table-column label="售价（元）" align="center" prop="goodPrice" />
      <el-table-column label="数量" align="center" prop="amount" />
    </el-table>

    <!-- 分页组件 -->
<!--    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"-->
<!--                @pagination="getList"/>-->

    <h2 v-if="order.orderStatus === 'done' || order.orderStatus === 'canceled' " style="font-weight: bold;color: #303133; text-align: center">订单已完成</h2>


<!--    <el-row  :gutter="12">-->
<!--      <el-col :span="4" v-for="(dict, index) in this.getDictDatas(DICT_TYPE.SHOP_PAY_TYPE)">-->
<!--        <div class="box">-->
<!--          <el-card shadow="always" :class="{selected:selectedPayType===dict.value}" :key="dict.value" @click.native="selectPayType(dict.value)">-->
<!--            {{ dict.label }}-->
<!--          </el-card>-->
<!--        </div>-->
<!--      </el-col>-->
<!--    </el-row>-->
    <el-row :gutter="12" style="margin-top: 15px" v-if="order.orderStatus !== 'done' & order.orderStatus !== 'canceled'">
      <h3  style="font-size: 16px;font-weight: bold;color: #303133">需支付 <span style="color: red">{{ order.price - order.balancePay - order.cashPay }}</span> 元，请选择支付方式</h3>
      <el-col :span="8">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="付款方式" prop="payType">
            <el-select v-model="form.payType" placeholder="请选择付款方式" clearable size="small">
              <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_PAY_TYPE)"
                         :key="dict.value" :label="dict.label" :value="dict.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="支付金额" prop="amount">
            <el-input v-model="form.amount" placeholder="" />
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer" v-if="order.orderStatus !== 'done' & order.orderStatus !== 'canceled'">
      <el-button type="primary" @click="submitSettle">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>

  </div>
</template>

<script>
import {
  createMemberAccountLog,
  updateMemberAccountLog,
  deleteMemberAccountLog,
  getMemberAccountLog,
  getMemberAccountLogPage,
  exportMemberAccountLogExcel
} from '@/api/shop/memberAccountLog'
import CashierMember from '@/views/shop/cashier/member'
import { listSimpleBranches } from '@/api/shop/branch'
import { getBranchGoodsPage } from '@/api/shop/branchGoods'
import { createBranchStock, updateBranchStock } from '@/api/shop/branchStock'
import { cancelOrder, createOrder, getOrder, payOrder } from '@/api/shop/order'
import { createProduct, updateProduct } from '@/api/shop/product'

export default {
  name: 'CashierSettle',
  props: {
    orderId: {
      type: Number,
      required: true
    }
  },
  // ruanzh：watch 监视器示例代码
  watch: {
    orderId: {
      immediate: true, // 首次创建组件时立即执行
      handler: function setOrder(val, oldVal) {
        this.getOrderData(val)
      }
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
        payType: [{ required: true, message: '支付方式不能为空', trigger: 'change' }],
        amount: [{ required: true, message: '支付金额不能为空', trigger: 'blur' }],
      },
      member: null,
      order: {},
      orderItems: [],
      labelStyle: {
        'color': '#000',
        'text-align': 'center',
        'font-weight': '600',
        'height': '40px',
        'background-color': 'rgba(255, 97, 2, 0.1)',
        'min-width': '110px',
        'word-break': 'keep-all'
      },
      contentStyle: {
        'font-weight': '500',
        'text-align': 'center',  //文本居中
        'min-width': '250px',   //最小宽度
        'word-break': 'break-all'  //过长时自动换行
      }

    }
  },
  created() {

  },
  methods: {
    getOrderData(orderId) {
      getOrder(orderId).then(response => {
        this.order = response.data
      })
    },
    /** 取消按钮 */
    cancel() {
      this.$emit("cancel")
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        action: undefined,
        balance: undefined,
        gift: undefined,
        point: undefined,
        growth: undefined,
        info: undefined
      }
      this.resetForm('form')
    },

    open_recharge_dialog() {
      this.recharge_dialog = true
    },
    recharge() {

    },
    submitSettle() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        this.form.id = this.orderId;
        payOrder(this.form).then(response => {
          this.$modal.msgSuccess("支付成功");
          this.getOrderData(this.orderId);
        });
      });
    },
    submitOrder() {
      let data = {
        memberId: this.member.id,
        orderType: 'cashier',
        cashier: '',
        price: this.handleTotal(),
        branchId: this.queryParams.branchId,
        items: this.cart
      }
      createOrder(data).then(response => {
        this.$modal.msgSuccess('下单成功')
        this.settle_dialog = false
        // this.getList();
      })
    },
    selectPayType(val){
      this.selectedPayType = val
      this.form.payType = val
    },
    cancelOrder(){
      cancelOrder(this.orderId).then(response => {
        this.$modal.msgSuccess("取消成功");
        this.getOrderData(this.orderId);
      });
    },


  }
}
</script>
<style>
.activeCls {
  position: absolute;
  right: 0;
  bottom: 0;
}

.activeCls::before {
  content: "";
  position: absolute;
  right: 0;
  bottom: 0;
  border: 12px solid #f90;
  border-top-color: transparent;
  border-left-color: transparent;
}

.activeCls::after {
  content: "";
  width: 5px;
  height: 10px;
  position: absolute;
  right: 4px;
  bottom: 5px;
  border: 1px solid #fff;
  border-top-color: transparent;
  border-left-color: transparent;
  transform: rotate(45deg);
}
.box {
  /*width: 100px;*/
  /*height: 100px;*/
  position: relative;
}
.selected {
  color: #4ABE84;
  box-shadow:0 2px 7px 0 rgba(85,110,97,0.35);
  border-radius:7px;
  border:1px solid rgba(74,190,132,1);
}
.selected:before {
  content: '';
  position: absolute;
  right: 0;
  bottom: 0;
  border: 17px solid #4ABE84;
  border-radius:7px;
  border-top-color: transparent;
  border-left-color: transparent;
}
.selected:after {
  content: '';
  width: 5px;
  height: 12px;
  position: absolute;
  right: 6px;
  bottom: 6px;
  border: 2px solid #fff;
  border-top-color: transparent;
  border-left-color: transparent;
  transform: rotate(45deg);
}
</style>
