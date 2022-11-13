<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 18px">基本信息</span>
          </div>
          <div>
            <ul class="list-group">
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                姓名
                <div class="pull-right">{{ member.name || '无' }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="user"/>
                昵称
                <div class="pull-right">{{ member.nickname || '无' }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone"/>
                手机号码
                <div class="pull-right">{{ member.mobile }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                会员类型
                <div class="pull-right" v-if="member.type">
                  {{ this.getDictDataLabel(DICT_TYPE.SHOP_CUSTOMER_TYPE, member.type) }}
                </div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                销售员
                <div class="pull-right" v-if="member.salesman">{{ member.salesman }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date"/>
                创建日期
                <div class="pull-right">{{ parseTime(member.createTime, '{y}-{m}-{d}') }}</div>
              </li>
            </ul>
          </div>
        </el-card>
        <el-card class="box-card" style="margin-top: 10px">
          <div slot="header" class="clearfix">
            <span style="font-size: 18px">账户信息</span>
          </div>
          <div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                充值余额
                <div class="pull-right">{{ member.balance }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                赠送余额
                <div class="pull-right">{{ member.gift }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                积分
                <div class="pull-right">{{ member.point }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree"/>
                成长值
                <div class="pull-right">{{ member.growth }}</div>
              </li>

            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="font-size: 18px">账户明细</span>
          </div>
          <el-tabs v-model="activeTab">
            <!--            <el-tab-pane label="基本资料" name="userinfo">-->
            <!--              <userInfo :user="user" />-->
            <!--            </el-tab-pane>-->
            <!--            <el-tab-pane label="修改密码" name="resetPwd">-->
            <!--              <resetPwd :user="user" />-->
            <!--            </el-tab-pane>-->
            <!--            <el-tab-pane label="社交信息" name="userSocial">-->
            <!--              <userSocial :user="user" :getUser="getUser" :setActiveTab="setActiveTab" />-->
            <!--            </el-tab-pane>-->
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { getMember } from '@/api/shop/member'

export default {
  name: 'MemberDetail',
  components: {},
  data() {
    return {
      memberId: '',
      member: {},
      roleGroup: {},
      postGroup: {},
      activeTab: 'userinfo'
    }
  },
  created() {
    this.memberId = this.$route.params && this.$route.params.memberId
    this.getMemberInfo(this.memberId)
  },
  methods: {
    setActiveTab(activeTab) {
      this.activeTab = activeTab
    },
    getMemberInfo(id) {
      getMember(id).then(response => {
        this.member = response.data
      })
    }
  }
}
</script>
