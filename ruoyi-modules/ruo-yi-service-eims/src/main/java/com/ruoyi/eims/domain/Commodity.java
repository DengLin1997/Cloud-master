package com.ruoyi.eims.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 商品对象 t_commodity
 * 
 * @author denglin
 * @date 2023-02-04
 */
public class Commodity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 条码 */
    @Excel(name = "条码")
    private Long barCode;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 规格 */
    @Excel(name = "规格")
    private String specifications;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 颜色 */
    @Excel(name = "颜色")
    private String colour;

    /** 类别 */
    @Excel(name = "类别")
    private String category;

    /** 扩展信息 */
    @Excel(name = "扩展信息")
    private String extendedInformation;

    /** 单位 */
    @Excel(name = "单位")
    private String company;

    /** 采购价 */
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;

    /** 零售价 */
    @Excel(name = "零售价")
    private BigDecimal retailPrice;

    /** 销售价 */
    @Excel(name = "销售价")
    private BigDecimal salesPrice;

    /** 最低售价 */
    @Excel(name = "最低售价")
    private BigDecimal lowestSellingPrice;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 序列号 */
    @Excel(name = "序列号")
    private Long serialNumber;

    /** 批号 */
    @Excel(name = "批号")
    private Long batchNo;
    /** 库存 */

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBarCode(Long barCode) 
    {
        this.barCode = barCode;
    }

    public Long getBarCode() 
    {
        return barCode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSpecifications(String specifications)
    {
        this.specifications = specifications;
    }

    public String getSpecifications() 
    {
        return specifications;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setColour(String colour) 
    {
        this.colour = colour;
    }

    public String getColour() 
    {
        return colour;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setExtendedInformation(String extendedInformation) 
    {
        this.extendedInformation = extendedInformation;
    }

    public String getExtendedInformation() 
    {
        return extendedInformation;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setPurchasePrice(BigDecimal purchasePrice) 
    {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchasePrice() 
    {
        return purchasePrice;
    }
    public void setRetailPrice(BigDecimal retailPrice) 
    {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailPrice() 
    {
        return retailPrice;
    }
    public void setSalesPrice(BigDecimal salesPrice) 
    {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getSalesPrice() 
    {
        return salesPrice;
    }
    public void setLowestSellingPrice(BigDecimal lowestSellingPrice) 
    {
        this.lowestSellingPrice = lowestSellingPrice;
    }

    public BigDecimal getLowestSellingPrice() 
    {
        return lowestSellingPrice;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setSerialNumber(Long serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public Long getSerialNumber() 
    {
        return serialNumber;
    }
    public void setBatchNo(Long batchNo) 
    {
        this.batchNo = batchNo;
    }

    public Long getBatchNo() 
    {
        return batchNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("barCode", getBarCode())
            .append("name", getName())
            .append("specifications", getSpecifications())
            .append("model", getModel())
            .append("colour", getColour())
            .append("category", getCategory())
            .append("extendedInformation", getExtendedInformation())
            .append("company", getCompany())
            .append("purchasePrice", getPurchasePrice())
            .append("retailPrice", getRetailPrice())
            .append("salesPrice", getSalesPrice())
            .append("lowestSellingPrice", getLowestSellingPrice())
            .append("state", getState())
            .append("serialNumber", getSerialNumber())
            .append("batchNo", getBatchNo())
            .toString();
    }
}
