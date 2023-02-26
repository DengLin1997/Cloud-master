package com.ruoyi.eims.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.SalesOrderMapper;
import com.ruoyi.eims.domain.SalesOrder;
import com.ruoyi.eims.service.ISalesOrderService;

/**
 * 销售订单Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-06
 */
@Service
public class SalesOrderServiceImpl implements ISalesOrderService 
{
    @Autowired
    private SalesOrderMapper salesOrderMapper;

    /**
     * 查询销售订单
     * 
     * @param id 销售订单主键
     * @return 销售订单
     */
    @Override
    public SalesOrder selectSalesOrderById(Long id)
    {
        return salesOrderMapper.selectSalesOrderById(id);
    }

    /**
     * 查询销售订单列表
     * 
     * @param salesOrder 销售订单
     * @return 销售订单
     */
    @Override
    public List<SalesOrder> selectSalesOrderList(SalesOrder salesOrder)
    {
        return salesOrderMapper.selectSalesOrderList(salesOrder);
    }

    /**
     * 新增销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    @Override
    public int insertSalesOrder(SalesOrder salesOrder)
    {
        return salesOrderMapper.insertSalesOrder(salesOrder);
    }

    /**
     * 修改销售订单
     * 
     * @param salesOrder 销售订单
     * @return 结果
     */
    @Override
    public int updateSalesOrder(SalesOrder salesOrder)
    {
        return salesOrderMapper.updateSalesOrder(salesOrder);
    }

    /**
     * 批量删除销售订单
     * 
     * @param ids 需要删除的销售订单主键
     * @return 结果
     */
    @Override
    public int deleteSalesOrderByIds(Long[] ids)
    {
        return salesOrderMapper.deleteSalesOrderByIds(ids);
    }

    /**
     * 删除销售订单信息
     * 
     * @param id 销售订单主键
     * @return 结果
     */
    @Override
    public int deleteSalesOrderById(Long id)
    {
        return salesOrderMapper.deleteSalesOrderById(id);
    }
}
