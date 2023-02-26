package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.SalesOrder;

/**
 * 销售订单Service接口
 * 
 * @author denglin
 * @date 2023-02-06
 */
public interface ISalesOrderService 
{
    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    public SalesOrder selectSalesOrderById(Long id);

    /**
     * 查询销售订单列表
     * 
     * @param salesOrder 销售订单
     * @return 销售订单集合
     */
    public List<SalesOrder> selectSalesOrderList(SalesOrder salesOrder);

    /**
     * 新增销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    public int insertSalesOrder(SalesOrder salesOrder);

    /**
     * 修改销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    public int updateSalesOrder(SalesOrder salesOrder);

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键集合
     * @return 结果
     */
    public int deleteSalesOrderByIds(Long[] ids);

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    public int deleteSalesOrderById(Long id);
}
