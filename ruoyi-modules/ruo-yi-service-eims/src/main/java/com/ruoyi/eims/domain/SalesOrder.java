package com.ruoyi.eims.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 销售订单对象 t_sales_order
 * 
 * @author denglin
 * @date 2023-02-06
 */
public class SalesOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 客户 */
    @Excel(name = "客户")
    private String customerName;

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
    private BigDecimal totalMoney;

    /** 含税合计 */
    @Excel(name = "含税合计")
    private BigDecimal taxIncludedMoney;

    /** 收取定金 */
    @Excel(name = "收取定金")
    private BigDecimal deposit;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 采购进度 */
    @Excel(name = "采购进度")
    private Long procurementProgress;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 优惠率 */
    @Excel(name = "优惠率")
    private BigDecimal preferentialRate;

    /** 收款优惠 */
    @Excel(name = "收款优惠")
    private BigDecimal collectionPreference;

    /** 优惠后金额 */
    @Excel(name = "优惠后金额")
    private BigDecimal afterDiscountMoney;

    /** 结算账户 */
    @Excel(name = "结算账户")
    private String settlementAccount;

    /** 销售人员 */
    @Excel(name = "销售人员")
    private String salesman;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
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
    public void setTotalMoney(BigDecimal totalMoney) 
    {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoney() 
    {
        return totalMoney;
    }
    public void setTaxIncludedMoney(BigDecimal taxIncludedMoney) 
    {
        this.taxIncludedMoney = taxIncludedMoney;
    }

    public BigDecimal getTaxIncludedMoney() 
    {
        return taxIncludedMoney;
    }
    public void setDeposit(BigDecimal deposit) 
    {
        this.deposit = deposit;
    }

    public BigDecimal getDeposit() 
    {
        return deposit;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setProcurementProgress(Long procurementProgress) 
    {
        this.procurementProgress = procurementProgress;
    }

    public Long getProcurementProgress() 
    {
        return procurementProgress;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setPreferentialRate(BigDecimal preferentialRate) 
    {
        this.preferentialRate = preferentialRate;
    }

    public BigDecimal getPreferentialRate() 
    {
        return preferentialRate;
    }
    public void setCollectionPreference(BigDecimal collectionPreference) 
    {
        this.collectionPreference = collectionPreference;
    }

    public BigDecimal getCollectionPreference() 
    {
        return collectionPreference;
    }
    public void setAfterDiscountMoney(BigDecimal afterDiscountMoney) 
    {
        this.afterDiscountMoney = afterDiscountMoney;
    }

    public BigDecimal getAfterDiscountMoney() 
    {
        return afterDiscountMoney;
    }
    public void setSettlementAccount(String settlementAccount) 
    {
        this.settlementAccount = settlementAccount;
    }

    public String getSettlementAccount() 
    {
        return settlementAccount;
    }
    public void setSalesman(String salesman) 
    {
        this.salesman = salesman;
    }

    public String getSalesman() 
    {
        return salesman;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerName", getCustomerName())
            .append("number", getNumber())
            .append("information", getInformation())
            .append("documentDate", getDocumentDate())
            .append("operator", getOperator())
            .append("totalMoney", getTotalMoney())
            .append("taxIncludedMoney", getTaxIncludedMoney())
            .append("deposit", getDeposit())
            .append("state", getState())
            .append("procurementProgress", getProcurementProgress())
            .append("remarks", getRemarks())
            .append("preferentialRate", getPreferentialRate())
            .append("collectionPreference", getCollectionPreference())
            .append("afterDiscountMoney", getAfterDiscountMoney())
            .append("settlementAccount", getSettlementAccount())
            .append("salesman", getSalesman())
            .toString();
    }
}
