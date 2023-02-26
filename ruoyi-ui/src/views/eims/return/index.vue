<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="单据编号" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入单据编号"
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
          v-hasPermi="['eims:order:add']"
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
          v-hasPermi="['eims:order:edit']"
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
          v-hasPermi="['eims:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['eims:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="供应商" align="center" prop="supplierName" />
      <el-table-column label="单据编号" align="center" prop="number" />
      <el-table-column label="商品信息" align="center" prop="information" />
      <el-table-column label="单据日期" align="center" prop="documentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.documentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="operator" />
      <el-table-column label="退款金额" align="center" prop="returnMoney" />
      <el-table-column label="实退金额" align="center" prop="actualMoney" />
      <el-table-column label="状态" align="center" prop="state" >
        <template scope="scope">
          <p v-if="scope.row.state=='0'" style="color: red">待审核</p>
          <p v-if="scope.row.state=='1'" style="color: yellow">已审核待⼊库</p>
          <p v-if="scope.row.state=='2'" style="color: green">完成采购</p>
          <p v-if="scope.row.state=='3'" style="color: green">已入库</p>
          <p v-if="scope.row.state=='4'" style="color: blue">退货</p>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['eims:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['eims:order:remove']"
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

    <!-- 添加或修改采购订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-button icon="el-icon-search" @click="getPurchaseOrderList">关联采购单</el-button>
        <el-divider></el-divider>
        <el-table  :data="purchaseOrderListInfo" style="width: 100%" :row-key="getRowKey" @selection-change="getChange($event,'select')" >
          <el-table-column prop="supplierName" label="供应商" >
          </el-table-column>
          <el-table-column prop="number" label="单号" >
          </el-table-column>
          <el-table-column prop="documentDate" label="单据日期">
          </el-table-column>
          <el-table-column prop="operator" label="操作员">
          </el-table-column>
          <el-table-column prop="totalAmount" label="总价">
          </el-table-column>
          <el-table-column prop="discount" label="优惠">
          </el-table-column>
          <el-table-column prop="discountAfter" label="实际金额">
          </el-table-column>
          <el-table-column prop="state" label="状态">
            <template scope="scope">
              <p v-if="scope.row.state=='0'" style="color: red">待审核</p>
              <p v-if="scope.row.state=='1'" >已审核</p>
              <p v-if="scope.row.state=='2'" style="color: green">完成采购</p>
              <p v-if="scope.row.state=='3'">已完成</p>
              <p v-if="scope.row.state=='4'">退货中</p>
              <p v-if="scope.row.state=='5'">已退货</p>
            </template>
          </el-table-column>
        </el-table>
        <el-form-item>
        </el-form-item>
        <el-form-item prop="remarks" label-width="0px">
          <el-input  v-model="form.remarks" type="textarea" placeholder="订单备注" />
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="结算账户" prop="supplierName">
              <el-select v-model="form.settlementAccount" placeholder="请选择账户" width="300px">
                <el-option
                  v-for="dict in settlementList"
                  :label="dict.name"
                  :value="dict.id"
                >
                  <span style="float: left">{{ dict.name }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="订单总额" prop="totalAmount">
              <el-input v-model="form.returnMoney" placeholder="" disabled="disabled" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优惠金额" prop="discount">
              <el-input v-model="form.discount" placeholder="" disabled="disabled"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="选择仓库" prop="warehouseId">
              <el-select v-model="form.warehouseId" placeholder="请选择仓库" width="300px">
                <el-option
                  v-for="dict in warehouseList"
                  :label="dict.warehouseName"
                  :value="dict.id"
                >
                  <span style="float: left">{{ dict.warehouseName }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实退金额" prop="actualMoney">
              <template slot-scope="scope">
                <el-input v-model="form.actualMoney" placeholder=""  />
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本次欠款" prop="arrears">
              <el-input v-model="form.arrears" placeholder="" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button type="primary" @click="submitForm1">添加并审核</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 选择采购单对话框 -->
    <el-dialog :title="title" :visible.sync="open1" width="1000px" append-to-body>
      <el-table  :data="purchaseOrderList" style="width: 100%" :row-key="getRowKey" @selection-change="getChange($event,'select')">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="supplierName" label="供应商" >
        </el-table-column>
        <el-table-column prop="number" label="单号" >
        </el-table-column>
        <el-table-column prop="documentDate" label="单据日期">
        </el-table-column>
        <el-table-column prop="operator" label="操作员">
        </el-table-column>
        <el-table-column prop="totalAmount" label="价格">
        </el-table-column>
        <el-table-column prop="state" label="状态">
          <template scope="scope">
            <p v-if="scope.row.state=='0'" style="color: red">待审核</p>
            <p v-if="scope.row.state=='1'" >已审核</p>
            <p v-if="scope.row.state=='2'" style="color: green">完成采购</p>
            <p v-if="scope.row.state=='3'" >已完成</p>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel1">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder } from "@/api/eims/order";
import { listReturn, getReturn, delReturn, addReturn, updateReturn } from "@/api/eims/return";
import { listSupplier } from "@/api/data/supplier";
import { listWarehouse } from "@/api/data/warehouse";
import {listSettlement} from "../../../api/data/settlement";




export default {
  name: "Order",
  data() {
    return {
      //仓库信息
      warehouseList: [],
      //结算账户
      settlementList:[],
      sumPrice:null,
      totalPrice:null,
      // 表格数据
      quantity: '',
      // 商品表格数据
      purchaseOrderList: [],
      // 商品表格数据
      purchaseOrderListInfo: [],
      //供应商列表
      supplierList: [],
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
      // 采购订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierName: null,
        number: null,
        information: null,
        documentDate: null,
        operator: null,
        totalAmount: null,
        deposit: null,
        state: null,
        remarks: null,
        discount: null,
        discountAfter: null,
        settlementAccount: null
      },
      // 表单参数
      form: {
        supplierName: null,
        number: null,
        information: null,
        documentDate: null,
        operator: null,
        returnMoney: null,
        actualMoney: null,
        state: null,
        remarks: null,
        discount: null,
        arrears: null,
        settlementAccount: null,
        warehouseId: null,
        purchaseOrderListInfo: []
      },
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    tableRowClassName: function (row, index) {
      if (row.state===3) {
        return 'hidden-row';
      }
      return '';
    },
    /** 查询采购订单列表 */
    getList() {
      this.loading = true;
      listReturn(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.purchaseOrderListInfo = [];
      this.totalPrice =null;
      this.reset();
    },
    cancel1() {
      this.open1 = false;
      this.form.returnMoney =this.purchaseOrderListInfo.map(row => row.discountAfter).reduce((acc, cur) => (cur + acc), 0)
      this.form.discount =this.purchaseOrderListInfo.map(row => row.discount).reduce((acc, cur) => (cur + acc), 0)
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        supplierName: null,
        number: null,
        information: null,
        documentDate: null,
        operator: null,
        returnMoney: null,
        actualMoney: null,
        state: null,
        remarks: null,
        discount: null,
        arrears: null,
        settlementAccount: null,
        warehouseId: null,
        purchaseOrderListInfo: []
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
      this.title = "添加采购入库单";
      this.getSettlementList();
      this.getWarehouse();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReturn(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购入库订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            this.form.state=5;
            updateReturn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=4;
            this.form.purchaseOrderListInfo=this.purchaseOrderListInfo;
            this.form.waitMoney=this.form.arrears;
            addReturn(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.purchaseOrderListInfo=[];
              this.getList();
            });
          }
        }
      });
    },
    submitForm1() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            this.form.state=5;
            updateReturn(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=5;
            addReturn(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.purchaseOrderListInfo=[];
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除采购订单编号为"' + ids + '"的数据项？').then(function() {
        return delReturn(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('eims/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    },
    /** 获得供应商信息 */
    getSupplier() {
      listSupplier().then(response => {
        this.supplierList = response.rows;
      });
    },
    /** 关联采购单按钮 */
    getPurchaseOrderList() {
      this.open1 = true;
      this.title = "添加";
      this.getPurchaseOrderListInfo();
    },
    /** 采购单信息 */
    getPurchaseOrderListInfo() {
      listOrder().then(response => {
        this.purchaseOrderList = response.rows;
        this.purchaseOrderList = this.purchaseOrderList.filter(v => v.state ===3)
      });
    },
    /** 采购单勾选事件 */
    getChange(row, key){
      if(key === 'select'){
        // 是否上锁
        if(this.flag) return
        this.purchaseOrderListInfo = row
      }else{
        // 中间参数 从展示表格中选择要删除的数据
        this.deleteData = row
      }
    },
    getRowKey(row) {
      // 唯一标识 作为对应回显参数的依据
      return row.id
    },
    /** input输入计算*/
    getTotalPrice(row){
      row.totalPrice = row.purchasePrice * row.quantity
      this.totalPrice +=row.totalPrice
      this.form.totalAmount = this.totalPrice
      this.form.information +=(row.name+",")
    },
    /** 优惠*/
    getDiscount(){
      this.form.discountAfter = this.form.totalAmount - this.form.discount
    },
    /** 获得结算信息 */
    getSettlementList() {
      listSettlement().then(response => {
        this.settlementList = response.rows;
      });
    },
    payment(row){
      this.form.arrears = this.form.totalAmount - this.form.paymentMoney
    },

    /** 获得仓库信息 */
    getWarehouse() {
      listWarehouse().then(response => {
        this.warehouseList = response.rows;
      });
    },
  }

};
</script>
