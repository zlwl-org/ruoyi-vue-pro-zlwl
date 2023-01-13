<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="促销类型" prop="promotionType">
        <el-select v-model="queryParams.promotionType" placeholder="请选择促销类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_PROMOTION_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.DISABLE_STATUS)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
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
                   v-hasPermi="['shop:promotion:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['shop:promotion:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="活动编号" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="促销类型" align="center" prop="promotionType">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SHOP_PROMOTION_TYPE" :value="scope.row.promotionType" />
        </template>
      </el-table-column>
      <el-table-column label="门槛" align="center" prop="priceCondition" >
        <template slot-scope="scope">
          <span>{{ scope.row.priceCondition || scope.row.amountCondition }}</span>
        </template>
      </el-table-column>
      <el-table-column label="促销" align="center" prop="priceTarget" >
        <template slot-scope="scope">
          <span>{{ scope.row.priceTarget || scope.row.amountTarget }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="产品编号" align="center" prop="productId" />-->
<!--      <el-table-column label="门店编号" align="center" prop="branchId" />-->
<!--      <el-table-column label="商品编号" align="center" prop="goodId" />-->
      <el-table-column label="信息" align="center" prop="info" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.DISABLE_STATUS" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column label="数量门槛" align="center" prop="amountCondition" />-->
<!--      <el-table-column label="数量促销" align="center" prop="amountTarget" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['shop:promotion:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['shop:promotion:delete']">删除</el-button>
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
          <el-input v-model="form.name" placeholder="请输入名称" :disabled="true"/>
        </el-form-item>
        <el-form-item label="促销类型" prop="promotionType">
          <el-select v-model="form.promotionType" placeholder="请选择促销类型" @change="typeSelected">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SHOP_PROMOTION_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="门槛" prop="priceCondition" :rules="[{required: !this.itemControl, message: '不能为空', trigger: 'blur'}]" :hidden="itemControl">
          <el-input v-model="form.priceCondition" placeholder="请输入金额门槛"  @change="handleFormChanges"/>
        </el-form-item>
        <el-form-item label="促销" prop="priceTarget" :rules="[{required: !this.itemControl, message: '不能为空', trigger: 'blur'}]" :hidden="itemControl">
          <el-input v-model="form.priceTarget" placeholder="请输入金额促销"  @change="handleFormChanges"/>
        </el-form-item>
        <el-form-item label="门槛" prop="amountCondition" :rules="[{required: this.itemControl, message: '不能为空', trigger: 'blur'}]" :hidden="!itemControl">
          <el-input v-model="form.amountCondition" placeholder="请输入数量门槛"  @change="handleFormChanges"/>
        </el-form-item>
        <el-form-item label="促销" prop="amountTarget" :rules="[{required: this.itemControl, message: '不能为空', trigger: 'blur'}]" :hidden="!itemControl">
          <el-input v-model="form.amountTarget" placeholder="请输入数量促销"  @change="handleFormChanges"/>
        </el-form-item>
<!--        <el-form-item label="门店编号" prop="branchId">-->
<!--          <el-input v-model="form.branchId" placeholder="请输入门店编号"  @change="handleFormChanges"/>-->
<!--        </el-form-item>-->
        <el-form-item label="产品" prop="product" :rules="[{required: this.itemControl, message: '不能为空', trigger: 'blur'}]" :hidden="!itemControl">
          <el-select v-model="form.product" value-key="id" placeholder="请选择产品" filterable @change="handleFormChanges">
            <el-option v-for="item in this.product_list"
                       :key="item.id" :label="item.name" :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item label="信息" prop="info">
          <el-input v-model="form.info" type="textarea" placeholder="请输入内容" :disabled="true"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.DISABLE_STATUS)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
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
import { createPromotion, updatePromotion, deletePromotion, getPromotion, getPromotionPage, exportPromotionExcel } from "@/api/shop/promotion";
import { getProducts } from '@/api/shop/product'

export default {
  name: "Promotion",
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
      // 促销活动列表
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
        promotionType: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }],
        promotionType: [{ required: true, message: "不能为空", trigger: "blur" }],
      },
      itemControl: true,
      product_list: [],
    };
  },
  created() {
    this.getList();
    this.getProductList()
  },
  methods: {
    typeSelected(type) {
      if (type === 'amount_promotion'){
        this.itemControl = true
        this.form.priceCondition = null
        this.form.priceTarget = null
        this.form.name = null
        this.form.info = null
      }
      if (type === 'price_promotion') {
        this.itemControl = false
        this.form.productId = null
        this.form.amountCondition = null
        this.form.amountTarget = null
        this.form.name = null
        this.form.info = null
      }
    },
    getProductList() {
      getProducts().then(response => {
        let data = JSON.parse(JSON.stringify(response.data))
        // data.forEach(item => {
        //   item['status'] = ''
        // })
        this.product_list = data
      })
    },
    productSelected(value) {
      this.handleFormChanges();
    },
    handleFormChanges(){
      if (this.form.promotionType === 'amount_promotion' && this.form.product && this.form.amountCondition && this.form.amountTarget){
        this.form.name = '买' + this.form.amountCondition + '送' + this.form.amountTarget;
        this.form.info = this.form.product.name + this.form.name
      }
      if (this.form.promotionType === 'price_promotion' && this.form.priceCondition && this.form.priceTarget) {
        this.form.name = '满' + this.form.priceCondition + '减' + this.form.priceTarget;
        this.form.info = this.form.name
      }
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getPromotionPage(this.queryParams).then(response => {
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
        promotionType: undefined,
        productId: undefined,
        branchId: undefined,
        goodId: undefined,
        info: undefined,
        status: undefined,
        priceCondition: undefined,
        priceTarget: undefined,
        amountCondition: undefined,
        amountTarget: undefined,
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
      this.title = "添加促销活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getPromotion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改促销活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        this.form.productId = this.form.product.id
        // 修改的提交
        if (this.form.id != null) {
          updatePromotion(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createPromotion(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除促销活动编号为"' + id + '"的数据项?').then(function() {
          return deletePromotion(id);
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
      this.$modal.confirm('是否确认导出所有促销活动数据项?').then(() => {
          this.exportLoading = true;
          return exportPromotionExcel(params);
        }).then(response => {
          this.$download.excel(response, '促销活动.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
