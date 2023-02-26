package com.ruoyi.eims.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.OrderDetailsMapper;
import com.ruoyi.eims.domain.OrderDetails;
import com.ruoyi.eims.service.IOrderDetailsService;

/**
 * 商品Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-05
 */
@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService 
{
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public OrderDetails selectOrderDetailsById(Long id)
    {
        return orderDetailsMapper.selectOrderDetailsById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param orderDetails 商品
     * @return 商品
     */
    @Override
    public List<OrderDetails> selectOrderDetailsList(OrderDetails orderDetails)
    {
        return orderDetailsMapper.selectOrderDetailsList(orderDetails);
    }

    /**
     * 新增商品
     * 
     * @param orderDetails 商品
     * @return 结果
     */
    @Override
    public int insertOrderDetails(OrderDetails orderDetails)
    {
        return orderDetailsMapper.insertOrderDetails(orderDetails);
    }

    /**
     * 修改商品
     * 
     * @param orderDetails 商品
     * @return 结果
     */
    @Override
    public int updateOrderDetails(OrderDetails orderDetails)
    {
        return orderDetailsMapper.updateOrderDetails(orderDetails);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailsByIds(Long[] ids)
    {
        return orderDetailsMapper.deleteOrderDetailsByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailsById(Long id)
    {
        return orderDetailsMapper.deleteOrderDetailsById(id);
    }
}
