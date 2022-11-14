<template>
  <el-container style="border: 1px solid #eee" >
    <el-header style="text-align: center; font-size: 20px; background-color: #272738" >
      <span style="color: #ffd04b">收 银 台</span>
    </el-header>

      <el-tabs type="border-card" tab-position="left" :stretch="true" style="height:  900px">
        <el-tab-pane style="height: 150px">
          <div slot="label" align="center">
            <div >
              <i class="el-icon-setting"></i>
            </div>
            <div>
              <span >开单</span>
            </div>
          </div>


          <cashier-member-account-log/>
        </el-tab-pane>
        <el-tab-pane label="消息中心">消息中心</el-tab-pane>
        <el-tab-pane label="角色管理">角色管理</el-tab-pane>
        <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
      </el-tabs>
  </el-container>
</template>
<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
li .icon-name {
  display: inline-block;
  padding: 0 3px;
  height: 1em;
}

.el-tabs__item {
  color: white;
  height: 100px;
}
</style>
<script>
import { createLeave}  from "@/api/bpm/leave"
import { getDictDatas, DICT_TYPE } from '@/utils/dict'
import CashierMemberAccountLog from '@/views/shop/cashier/log'

export default {
  // name: "Cashier",
  components: {
    CashierMemberAccountLog
  },
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(20).fill(item),
      // 表单参数
      form: {
        startTime: undefined,
        endTime: undefined,
        type: undefined,
        reason: undefined,
      },
      // 表单校验
      rules: {
        startTime: [{ required: true, message: "开始时间不能为空", trigger: "blur" }],
        endTime: [{ required: true, message: "结束时间不能为空", trigger: "blur" }],
        type: [{ required: true, message: "请假类型不能为空", trigger: "change" }],
        reason: [{ required: true, message: "请假原因不能为空", trigger: "change" }],
      },

      typeDictData: getDictDatas(DICT_TYPE.BPM_OA_LEAVE_TYPE),
    };
  },
  created() {
  },
  methods: {
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }

        // 添加的提交
        createLeave(this.form).then(response => {
          this.$modal.msgSuccess("发起成功");
          this.$tab.closeOpenPage({ path: "/bpm/oa/leave" });
        });
      });
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  }
};
</script>
