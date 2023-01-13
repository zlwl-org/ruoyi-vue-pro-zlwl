<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-header style="text-align: center; font-size: 20px; background-color: #272738" >
      <span style="color: #ffd04b">收 银 台</span>
    </el-header>

    <el-container>
      <el-aside width="200px" style="background-color: #545c64">
        <el-menu
          default-active="1"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <el-menu-item index="1">
            <i class="el-icon-setting"></i>
            <span slot="title">开单</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-setting"></i>
            <span slot="title">充值</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <div >
              <svg-icon icon-class="user"/>
            </div>
            <div >
              <span slot="title" style="text-align: center">导航二</span>
            </div>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">订单</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-setting"></i>
            <span slot="title">会员</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="date" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
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
</style>
<script>
import { createLeave}  from "@/api/bpm/leave"
import { getDictDatas, DICT_TYPE } from '@/utils/dict'

export default {
  name: "Cashier",
  components: {
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
