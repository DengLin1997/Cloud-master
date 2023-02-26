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
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['eims:order:remove']"
        >批量操作</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户" align="center" prop="customerName" />
      <el-table-column label="单据编号" align="center" prop="number" />
      <el-table-column label="商品信息" align="center" prop="information" />
      <el-table-column label="单据日期" align="center" prop="documentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.documentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="operator" />
      <el-table-column label="金额合计" align="center" prop="totalMoney" />
      <el-table-column label="含税合计" align="center" prop="taxIncludedMoney" />
      <el-table-column label="收取定金" align="center" prop="deposit" />
      <el-table-column label="状态" align="center" prop="state" >
        <template scope="scope">
          <p v-if="scope.row.state=='0'" style="color: red">待审核</p>
          <p v-if="scope.row.state=='1'" style="color: yellow">已审核待⼊库</p>
          <p v-if="scope.row.state=='2'" style="color: green">完成采购</p>
          <p v-if="scope.row.state=='3'" style="color: green">已入库</p>
          <p v-if="scope.row.state=='4'" style="color: blue">退货</p>
        </template>
      </el-table-column>
      <el-table-column label="采购进度" align="center" prop="procurementProgress" >
        <template scope="scope">
          <p v-if="scope.row.state=='0'" style="color: darkseagreen">采购中</p>
          <p v-if="scope.row.state=='1'" style="color: dodgerblue">完成采购</p>
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
    <el-dialog :title="title" :visible.sync="open" width="1300px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="客户" prop="customerName">
              <el-select v-model="form.customerName" placeholder="请选择账户" width="300px">
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
          <el-col :span="6">
            <el-form-item label="单据日期" prop="documentDate">
              <el-date-picker clearable
                              v-model="form.documentDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择发生时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="单据编号" prop="number">
              <el-input v-model="form.number" placeholder="请输入编号" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="销售人员" prop="salesman">
              <el-input v-model="form.salesman" placeholder="请输入销售人员" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-button icon="el-icon-plus" type="primary" @click="addThat">新增行</el-button>
        <el-button  @click="submitForm">扫码录入</el-button>
        <el-button icon="el-icon-time" @click="submitForm">历史单据</el-button>
        <el-table  :data="commodityListInfo" ref="demoTable" style="width: 100%" :row-key="getRowKey" @selection-change="getChange($event,'select')" >
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column prop="barCode" label="条码" >
            <template slot-scope="scope">
              <el-input v-model="scope.row.barCode" icon="el-icon-search" @click.native="getCommodityList"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="名称"  >
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="specifications" label="规格">
            <template slot-scope="scope">
              <el-input v-model="scope.row.specifications"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="model" label="型号">
            <template slot-scope="scope">
              <el-input v-model="scope.row.model" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="colour" label="颜色">
            <template slot-scope="scope">
              <el-input v-model="scope.row.colour" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="stock" label="库存">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stock" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="quantity" label="单位">
            <template slot-scope="scope">
              <el-input v-model="scope.row.quantity" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="quantity" label="数量">
            <template slot-scope="scope">
              <el-input v-model="scope.row.quantity" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="salesPrice" label="单价">
            <template slot-scope="scope">
              <el-input v-model="scope.row.salesPrice" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="amountMoney" label="金额">
            <template slot-scope="scope">
              <el-input v-model="scope.row.amountMoney" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="taxRate" label="税率">
            <template slot-scope="scope">
              <el-input v-model="scope.row.taxRate" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="taxAmount" label="税额">
            <template slot-scope="scope">
              <el-input v-model="scope.row.taxAmount" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceTax" label="价税合计">
            <template slot-scope="scope">
              <el-input v-model="scope.row.priceTax" ></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" label="备注">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remarks" ></el-input>
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
              <el-input v-model="form.salesOrderMoney" placeholder="" disabled="disabled" />
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
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
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
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />

        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder } from "@/api/eims/order";
import { listSalesOrder, getSalesOrder, delSalesOrder, addSalesOrder, updateSalesOrder } from "@/api/eims/salesOrder";
import { listSupplier } from "@/api/data/supplier";
import { listWarehouse } from "@/api/data/warehouse";
import {listSettlement} from "../../../api/data/settlement";




export default {
  name: "Order",
  data() {
    return {
      dataId:0,
      //仓库信息
      warehouseList: [],
      //结算账户
      settlementList:[],
      sumPrice:null,
      totalPrice:null,
      // 表格数据
      quantity: '',
      // 商品表格数据
      commodityList: [],
      // 商品表格数据
      commodityListInfo: [],
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
        salesOrderMoney: null,
        actualMoney: null,
        state: null,
        remarks: null,
        discount: null,
        arrears: null,
        settlementAccount: null,
        warehouseId: null,
        commodityListInfo: []
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
    addThat(){
      var d ={
        index: this.dataId++,
        barCode:"",
        name:"",
        specifications:"",
        model:"",
        colour:"",
        stock:"",
        company:"",
        quantity:"",
        salesPrice:"",
        amountMoney:"",
        taxRate:"",
        taxAmount:"",
        priceTax:"",
        remarks:"",
      };
      this.commodityListInfo.push(d);
      setTimeout(() => {
        this.$refs.demoTable.setCurrentRow(d);
      }, 10);

    },
    tableRowClassName: function (row, index) {
      if (row.state===3) {
        return  'hidden-row';
      }
      return  '';
    },
    /** 查询采购订单列表 */
    getList() {
      this.loading = true;
      listSalesOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.commodityListInfo = [];
      this.totalPrice =null;
      this.reset();
    },
    cancel1() {
      this.open1 = false;
      this.form.salesOrderMoney =this.commodityListInfo.map(row => row.discountAfter).reduce((acc, cur) => (cur + acc), 0)
      this.form.discount =this.commodityListInfo.map(row => row.discount).reduce((acc, cur) => (cur + acc), 0)
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
        salesOrderMoney: null,
        actualMoney: null,
        state: null,
        remarks: null,
        discount: null,
        arrears: null,
        settlementAccount: null,
        warehouseId: null,
        commodityListInfo: []
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
      this.title = "新增";
      this.getSettlementList();
      this.getWarehouse();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSalesOrder(id).then(response => {
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
            updateSalesOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=4;
            this.form.commodityListInfo=this.commodityListInfo;
            this.form.waitMoney=this.form.arrears;
            addSalesOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.commodityListInfo=[];
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
            updateSalesOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=5;
            addSalesOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.commodityListInfo=[];
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
        return  delSalesOrder(ids);
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
    getCommodityList() {
      this.open1 = true;
      this.title = "选择商品";
      this.getCommodityListInfo();
    },
    /** 采购单信息 */
    getCommodityListInfo() {
      listOrder().then(response => {
        this.commodityList = response.rows;
        this.commodityList = this.commodityList.filter(v => v.state ===3)
      });
    },
    /** 采购单勾选事件 */
    getChange(row, key){
      if(key === 'select'){
        // 是否上锁
        if(this.flag) salesOrder
        this.commodityListInfo = row
      }else{
        // 中间参数 从展示表格中选择要删除的数据
        this.deleteData = row
      }
    },
    getRowKey(row) {
      // 唯一标识 作为对应回显参数的依据
      return  row.id
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
    change(){
      this.$forceUpdate();  //强制刷新
    }
  }

};
</script>
