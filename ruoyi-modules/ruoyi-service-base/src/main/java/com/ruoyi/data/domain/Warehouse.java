package com.ruoyi.data.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 仓库管理对象 t_warehouse
 * 
 * @author denglin
 * @date 2023-02-04
 */
public class Warehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 仓库名字 */
    @Excel(name = "仓库名字")
    private String warehouseName;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String warehouseAddress;

    /** 仓储费 */
    @Excel(name = "仓储费")
    private BigDecimal storageFee;

    /** 搬运费 */
    @Excel(name = "搬运费")
    private BigDecimal handlingFee;

    /** 负责人 */
    @Excel(name = "负责人")
    private String personCharge;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

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
    public void setWarehouseName(String warehouseName) 
    {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() 
    {
        return warehouseName;
    }
    public void setWarehouseAddress(String warehouseAddress) 
    {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseAddress() 
    {
        return warehouseAddress;
    }
    public void setStorageFee(BigDecimal storageFee) 
    {
        this.storageFee = storageFee;
    }

    public BigDecimal getStorageFee() 
    {
        return storageFee;
    }
    public void setHandlingFee(BigDecimal handlingFee) 
    {
        this.handlingFee = handlingFee;
    }

    public BigDecimal getHandlingFee() 
    {
        return handlingFee;
    }
    public void setPersonCharge(String personCharge) 
    {
        this.personCharge = personCharge;
    }

    public String getPersonCharge() 
    {
        return personCharge;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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
            .append("warehouseName", getWarehouseName())
            .append("warehouseAddress", getWarehouseAddress())
            .append("storageFee", getStorageFee())
            .append("handlingFee", getHandlingFee())
            .append("personCharge", getPersonCharge())
            .append("sort", getSort())
            .append("remarks", getRemarks())
            .toString();
    }
}
