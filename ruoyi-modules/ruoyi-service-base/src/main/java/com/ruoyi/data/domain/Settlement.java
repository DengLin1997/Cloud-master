package com.ruoyi.data.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 结算账户对象 t_settlement
 * 
 * @author denglin
 * @date 2023-02-04
 */
public class Settlement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 期初金额 */
    @Excel(name = "期初金额")
    private BigDecimal initialAmount;

    /** 当前余额 */
    @Excel(name = "当前余额")
    private BigDecimal currentBalance;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setInitialAmount(BigDecimal initialAmount) 
    {
        this.initialAmount = initialAmount;
    }

    public BigDecimal getInitialAmount() 
    {
        return initialAmount;
    }
    public void setCurrentBalance(BigDecimal currentBalance) 
    {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getCurrentBalance() 
    {
        return currentBalance;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("number", getNumber())
            .append("initialAmount", getInitialAmount())
            .append("currentBalance", getCurrentBalance())
            .append("remarks", getRemarks())
            .toString();
    }
}
