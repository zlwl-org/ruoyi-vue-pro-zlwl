<template>
  <div class="app-container">
    <el-card class="box-card">
      <div  class="clearfix">
        <span style="font-size: 20px; font-weight: bold">实时概况</span>
        <span style="font-size: 14px;margin-left: 20px">更新时间: {{parseTime(this.time)}}</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="getShopData">刷新</el-button>
      </div>
    </el-card>
    <el-col :span="12">
      <el-card class="box-card">
        <div slot="header" class="clearfix" align="center">
          <span style="font-size: 20px; font-weight: bold">订单数据</span>
        </div>
        <el-row :gutter="20">
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.todayOrder" title="今日订单"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.todaySale" title="今日销售"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.todayConsume" title="今日消耗"></el-statistic>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 15px">
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.totalOrder" title="总订单数"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.totalSale" title="总销售额"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/3">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.totalConsume" title="总消耗"></el-statistic>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card class="box-card">
        <div slot="header" class="clearfix" align="center">
          <span style="font-size: 20px; font-weight: bold">会员数据</span>
        </div>
        <el-row :gutter="20">
          <el-col :span="24/2">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.todayMember" title="今日新增"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/2">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.todayRecharge" title="今日充值"></el-statistic>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 15px">
          <el-col :span="24/2">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.totalMember" title="总会员"></el-statistic>
            </el-card>
          </el-col>
          <el-col :span="24/2">
            <el-card shadow="hover">
              <el-statistic group-separator="," :precision="0" :value="data.totalRecharge" title="总充值"></el-statistic>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </el-col>

  </div>
</template>

<script>

import { getShopData } from '@/api/shop/data'

export default {
  name: 'ShopIndex',
  components: {},
  data() {
    return {
      data: {
        todayOrder: 0,
        totalOrder: 0,

        todaySale: 0,
        totalSale: 0,

        todayMember: 0,
        totalMember: 0,

        todayRecharge: 0,
        totalRecharge: 0,

        todayConsume: 0,
        totalConsume: 0,
      },
      time: Date,
    }
  },
  created() {
    this.getShopData()
  },
  methods: {
    getShopData() {
      this.time = new Date();
      getShopData().then(response => {
        this.data = response.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
