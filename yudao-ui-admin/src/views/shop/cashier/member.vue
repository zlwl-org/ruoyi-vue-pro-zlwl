<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="queryParams.nickname" placeholder="请输入昵称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="queryParams.mobile" placeholder="请输入手机号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="会员编号" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="昵称" align="center" prop="nickname" />
      <el-table-column label="手机号" align="center" prop="mobile" >
        <template slot-scope="scope">
          <router-link :to="'/shop/member/detail/' + scope.row.id" class="link-type">
            <span style="font-weight: bold">{{ scope.row.mobile }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="充值余额" align="center" prop="balance" />
      <el-table-column label="赠送余额" align="center" prop="gift" />
      <el-table-column label="积分" align="center" prop="point" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text"  @click="handleSelect(scope.row)">选择</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="销售员" prop="salesman">
          <el-select v-model="form.salesman" placeholder="请选择客户归属" clearable style="width: 100%">
            <el-option v-for="item in users" :key="parseInt(item.id)" :label="item.nickname" :value="parseInt(item.id)" />
          </el-select>
        </el-form-item>
        <el-form-item label="客户类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择客户类型">
            <el-option v-for="dict in getDictDatas(DICT_TYPE.SHOP_CUSTOMER_TYPE)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in getDictDatas(DICT_TYPE.SHOP_MEMBER_STATUS)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="店铺编号" prop="branchId">
          <el-input v-model="form.branchId" placeholder="请输入店铺编号" />
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
import { createMember, updateMember, deleteMember, getMember, getMemberPage, exportMemberExcel } from "@/api/shop/member";
import { listSimpleUsers } from '@/api/system/user'
import { CommonStatusEnum } from '@/utils/constants'
import { listSimpleBranches } from '@/api/shop/branch'

export default {
  name: "CashierMember",
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
      // 会员列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: null,
        nickname: null,
        mobile: null,
        salesman: null,
        type: null,
        status: null,
        branchId: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 用户下拉列表
      users: [],
      // 表单校验
      rules: {
        nickname: [{ required: true, message: "昵称不能为空", trigger: "blur" }],
        mobile: [{ required: true, message: "手机号不能为空", trigger: "blur" }],
        salesman: [{ required: true, message: "销售员不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    // 获得用户列表
    listSimpleUsers().then(response => {
      this.users = response.data;
    });
    listSimpleBranches().then(response =>{
      this.branches = response.data
    });
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getMemberPage(this.queryParams).then(response => {
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
        name: undefined,
        nickname: undefined,
        mobile: undefined,
        salesman: undefined,
        type: 1,
        status: CommonStatusEnum.ENABLE,
        branchId: undefined,
      };
      this.resetForm("form");
    },
    // 用户昵称展示
    userNicknameFormat(row, column) {
      if (!row.salesman) {
        return '未设置';
      }
      for (const user of this.users) {
        if (row.salesman === user.id) {
          return user.nickname;
        }
      }
      return '未知【' + row.salesman + '】';
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
      this.title = "添加会员";
    },
    /** 修改按钮操作 */
    handleSelect(row) {
      this.$emit("memberSelected", row);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateMember(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createMember(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
  }
};
</script>
