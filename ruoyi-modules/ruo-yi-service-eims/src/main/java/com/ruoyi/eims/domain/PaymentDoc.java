package com.ruoyi.eims.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 付快但对象 t_payment_doc
 * 
 * @author denglin
 * @date 2023-02-05
 */
public class PaymentDoc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 采购单号 */
    @Excel(name = "采购单号")
    private String purchaseId;

    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentDate;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplierName;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Long state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPurchaseId(String purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseId() 
    {
        return purchaseId;
    }
    public void setPaymentDate(Date paymentDate) 
    {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate() 
    {
        return paymentDate;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("purchaseId", getPurchaseId())
            .append("paymentDate", getPaymentDate())
            .append("supplierName", getSupplierName())
            .append("operator", getOperator())
            .append("state", getState())
            .toString();
    }
}
