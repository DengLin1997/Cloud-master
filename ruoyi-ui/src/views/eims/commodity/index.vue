<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="条码" prop="barCode">
        <el-input
          v-model="queryParams.barCode"
          placeholder="请输入条码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="Specifications">
        <el-input
          v-model="queryParams.Specifications"
          placeholder="请输入规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="型号" prop="model">
        <el-input
          v-model="queryParams.model"
          placeholder="请输入型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="颜色" prop="colour">
        <el-input
          v-model="queryParams.colour"
          placeholder="请输入颜色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="扩展信息" prop="extendedInformation">
        <el-input
          v-model="queryParams.extendedInformation"
          placeholder="请输入扩展信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购价" prop="purchasePrice">
        <el-input
          v-model="queryParams.purchasePrice"
          placeholder="请输入采购价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="零售价" prop="retailPrice">
        <el-input
          v-model="queryParams.retailPrice"
          placeholder="请输入零售价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销售价" prop="salesPrice">
        <el-input
          v-model="queryParams.salesPrice"
          placeholder="请输入销售价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最低售价" prop="lowestSellingPrice">
        <el-input
          v-model="queryParams.lowestSellingPrice"
          placeholder="请输入最低售价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="序列号" prop="serialNumber">
        <el-input
          v-model="queryParams.serialNumber"
          placeholder="请输入序列号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批号" prop="batchNo">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入批号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['eims:commodity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['eims:commodity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['eims:commodity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['eims:commodity:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commodityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="条码" align="center" prop="barCode" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="规格" align="center" prop="Specifications" />
      <el-table-column label="型号" align="center" prop="model" />
      <el-table-column label="颜色" align="center" prop="colour" />
      <el-table-column label="类别" align="center" prop="category" />
      <el-table-column label="扩展信息" align="center" prop="extendedInformation" />
      <el-table-column label="单位" align="center" prop="company" />
      <el-table-column label="采购价" align="center" prop="purchasePrice" />
      <el-table-column label="零售价" align="center" prop="retailPrice" />
      <el-table-column label="销售价" align="center" prop="salesPrice" />
      <el-table-column label="最低售价" align="center" prop="lowestSellingPrice" />
      <el-table-column label="状态" align="center" prop="state" />
      <el-table-column label="序列号" align="center" prop="serialNumber" />
      <el-table-column label="批号" align="center" prop="batchNo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['eims:commodity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['eims:commodity:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="条码" prop="barCode">
          <el-input v-model="form.barCode" placeholder="请输入条码" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="规格" prop="Specifications">
          <el-input v-model="form.Specifications" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="颜色" prop="colour">
          <el-input v-model="form.colour" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="类别" prop="category">
          <el-input v-model="form.category" placeholder="请输入类别" />
        </el-form-item>
        <el-form-item label="扩展信息" prop="extendedInformation">
          <el-input v-model="form.extendedInformation" placeholder="请输入扩展信息" />
        </el-form-item>
        <el-form-item label="单位" prop="company">
          <el-input v-model="form.company" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="采购价" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入采购价" />
        </el-form-item>
        <el-form-item label="零售价" prop="retailPrice">
          <el-input v-model="form.retailPrice" placeholder="请输入零售价" />
        </el-form-item>
        <el-form-item label="销售价" prop="salesPrice">
          <el-input v-model="form.salesPrice" placeholder="请输入销售价" />
        </el-form-item>
        <el-form-item label="最低售价" prop="lowestSellingPrice">
          <el-input v-model="form.lowestSellingPrice" placeholder="请输入最低售价" />
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="序列号" prop="serialNumber">
          <el-input v-model="form.serialNumber" placeholder="请输入序列号" />
        </el-form-item>
        <el-form-item label="批号" prop="batchNo">
          <el-input v-model="form.batchNo" placeholder="请输入批号" />
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
import { listCommodity, getCommodity, delCommodity, addCommodity, updateCommodity } from "@/api/eims/commodity";

export default {
  name: "Commodity",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      commodityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        barCode: null,
        name: null,
        Specifications: null,
        model: null,
        colour: null,
        category: null,
        extendedInformation: null,
        company: null,
        purchasePrice: null,
        retailPrice: null,
        salesPrice: null,
        lowestSellingPrice: null,
        state: null,
        serialNumber: null,
        batchNo: null
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
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      listCommodity(this.queryParams).then(response => {
        this.commodityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        barCode: null,
        name: null,
        Specifications: null,
        model: null,
        colour: null,
        category: null,
        extendedInformation: null,
        company: null,
        purchasePrice: null,
        retailPrice: null,
        salesPrice: null,
        lowestSellingPrice: null,
        state: null,
        serialNumber: null,
        batchNo: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCommodity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCommodity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommodity(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除商品编号为"' + ids + '"的数据项？').then(function() {
        return delCommodity(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('eims/commodity/export', {
        ...this.queryParams
      }, `commodity_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
