package com.ruoyi.eims.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.eims.domain.PurchaseOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 采购退货对象 t_purchase_return
 * 
 * @author denglin
 * @date 2023-02-06
 */
public class PurchaseReturnDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String number;

    /** 商品信息 */
    @Excel(name = "商品信息")
    private String information;

    /** 单据日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "单据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date documentDate;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal returnMoney;

    /** 实退金额 */
    @Excel(name = "实退金额")
    private BigDecimal actualMoney;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discount;

    /** 结算账户 */
    @Excel(name = "结算账户")
    private String settlementAccount;

    /** 欠款 */
    @Excel(name = "欠款")
    private BigDecimal arrears;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long warehouseId;

    private List<PurchaseOrder> purchaseOrderListInfo;

    public List<PurchaseOrder> getPurchaseOrderListInfo() {
        return purchaseOrderListInfo;
    }

    public void setPurchaseOrderListInfo(List<PurchaseOrder> purchaseOrderListInfo) {
        this.purchaseOrderListInfo = purchaseOrderListInfo;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setInformation(String information) 
    {
        this.information = information;
    }

    public String getInformation() 
    {
        return information;
    }
    public void setDocumentDate(Date documentDate) 
    {
        this.documentDate = documentDate;
    }

    public Date getDocumentDate() 
    {
        return documentDate;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setReturnMoney(BigDecimal returnMoney) 
    {
        this.returnMoney = returnMoney;
    }

    public BigDecimal getReturnMoney() 
    {
        return returnMoney;
    }
    public void setActualMoney(BigDecimal actualMoney) 
    {
        this.actualMoney = actualMoney;
    }

    public BigDecimal getActualMoney() 
    {
        return actualMoney;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setDiscount(BigDecimal discount) 
    {
        this.discount = discount;
    }

    public BigDecimal getDiscount() 
    {
        return discount;
    }
    public void setSettlementAccount(String settlementAccount) 
    {
        this.settlementAccount = settlementAccount;
    }

    public String getSettlementAccount() 
    {
        return settlementAccount;
    }
    public void setArrears(BigDecimal arrears) 
    {
        this.arrears = arrears;
    }

    public BigDecimal getArrears() 
    {
        return arrears;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }

    @Override
    public String toString() {
        return "PurchaseReturnDto{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", number='" + number + '\'' +
                ", information='" + information + '\'' +
                ", documentDate=" + documentDate +
                ", operator='" + operator + '\'' +
                ", returnMoney=" + returnMoney +
                ", actualMoney=" + actualMoney +
                ", state=" + state +
                ", remarks='" + remarks + '\'' +
                ", discount=" + discount +
                ", settlementAccount='" + settlementAccount + '\'' +
                ", arrears=" + arrears +
                ", warehouseId=" + warehouseId +
                ", purchaseOrderListInfo=" + purchaseOrderListInfo +
                '}';
    }
}
