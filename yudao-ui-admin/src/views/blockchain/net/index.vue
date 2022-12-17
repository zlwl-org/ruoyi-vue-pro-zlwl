<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="中文名称" prop="nameZh">
        <el-input v-model="queryParams.nameZh" placeholder="请输入中文名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="原生代币" prop="symbol">
        <el-input v-model="queryParams.symbol" placeholder="请输入原生代币" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="网络类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择网络类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="链ID" prop="chainId">
        <el-input v-model="queryParams.chainId" placeholder="请输入链ID" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['blockchain:net:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['blockchain:net:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="中文名称" align="center" prop="nameZh" />
      <el-table-column label="原生代币" align="center" prop="symbol" />
      <el-table-column label="浏览器" align="center" prop="explorer" />
      <el-table-column label="默认节点" align="center" prop="publicRpc" />
      <el-table-column label="私密节点" align="center" prop="privateRpc" />
      <el-table-column label="网络类型" align="center" prop="type" />
      <el-table-column label="链ID" align="center" prop="chainId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['blockchain:net:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['blockchain:net:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="中文名称" prop="nameZh">
          <el-input v-model="form.nameZh" placeholder="请输入中文名称" />
        </el-form-item>
        <el-form-item label="原生代币" prop="symbol">
          <el-input v-model="form.symbol" placeholder="请输入原生代币" />
        </el-form-item>
        <el-form-item label="浏览器" prop="explorer">
          <el-input v-model="form.explorer" placeholder="请输入浏览器" />
        </el-form-item>
        <el-form-item label="默认节点" prop="publicRpc">
          <el-input v-model="form.publicRpc" placeholder="请输入默认节点" />
        </el-form-item>
        <el-form-item label="私密节点" prop="privateRpc">
          <el-input v-model="form.privateRpc" placeholder="请输入私密节点" />
        </el-form-item>
        <el-form-item label="网络类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择网络类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="链ID" prop="chainId">
          <el-input v-model="form.chainId" placeholder="请输入链ID" />
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
import { createNet, updateNet, deleteNet, getNet, getNetPage, exportNetExcel } from "@/api/blockchain/net";

export default {
  name: "Net",
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
      // 网络列表
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
        nameZh: null,
        symbol: null,
        type: null,
        chainId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getNetPage(this.queryParams).then(response => {
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
        nameZh: undefined,
        symbol: undefined,
        explorer: undefined,
        publicRpc: undefined,
        privateRpc: undefined,
        type: undefined,
        chainId: undefined,
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
      this.open = true;
      this.title = "添加网络";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getNet(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改网络";
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
          updateNet(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createNet(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除网络编号为"' + id + '"的数据项?').then(function() {
          return deleteNet(id);
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
      this.$modal.confirm('是否确认导出所有网络数据项?').then(() => {
          this.exportLoading = true;
          return exportNetExcel(params);
        }).then(response => {
          this.$download.excel(response, '网络.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
