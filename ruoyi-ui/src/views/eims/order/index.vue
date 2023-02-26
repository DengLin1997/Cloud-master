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
      <el-table-column label="金额合计" align="center" prop="totalAmount" />
      <el-table-column label="支付定金" align="center" prop="deposit" />
      <el-table-column label="状态" align="center" prop="state" >
        <template scope="scope">
          <p v-if="scope.row.state=='0'" style="color: red">待审核</p>
          <p v-if="scope.row.state=='1'" style="color: yellow">已审核</p>
          <p v-if="scope.row.state=='2'" style="color: green">完成采购</p>
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
        <el-form-item label="供应商" prop="supplierName">
          <el-col :span="7">
            <el-select v-model="form.supplierName" placeholder="请选择供应商">
              <el-option
                v-for="dict in supplierList"
                :label="dict.name"
                :value="dict.name"
              >
                <span style="float: left">{{ dict.name }}</span>
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span=3>
            <el-button icon="el-icon-search" @click="getCommodityList">选择商品</el-button>
          </el-col>
          <el-col :span="8"></el-col>
        </el-form-item>
        <el-divider></el-divider>
        <el-table  :data="commodityListInfo" style="width: 100%" :row-key="getRowKey" @selection-change="getChange($event,'select')" >
          <el-table-column prop="name" label="名字" >
          </el-table-column>
          <el-table-column prop="specifications" label="规格" >
          </el-table-column>
          <el-table-column prop="model" label="型号">
          </el-table-column>
          <el-table-column prop="colour" label="颜色">
          </el-table-column>
          <el-table-column prop="company" label="单位">
          </el-table-column>
          <el-table-column prop="purchasePrice" label="价格">
          </el-table-column>
          <el-table-column prop="quantity" label="数量">
            <template slot-scope="scope">
              <el-input v-model="scope.row.quantity"  placeholder="请输入数量" @input="getTotalPrice(scope.row)"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="totalPrice" label="总价" v-if="false">
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
                  :value="dict.name"
                >
                  <span style="float: left">{{ dict.name }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="金额合计" prop="totalAmount">
              <el-input v-model="form.totalAmount" placeholder="" disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="优惠金额" prop="discount">
              <el-input v-model="form.discount" placeholder="请输入优惠金额" @input="getDiscount" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="优惠后金额" prop="discountAfter" label-width="100px">
              <el-input v-model="form.discountAfter" placeholder="" disabled="true" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="支付定金" prop="deposit">
              <el-input v-model="form.deposit" placeholder="请输入支付定金" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button type="primary" @click="submitForm1">添加并审核</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 选择商品对话框 -->
    <el-dialog :title="title" :visible.sync="open1" width="1000px" append-to-body>
      <el-table  :data="commodityList" style="width: 100%" :row-key="getRowKey" @selection-change="getChange($event,'select')">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="name" label="名字" >
        </el-table-column>
        <el-table-column prop="specifications" label="规格" >
        </el-table-column>
        <el-table-column prop="model" label="型号">
        </el-table-column>
        <el-table-column prop="colour" label="颜色">
        </el-table-column>
        <el-table-column prop="company" label="单位">
        </el-table-column>
        <el-table-column prop="purchasePrice" label="价格">
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/eims/order";
import { listSupplier } from "@/api/data/supplier";
import {listCommodity} from "@/api/eims/commodity";
import {addDoc} from "@/api/eims/doc";
import {listSettlement} from "../../../api/data/settlement";


export default {
  name: "Order",
  data() {
    return {
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
        state: null,
        quantity: null,
        totalAmount: null,
        discount: null,
        discountAfter: null,
        information: null,
        commodityListInfo: [],
      },
      form1:{
        purchaseId: null,
        supplierName: null,
        operator: null
      },
      // 表单校验
      rules: {
      },
      rules1: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
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
      this.reset1();
    },
    cancel1() {
      this.open1 = false;
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
        totalAmount: null,
        deposit: null,
        state: null,
        remarks: null,
        discount: null,
        discountAfter: null,
        settlementAccount: null,
        commodityListInfo: [],
        quantity: null,
      };
      this.resetForm("form");
    },
    reset1() {
      this.form1 = {
        id: null,
        purchaseId: null,
        supplierName: null,
        operator: null
      };
      this.resetForm("form1");
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
      this.title = "添加采购订单";
      this.getSupplier();
      this.getSettlementList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=0;
            this.form.commodityListInfo=this.commodityListInfo;
            addOrder(this.form).then(response => {
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
            this.form.state=1;
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.state=1;
            addOrder(this.form).then(response => {
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
        return delOrder(ids);
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
    /** 选择商品按钮 */
    getCommodityList() {
      this.open1 = true;
      this.title = "添加";
      this.getCommodityListInfo();
    },
    /** 选择商品信息 */
    getCommodityListInfo() {
      listCommodity().then(response => {
        this.commodityList = response.rows;
      });
    },
    /** 选择商品勾选事件 */
    getChange(row, key){
      if(key === 'select'){
        // 是否上锁
        if(this.flag) return
        this.commodityListInfo = row
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
  }
};
</script>
