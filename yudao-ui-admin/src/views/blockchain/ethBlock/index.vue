<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区块号码" prop="number">
        <el-input v-model="queryParams.number" placeholder="请输入区块号码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="区块哈希" prop="hash">
        <el-input v-model="queryParams.hash" placeholder="请输入区块哈希" clearable @keyup.enter.native="handleQuery"/>
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
                   v-hasPermi="['blockchain:eth-block:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['blockchain:eth-block:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
<!--      <el-table-column label="编号" align="center" prop="id" />-->
      <el-table-column label="区块号码" align="center" prop="number" />
      <el-table-column label="区块哈希" align="center" prop="hash" />
<!--      <el-table-column label="父区块哈希" align="center" prop="parentHash" />-->
      <el-table-column label="nonce" align="center" prop="nonce" />
<!--      <el-table-column label="sha3uncles" align="center" prop="sha3uncles" />-->
<!--      <el-table-column label="logs_bloom" align="center" prop="logsBloom" />-->
<!--      <el-table-column label="transactions_root" align="center" prop="transactionsRoot" />-->
<!--      <el-table-column label="state_root" align="center" prop="stateRoot" />-->
<!--      <el-table-column label="receipts_root" align="center" prop="receiptsRoot" />-->
<!--      <el-table-column label="author" align="center" prop="author" />-->
      <el-table-column label="矿工" align="center" prop="miner" />
<!--      <el-table-column label="mix_hash" align="center" prop="mixHash" />-->
<!--      <el-table-column label="难度" align="center" prop="difficulty" />-->
<!--      <el-table-column label="总难度" align="center" prop="totalDifficulty" />-->
      <el-table-column label="size" align="center" prop="size" />
      <el-table-column label="gas_limit" align="center" prop="gasLimit" />
      <el-table-column label="gas_used" align="center" prop="gasUsed" />
      <el-table-column label="base_fee_per_gas" align="center" prop="baseFeePerGas" />
      <el-table-column label="时间" align="center" prop="timestamp">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.timestamp) }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="是否处理" align="center" prop="done" />-->
<!--      <el-table-column label="信息" align="center" prop="info" />-->
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">-->
<!--        <template v-slot="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['blockchain:eth-block:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['blockchain:eth-block:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区块号码" prop="number">
          <el-input v-model="form.number" placeholder="请输入区块号码" />
        </el-form-item>
        <el-form-item label="区块哈希" prop="hash">
          <el-input v-model="form.hash" placeholder="请输入区块哈希" />
        </el-form-item>
        <el-form-item label="父区块哈希" prop="parentHash">
          <el-input v-model="form.parentHash" placeholder="请输入父区块哈希" />
        </el-form-item>
        <el-form-item label="nonce" prop="nonce">
          <el-input v-model="form.nonce" placeholder="请输入nonce" />
        </el-form-item>
        <el-form-item label="sha3uncles" prop="sha3uncles">
          <el-input v-model="form.sha3uncles" placeholder="请输入sha3uncles" />
        </el-form-item>
        <el-form-item label="logs_bloom" prop="logsBloom">
          <el-input v-model="form.logsBloom" placeholder="请输入logs_bloom" />
        </el-form-item>
        <el-form-item label="transactions_root" prop="transactionsRoot">
          <el-input v-model="form.transactionsRoot" placeholder="请输入transactions_root" />
        </el-form-item>
        <el-form-item label="state_root" prop="stateRoot">
          <el-input v-model="form.stateRoot" placeholder="请输入state_root" />
        </el-form-item>
        <el-form-item label="receipts_root" prop="receiptsRoot">
          <el-input v-model="form.receiptsRoot" placeholder="请输入receipts_root" />
        </el-form-item>
        <el-form-item label="author" prop="author">
          <el-input v-model="form.author" placeholder="请输入author" />
        </el-form-item>
        <el-form-item label="矿工" prop="miner">
          <el-input v-model="form.miner" placeholder="请输入矿工" />
        </el-form-item>
        <el-form-item label="mix_hash" prop="mixHash">
          <el-input v-model="form.mixHash" placeholder="请输入mix_hash" />
        </el-form-item>
        <el-form-item label="难度" prop="difficulty">
          <el-input v-model="form.difficulty" placeholder="请输入难度" />
        </el-form-item>
        <el-form-item label="总难度" prop="totalDifficulty">
          <el-input v-model="form.totalDifficulty" placeholder="请输入总难度" />
        </el-form-item>
        <el-form-item label="size" prop="size">
          <el-input v-model="form.size" placeholder="请输入size" />
        </el-form-item>
        <el-form-item label="gas_limit" prop="gasLimit">
          <el-input v-model="form.gasLimit" placeholder="请输入gas_limit" />
        </el-form-item>
        <el-form-item label="gas_used" prop="gasUsed">
          <el-input v-model="form.gasUsed" placeholder="请输入gas_used" />
        </el-form-item>
        <el-form-item label="时间" prop="timestamp">
          <el-input v-model="form.timestamp" placeholder="请输入时间" />
        </el-form-item>
        <el-form-item label="base_fee_per_gas" prop="baseFeePerGas">
          <el-input v-model="form.baseFeePerGas" placeholder="请输入base_fee_per_gas" />
        </el-form-item>
        <el-form-item label="是否处理" prop="done">
          <el-radio-group v-model="form.done">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="信息" prop="info">
          <el-input v-model="form.info" placeholder="请输入信息" />
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
import { createEthBlock, updateEthBlock, deleteEthBlock, getEthBlock, getEthBlockPage, exportEthBlockExcel } from "@/api/blockchain/ethBlock";

export default {
  name: "EthBlock",
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
      // Eth区块数据列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        number: null,
        hash: null,
        parentHash: null,
        nonce: null,
        sha3uncles: null,
        logsBloom: null,
        transactionsRoot: null,
        stateRoot: null,
        receiptsRoot: null,
        author: null,
        miner: null,
        mixHash: null,
        difficulty: null,
        totalDifficulty: null,
        size: null,
        gasLimit: null,
        gasUsed: null,
        timestamp: null,
        baseFeePerGas: null,
        done: null,
        info: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        number: [{ required: true, message: "区块号码不能为空", trigger: "blur" }],
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
      getEthBlockPage(this.queryParams).then(response => {
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
        number: undefined,
        hash: undefined,
        parentHash: undefined,
        nonce: undefined,
        sha3uncles: undefined,
        logsBloom: undefined,
        transactionsRoot: undefined,
        stateRoot: undefined,
        receiptsRoot: undefined,
        author: undefined,
        miner: undefined,
        mixHash: undefined,
        difficulty: undefined,
        totalDifficulty: undefined,
        size: undefined,
        gasLimit: undefined,
        gasUsed: undefined,
        timestamp: undefined,
        baseFeePerGas: undefined,
        done: undefined,
        info: undefined,
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
      this.title = "添加Eth区块数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getEthBlock(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改Eth区块数据";
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
          updateEthBlock(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createEthBlock(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除Eth区块数据编号为"' + id + '"的数据项?').then(function() {
          return deleteEthBlock(id);
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
      this.$modal.confirm('是否确认导出所有Eth区块数据数据项?').then(() => {
          this.exportLoading = true;
          return exportEthBlockExcel(params);
        }).then(response => {
          this.$download.excel(response, 'Eth区块数据.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
