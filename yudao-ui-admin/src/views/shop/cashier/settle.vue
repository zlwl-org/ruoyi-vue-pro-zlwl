<template>
  <div class="app-container">
    <el-descriptions title="订单信息" :column="2"  border :content-style="contentStyle" :label-style="labelStyle">
      <el-descriptions-item label="订单编号">{{ order.id }}</el-descriptions-item>
      <el-descriptions-item label="订单金额">{{ order.price }}</el-descriptions-item>

    </el-descriptions>
    <el-divider/>
    <el-descriptions title="会员信息" :column="2"  border :content-style="contentStyle" :label-style="labelStyle">
      <el-descriptions-item v-if="!order.memberId" label="昵称">散客</el-descriptions-item>
      <el-descriptions-item v-if="order.member" label="昵称">{{ order.member.nickname || '无' }}</el-descriptions-item>
      <el-descriptions-item v-if="order.member">
        <template slot="label">
          <a style="color: #20a5f9" @click="open_recharge_dialog">充值</a>余额
        </template>
        {{ order.member.balance }}
      </el-descriptions-item>
      <el-descriptions-item v-if="order.member" label="手机">{{ order.member.mobile }}</el-descriptions-item>
      <el-descriptions-item v-if="order.member" label="赠送余额">{{ order.member.gift }}</el-descriptions-item>
    </el-descriptions>
    <el-divider/>
    <h3 style="font-size: 16px;font-weight: bold;color: #303133">支付方式</h3>
    <span>余额支付</span>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitSettle">确 定</el-button>
      <el-button @click="settle_dialog=false">取 消</el-button>
    </div>

  </div>
</template>
<style>

</style>
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
import { createOrder, getOrder } from '@/api/shop/order'

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
        getOrder(val).then(response => {
          this.order = response.data
        })
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
        action: [{ required: true, message: '发生方式不能为空', trigger: 'change' }],
        balance: [{ required: true, message: '充值余额变动不能为空', trigger: 'blur' }],
        gift: [{ required: true, message: '赠送余额变动不能为空', trigger: 'blur' }],
        point: [{ required: true, message: '积分变动不能为空', trigger: 'blur' }],
        growth: [{ required: true, message: '成长值变动不能为空', trigger: 'blur' }]
      },

      member: null,
      recharge_dialog: false,
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
      },

    }
  },
  created() {

  },
  mounted() {
    // console.log('....' + this.orderId)
    // const id = this.orderId;
    // getOrder(id).then(response => {
    //   this.order = response.data;
    // });
  },
  methods: {
    /** 取消按钮 */
    cancel() {
      this.open = false
      this.reset()
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
    }

  }
}
</script>
