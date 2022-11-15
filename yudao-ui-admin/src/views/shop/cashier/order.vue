<template>
  <div class="app-container">

    <el-row style="display: flex; height: 100%">
      <el-col style="width: 400px;border: 1px solid #ebebeb">
        <el-row style="border: 1px solid #ebebeb;padding: 10px">
          <!--    会员信息    -->
          <el-descriptions class="margin-top" title="会员信息" :column="2" size="mini" border>
            <template slot="extra">
              <el-button type="primary" size="small" @click="open_member_dialog">查询会员</el-button>
            </template>
            <el-descriptions-item v-if="!member">
              <template slot="label">
                昵称
              </template>
              散客
            </el-descriptions-item>
            <el-descriptions-item v-if="member">
              <template slot="label">
                昵称
              </template>
              {{ member.nickname || '无' }}
            </el-descriptions-item>

            <el-descriptions-item v-if="member">
              <template slot="label">
                充值余额
              </template>
              {{ member.balance }}
            </el-descriptions-item>
            <el-descriptions-item v-if="member">
              <template slot="label">
                手机
              </template>
              {{ member.mobile }}
            </el-descriptions-item>
            <el-descriptions-item v-if="member">
              <template slot="label">
                赠送余额
              </template>
              {{ member.gift }}
            </el-descriptions-item>
          </el-descriptions>
          <el-button type="primary" size="small" @click="open_recharge_dialog">充值</el-button>

        </el-row>
        <!--   购物车     -->
        <el-row style="border: 1px solid #ebebeb;padding: 10px; height: 500px" >
            <div  class="block">
              <span>{{ '结算清单( ' + cart.length + ' )'  }}</span>
              <el-button style="float: right; padding: 3px 0" size="mini" type="text" @click="clearCart">清空</el-button>
            </div>
          <el-divider/>
            <el-card v-for="item in cart" :key="item.id" >
                <div>
                  <span>{{ item.name }}</span>
                  <el-button style="float: right; padding: 3px 0" size="mini" type="text">删除</el-button>
                </div>
                <div>
                  <span>{{ '￥'+item.price }}</span>
                  <el-input-number size="mini" v-model="item.count"></el-input-number>
                </div>
            </el-card>
        </el-row>
        <!--   购物车     -->
        <el-row style="border: 1px solid #ebebeb;padding: 10px">
          <el-button>收款<span>{{handleTotal()}}</span></el-button>
        </el-row>
      </el-col>
<!--      <el-col style="width: 120px;border: 1px solid #ebebeb; padding: 10px">-->
<!--        -->
<!--      </el-col>-->
      <el-col style="flex-grow: inherit;">
        <el-tabs type="border-card" tab-position="left">
          <el-tab-pane label="用户管理">用户管理</el-tab-pane>
          <el-tab-pane label="配置管理">配置管理</el-tab-pane>
          <el-tab-pane label="角色管理">角色管理</el-tab-pane>
          <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>

    <el-dialog title="会员列表" :visible.sync="member_dialog" width="70%" v-dialogDrag append-to-body>
      <cashier-member @memberSelected="memberSelected"/>
    </el-dialog>

    <el-dialog title="会员充值" :visible.sync="recharge_dialog" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="recharge_form" :rules="recharge_rules" label-width="80px">
        <el-form-item label="充值金额" prop="rechargeAmount">
          <el-input v-model="recharge_form.rechargeAmount" placeholder="请输入充值金额" />
        </el-form-item>
        <el-form-item label="支付方式" prop="payType">
          <el-select v-model="recharge_form.payType" placeholder="请选择支付方式">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.PAY_CHANNEL_CODE_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="status">
          <el-radio-group v-model="recharge_form.status">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.PAY_ORDER_STATUS)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRecharge">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style>
.el-row {
  margin-bottom: 20px;
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.bg-green-light {
  background: green;
}

.bg-red-light {
  background: red;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
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

export default {
  name: 'CashierMemberAccountLog',
  components: {
    CashierMember
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
        memberId: null,
        action: null,
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
        rechargeAmount: [{ required: true, message: "充值金额不能为空", trigger: "blur" }],
        payType: [{ required: true, message: "支付方式不能为空", trigger: "change" }],
        status: [{ required: true, message: "支付状态不能为空", trigger: "blur" }],
      },
      member: null,
      member_dialog: false,
      recharge_dialog: false,
      cart: [
        { id: 1,
          name: '内衣',
          price: 766,
          count: 1,
        },
        { id: 2,
          name: '内裤',
          price: 123,
          count: 2,
        },
        { id: 3,
          name: '人字拖',
          price: 445,
          count: 3,
        },
      ],
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true
      // 执行查询
      getMemberAccountLogPage(this.queryParams).then(response => {
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
        action: undefined,
        balance: undefined,
        gift: undefined,
        point: undefined,
        growth: undefined,
        info: undefined
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
    open_member_dialog() {
      this.member_dialog = true
    },
    memberSelected(data) {
      console.log(data)
      this.member = data
      this.member_dialog = false
    },
    handleTotal(){
      if (this.cart.length>0){
        let sum = 0;
        this.cart.map(item => sum = sum + item.count* item.price)
        return ' $ '+sum;
      }else {
        return '';
      }
    },
    clearCart(){
      this.cart = []
    },
    open_recharge_dialog() {
      this.recharge_dialog = true
    },
    recharge(){

    }

  }
}
</script>
