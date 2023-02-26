package com.ruoyi.eims.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.eims.domain.OrderDetails;
import com.ruoyi.eims.domain.PaymentDoc;
import com.ruoyi.eims.mapper.OrderDetailsMapper;
import com.ruoyi.eims.mapper.PaymentDocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.PurchaseOrderMapper;
import com.ruoyi.eims.domain.PurchaseOrder;
import com.ruoyi.eims.service.IPurchaseOrderService;

/**
 * 采购订单Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-04
 */
@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService 
{
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PaymentDocMapper paymentDocMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 查询采购订单
     * 
     * @param id 采购订单主键
     * @return 采购订单
     */
    @Override
    public PurchaseOrder selectPurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.selectPurchaseOrderById(id);
    }

    /**
     * 查询采购订单列表
     * 
     * @param purchaseOrder 采购订单
     * @return 采购订单
     */
    @Override
    public List<PurchaseOrder> selectPurchaseOrderList(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.selectPurchaseOrderList(purchaseOrder);
    }

    /**
     * 新增采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int insertPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        int str=0;
        for(int j = 0; j< 100; j++) {
            str=(int) ((Math.random() * 9 + 1) * 100000);
        }
        purchaseOrder.setNumber("CGD"+str);
        purchaseOrder.setDocumentDate(new Date());
        purchaseOrder.setOperator(SecurityUtils.getUsername());
        purchaseOrder.setInformation(purchaseOrder.getInformation().substring(4,purchaseOrder.getInformation().length()-1));

        PaymentDoc paymentDoc = new PaymentDoc();
        paymentDoc.setPurchaseId(purchaseOrder.getNumber());
        paymentDoc.setPaymentDate(new Date());
        paymentDoc.setSupplierName(purchaseOrder.getSupplierName());
        paymentDoc.setOperator(purchaseOrder.getOperator());
        paymentDoc.setState(0L);
        paymentDocMapper.insertPaymentDoc(paymentDoc);

        List<OrderDetails> commodityListInfo = purchaseOrder.getCommodityListInfo();
        for (OrderDetails orderDetails : commodityListInfo) {
            OrderDetails orderDetails1 = new OrderDetails();
            orderDetails1.setBarCode(orderDetails.getBarCode());
            orderDetails1.setName(orderDetails.getName());
            orderDetails1.setSpecifications(orderDetails.getSpecifications());
            orderDetails1.setModel(orderDetails.getModel());
            orderDetails1.setColour(orderDetails.getColour());
            orderDetails1.setCategory(orderDetails.getCategory());
            orderDetails1.setCompany(orderDetails.getCompany());
            orderDetails1.setPurchasePrice(orderDetails.getPurchasePrice());
            orderDetails1.setQuantity(orderDetails.getQuantity());
            orderDetails1.setPurchaseId(purchaseOrder.getNumber());
            orderDetailsMapper.insertOrderDetails(orderDetails1);
        }

        return purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
    }

    /**
     * 修改采购订单
     * 
     * @param purchaseOrder 采购订单
     * @return 结果
     */
    @Override
    public int updatePurchaseOrder(PurchaseOrder purchaseOrder)
    {
        return purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    /**
     * 批量删除采购订单
     * 
     * @param ids 需要删除的采购订单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderByIds(Long[] ids)
    {
        return purchaseOrderMapper.deletePurchaseOrderByIds(ids);
    }

    /**
     * 删除采购订单信息
     * 
     * @param id 采购订单主键
     * @return 结果
     */
    @Override
    public int deletePurchaseOrderById(Long id)
    {
        return purchaseOrderMapper.deletePurchaseOrderById(id);
    }

}
