package com.ruoyi.eims.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 采购入库对象 t_purchase_receipt
 *
 * @author denglin
 * @date 2023-02-05
 */
public class PurchaseReceiptDto extends BaseEntity
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

    /** 金额合计 */
    @Excel(name = "金额合计")
    private BigDecimal totalAmount;

    /** 待付金额 */
    @Excel(name = "待付金额")
    private Long waitMoney;

    /** 付款金额 */
    @Excel(name = "付款金额")
    private BigDecimal paymentMoney;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String remarks;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discount;

    /** 本次欠款 */
    @Excel(name = "本次欠款")
    private BigDecimal arrears;

    /** 结算账户 */
    @Excel(name = "结算账户")
    private String settlementAccount;

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
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setWaitMoney(Long waitMoney)
    {
        this.waitMoney = waitMoney;
    }

    public Long getWaitMoney()
    {
        return waitMoney;
    }
    public void setPaymentMoney(BigDecimal paymentMoney)
    {
        this.paymentMoney = paymentMoney;
    }

    public BigDecimal getPaymentMoney()
    {
        return paymentMoney;
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
    public void setArrears(BigDecimal arrears)
    {
        this.arrears = arrears;
    }

    public BigDecimal getArrears()
    {
        return arrears;
    }
    public void setSettlementAccount(String settlementAccount)
    {
        this.settlementAccount = settlementAccount;
    }

    public String getSettlementAccount()
    {
        return settlementAccount;
    }

    @Override
    public String toString() {
        return "PurchaseReceiptDto{" +
                "id=" + id +
                ", supplierName='" + supplierName + '\'' +
                ", number='" + number + '\'' +
                ", information='" + information + '\'' +
                ", documentDate=" + documentDate +
                ", operator='" + operator + '\'' +
                ", totalAmount=" + totalAmount +
                ", waitMoney=" + waitMoney +
                ", paymentMoney=" + paymentMoney +
                ", state=" + state +
                ", remarks='" + remarks + '\'' +
                ", discount=" + discount +
                ", arrears=" + arrears +
                ", settlementAccount='" + settlementAccount + '\'' +
                ", purchaseOrderListInfo=" + purchaseOrderListInfo +
                '}';
    }
}
