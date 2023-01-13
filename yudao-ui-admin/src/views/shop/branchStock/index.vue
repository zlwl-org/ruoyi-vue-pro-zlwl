<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店铺" prop="branchId">
        <el-select v-model="queryParams.branchId" placeholder="">
          <el-option v-for="item in branches" :key="parseInt(item.id)" :label="item.name" :value="parseInt(item.id)" />
        </el-select>
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
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['shop:branch-stock:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['shop:branch-stock:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="表单编号" align="center" prop="id" />
      <el-table-column label="店铺" align="center" prop="branchId" :formatter="branchesFormat"/>
      <el-table-column label="店铺" align="center" prop="branchName"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleDetail(scope.row)"
                     v-hasPermi="['shop:branch-stock:update']">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="店铺编号" prop="branchId">-->
<!--          <el-input v-model="form.branchId" placeholder="请输入店铺编号" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
    <el-dialog title="新增表单" :visible.sync="open"  v-dialogDrag append-to-body width="75%">
      <add-branch-stock @createdDone="createdDone" :branches="branches"></add-branch-stock>
    </el-dialog>
    <el-dialog title="表单明细" :visible.sync="detail_open"  v-dialogDrag append-to-body width="80%">
      <detail-branch-stock :item="this.data"></detail-branch-stock>
    </el-dialog>
  </div>
</template>

<script>
import { createBranchStock, updateBranchStock, deleteBranchStock, getBranchStock, getBranchStockPage, exportBranchStockExcel } from "@/api/shop/branchStock";
import AddBranchStock from '@/views/shop/branchStock/add'
import DetailBranchStock from '@/views/shop/branchStock/detial'
import { listSimpleBranches } from '@/api/shop/branch'
import item from '@/layout/components/Sidebar/Item'

export default {
  name: "BranchStock",
  components: {
    DetailBranchStock,
    AddBranchStock
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
      // 门店出入库列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      detail_open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        branchId: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        branchId: [{ required: true, message: "店铺编号不能为空", trigger: "blur" }],
      },
      product_list: [],
      branches: [],
      data: {},
    };
  },
  created() {
    listSimpleBranches().then(response =>{
      this.branches = response.data
      this.getList();
    });
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getBranchStockPage(this.queryParams).then(response => {
        let data = response.data.list;
        data.forEach( item => {
          item.branchName = this.formatBranch(item.branchId)
        })
        this.list = data;
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
        branchId: undefined,
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
    },
    /** 修改按钮操作 */
    handleDetail(row) {
      this.reset();
      // const id = row.id;
      // getBranchStock(id).then(response => {
      //   this.form = response.data;
      //   this.detail_open = true;
      // });
      this.data = row;
      this.detail_open = true;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateBranchStock(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createBranchStock(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除门店出入库编号为"' + id + '"的数据项?').then(function() {
          return deleteBranchStock(id);
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
      this.$modal.confirm('是否确认导出所有门店出入库数据项?').then(() => {
          this.exportLoading = true;
          return exportBranchStockExcel(params);
        }).then(response => {
          this.$download.excel(response, '门店出入库.xls');
          this.exportLoading = false;
        }).catch(() => {});
    },
    createdDone(){
      this.getList();
      this.open = false;
    },
    branchesFormat(row, column) {
      if (!row.branchId) {
        return '未设置门店';
      }
      for (const branch of this.branches) {
        if (row.branchId === branch.id) {
          return branch.name;
        }
      }
      return '未知【' + row.branchId + '】';
    },
    formatBranch(branchId){
      for (const branch of this.branches) {
        if (branchId === branch.id) {
          return branch.name;
        }
        return branchId;
      }
    }

  }
};
</script>
