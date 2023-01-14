<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="queryParams.address" placeholder="请输入地址" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="网络" prop="net">
        <el-select v-model="queryParams.net" placeholder="请选择网络" clearable size="small">
          <el-option v-for="item in this.netList"
                       :key="item.symbol" :label="item.name" :value="item.symbol"/>
        </el-select>
      </el-form-item>
      <el-form-item label="代币" prop="symbol">
        <el-input v-model="queryParams.symbol" placeholder="请输入代币" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleImport"
                   v-hasPermi="['blockchain:user-wallet:create']">导入钱包</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['blockchain:user-wallet:create']">添加钱包</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['blockchain:user-wallet:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" width="80" />
      <el-table-column label="名称" align="center" prop="name" width="100">
        <template v-slot="scope">
          <span>{{scope.row.name}}</span>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address" width="400">
        <template v-slot="scope">
          <el-link type="success" @click="openExplorer(scope.row.symbol, scope.row.address)">{{scope.row.address}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="网络" align="center" prop="net" width="160">
<!--        <template v-slot="scope">-->
<!--          <dict-tag :type="DICT_TYPE.BLOCKCHAIN_NET_TYPE" :value="scope.row.net" />-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="代币" align="center" prop="symbol" />
      <el-table-column label="余额" align="center" prop="balance" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-delete"
                     v-hasPermi="['blockchain:user-wallet:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加) -->
    <el-dialog :title="title" :visible.sync="add" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网络" prop="net">
          <el-select v-model="form.net" placeholder="请选择网络" filterable clearable>
            <el-option v-for="item in this.netList" :key="item.symbol" :label="item.name" :value="item.symbol"/>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 对话框(导入) -->
    <el-dialog :title="title" :visible.sync="impot" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网络" prop="net">
          <el-select v-model="form.net" placeholder="请选择网络" filterable clearable>
            <el-option v-for="item in this.netList" :key="item.symbol" :label="item.name" :value="item.symbol"/>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="助记词" prop="mnemonic">
          <el-input type="textarea" :rows="3" v-model="form.mnemonic" placeholder="请输入助记词" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="impot=false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 对话框(修改) -->
    <el-dialog :title="title" :visible.sync="edit" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="edit=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  createUserWallet,
  updateUserWallet,
  deleteUserWallet,
  getUserWallet,
  getUserWalletPage,
  exportUserWalletExcel,
  importUserWallet
} from '@/api/blockchain/userWallet'
import { getNetSimple } from '@/api/blockchain/net'
import ScrollPane from '@/layout/components/TagsView/ScrollPane'

export default {
  name: "UserWallet",
  components: {
    ScrollPane
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
      // 用户钱包列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      add: false,
      impot: false,
      edit: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        userId: null,
        name: null,
        address: null,
        net: null,
        symbol: null,
        balance: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mnemonic: [{ required: true, message: "助记词不能为空", trigger: "blur" }],
        net: [{ required: true, message: "网络不能为空", trigger: "blur" }],
      },
      netList: {},
    };
  },
  created() {
    this.getList();
    this.getNetList();
  },
  methods: {
    openExplorer(symbol, address){
      let url
      if (symbol === 'ETH') {
        url = 'https://etherscan.io/address/'
      } else if (symbol === "MATIC"){
        url = 'https://polygonscan.com/address/'
      }
      window.open(url + address,'_blank')
    },
    getNetList(){
      getNetSimple().then(response =>{
        this.netList = response.data;
      })
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getUserWalletPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.add = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        userId: undefined,
        name: undefined,
        address: undefined,
        net: undefined,
        symbol: undefined,
        balance: undefined,
      };
      this.resetForm("form");
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
      this.add = true;
      this.title = "生成钱包";
    },
    handleImport() {
      this.reset();
      this.impot = true;
      this.title = "导入钱包";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getUserWallet(id).then(response => {
        this.form = response.data;
        this.edit = true;
        this.title = "修改钱包";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateUserWallet(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.edit = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        if (this.add === true){
          createUserWallet(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.add = false;
            this.getList();
          });
        }
        if (this.impot === true){
          importUserWallet(this.form).then(response => {
            this.$modal.msgSuccess("导入成功");
            this.impot = false;
            this.getList();
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除用户钱包编号为"' + id + '"的数据项?').then(function() {
          return deleteUserWallet(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有用户钱包数据项?').then(() => {
          this.exportLoading = true;
          return exportUserWalletExcel(params);
        }).then(response => {
          this.$download.excel(response, '用户钱包.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
