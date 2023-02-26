package com.ruoyi.eims.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.base.api.SettlementService;
import com.ruoyi.base.api.StockService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.data.domain.Settlement;
import com.ruoyi.data.domain.Stock;
import com.ruoyi.eims.domain.*;
import com.ruoyi.eims.domain.PurchaseReceiptDto;
import com.ruoyi.eims.mapper.OrderDetailsMapper;
import com.ruoyi.eims.mapper.PaymentDocMapper;
import com.ruoyi.eims.mapper.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.PurchaseReceiptMapper;
import com.ruoyi.eims.service.IPurchaseReceiptService;

/**
 * 采购入库Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-05
 */
@Service
public class PurchaseReceiptServiceImpl implements IPurchaseReceiptService 
{
    @Autowired
    private PurchaseReceiptMapper purchaseReceiptMapper;

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private PaymentDocMapper paymentDocMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询采购入库
     * 
     * @param id 采购入库主键
     * @return 采购入库
     */
    @Override
    public PurchaseReceipt selectPurchaseReceiptById(Long id)
    {
        return purchaseReceiptMapper.selectPurchaseReceiptById(id);
    }

    /**
     * 查询采购入库列表
     * 
     * @param purchaseReceipt 采购入库
     * @return 采购入库
     */
    @Override
    public List<PurchaseReceipt> selectPurchaseReceiptList(PurchaseReceipt purchaseReceipt)
    {
        return purchaseReceiptMapper.selectPurchaseReceiptList(purchaseReceipt);
    }

    /**
     * 新增采购入库
     * 
     * @param purchaseReceiptDto 采购入库
     * @return 结果
     */
    @Override
    public int insertPurchaseReceipt(PurchaseReceiptDto purchaseReceiptDto)
    {
        AjaxResult info = settlementService.getInfo(Long.valueOf(purchaseReceiptDto.getSettlementAccount()));
        Object data = info.get("data");
        ObjectMapper mapper = new ObjectMapper();
        Settlement settlement = mapper.convertValue(data, Settlement.class);
        BigDecimal currentBalance = settlement.getCurrentBalance();
        BigDecimal paymentMoney = purchaseReceiptDto.getPaymentMoney();
        settlement.setCurrentBalance(currentBalance.subtract(paymentMoney));
        settlementService.edit(settlement);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        String supplierName="";
        String information="";
        String operator="";
        List<String> numberList = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseReceiptDto.getPurchaseOrderListInfo()) {
            supplierName += purchaseOrder.getSupplierName()+",";
            information += purchaseOrder.getInformation()+",";
            operator = purchaseOrder.getOperator();
            numberList.add(purchaseOrder.getNumber());
        }
        purchaseReceipt.setSupplierName(supplierName.substring(0,supplierName.length()-1));
        int str=0;
        for(int j = 0; j< 100; j++) {
            str=(int) ((Math.random() * 9 + 1) * 100000);
        }
        purchaseReceipt.setNumber("CGD"+str);
        purchaseReceipt.setInformation(information.substring(0,information.length()-1));
        purchaseReceipt.setDocumentDate(new Date());
        purchaseReceipt.setOperator(operator);
        purchaseReceipt.setTotalAmount(purchaseReceiptDto.getTotalAmount());
        purchaseReceipt.setWaitMoney(purchaseReceiptDto.getWaitMoney());
        purchaseReceipt.setPaymentMoney(purchaseReceiptDto.getPaymentMoney());
        purchaseReceipt.setSettlementAccount(purchaseReceiptDto.getSettlementAccount());
        purchaseReceipt.setRemarks(purchaseReceiptDto.getRemarks());
        purchaseReceipt.setArrears(purchaseReceiptDto.getArrears());
        purchaseReceipt.setDiscount(purchaseReceiptDto.getDiscount());
        purchaseReceipt.setState(purchaseReceiptDto.getState());

        //修改付款单状态
        for (String number : numberList) {
            PaymentDoc paymentDoc = paymentDocMapper.selectPaymentDocByNumber(number);
            paymentDoc.setState(1L);
            paymentDocMapper.updatePaymentDoc(paymentDoc);
        }

        //添加库存
        for (String number : numberList){
            List<OrderDetails> orderDetailsList = orderDetailsMapper.selectOrderDetailsByNumber(number);
            for (OrderDetails orderDetails : orderDetailsList) {
                AjaxResult infoByBarCode = stockService.getInfoByBarCode(orderDetails.getBarCode());
                Object data1 = infoByBarCode.get("data");
                ObjectMapper objectMapper = new ObjectMapper();
                Stock stock = objectMapper.convertValue(data1, Stock.class);
                long amount = stock.getStock() + orderDetails.getQuantity();
                stock.setStock(amount);
                stockService.edit(stock);
            }

        }
        //修改采购订单状态
        for (String number : numberList) {
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectPurchaseOrderByNumber(number);
            purchaseOrder.setState(3L);
            purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
        }

        return purchaseReceiptMapper.insertPurchaseReceipt(purchaseReceipt);
    }

    /**
     * 修改采购入库
     * 
     * @param purchaseReceipt 采购入库
     * @return 结果
     */
    @Override
    public int updatePurchaseReceipt(PurchaseReceipt purchaseReceipt)
    {
        return purchaseReceiptMapper.updatePurchaseReceipt(purchaseReceipt);
    }

    /**
     * 批量删除采购入库
     * 
     * @param ids 需要删除的采购入库主键
     * @return 结果
     */
    @Override
    public int deletePurchaseReceiptByIds(Long[] ids)
    {
        return purchaseReceiptMapper.deletePurchaseReceiptByIds(ids);
    }

    /**
     * 删除采购入库信息
     * 
     * @param id 采购入库主键
     * @return 结果
     */
    @Override
    public int deletePurchaseReceiptById(Long id)
    {
        return purchaseReceiptMapper.deletePurchaseReceiptById(id);
    }
}
