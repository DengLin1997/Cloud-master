package com.ruoyi.base.api.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品对象 t_stock
 * 
 * @author denglin
 * @date 2023-02-05
 */
public class Stock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 条码 */
    @Excel(name = "条码")
    private String barCode;

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

    /** 单位 */
    @Excel(name = "单位")
    private String company;

    /** 采购价 */
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;

    /** 初始库存 */
    @Excel(name = "初始库存")
    private Long initialInventory;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

    /** 库存金额 */
    @Excel(name = "库存金额")
    private String stockMoney;

    /** 流水 */
    @Excel(name = "流水")
    private String flowingWater;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBarCode(String barCode) 
    {
        this.barCode = barCode;
    }

    public String getBarCode() 
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
    public void setInitialInventory(Long initialInventory) 
    {
        this.initialInventory = initialInventory;
    }

    public Long getInitialInventory() 
    {
        return initialInventory;
    }
    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }
    public void setStockMoney(String stockMoney) 
    {
        this.stockMoney = stockMoney;
    }

    public String getStockMoney() 
    {
        return stockMoney;
    }
    public void setFlowingWater(String flowingWater) 
    {
        this.flowingWater = flowingWater;
    }

    public String getFlowingWater() 
    {
        return flowingWater;
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
            .append("company", getCompany())
            .append("purchasePrice", getPurchasePrice())
            .append("initialInventory", getInitialInventory())
            .append("stock", getStock())
            .append("stockMoney", getStockMoney())
            .append("flowingWater", getFlowingWater())
            .toString();
    }
}
