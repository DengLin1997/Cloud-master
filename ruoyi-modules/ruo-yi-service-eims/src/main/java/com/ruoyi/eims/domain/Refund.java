package com.ruoyi.eims.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 退款对象 t_refund
 * 
 * @author denglin
 * @date 2023-02-06
 */
public class Refund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 退货单号 */
    @Excel(name = "退货单号")
    private String returnId;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal actualMoney;

    /** 结算账户 */
    @Excel(name = "结算账户")
    private String settlementAccount;

    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setReturnId(String returnId) 
    {
        this.returnId = returnId;
    }

    public String getReturnId() 
    {
        return returnId;
    }
    public void setActualMoney(BigDecimal actualMoney)
    {
        this.actualMoney = actualMoney;
    }

    public BigDecimal getActualMoney()
    {
        return actualMoney;
    }
    public void setSettlementAccount(String settlementAccount) 
    {
        this.settlementAccount = settlementAccount;
    }

    public String getSettlementAccount() 
    {
        return settlementAccount;
    }
    public void setReturnTime(Date returnTime) 
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() 
    {
        return returnTime;
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
            .append("returnId", getReturnId())
            .append("actualMoney", getActualMoney())
            .append("settlementAccount", getSettlementAccount())
            .append("returnTime", getReturnTime())
            .append("state", getState())
            .toString();
    }
}
