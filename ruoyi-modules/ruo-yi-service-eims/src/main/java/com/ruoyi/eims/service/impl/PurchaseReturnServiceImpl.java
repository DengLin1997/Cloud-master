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
import com.ruoyi.eims.domain.OrderDetails;
import com.ruoyi.eims.domain.PurchaseOrder;
import com.ruoyi.eims.domain.Refund;
import com.ruoyi.eims.dto.PurchaseReturnDto;
import com.ruoyi.eims.mapper.OrderDetailsMapper;
import com.ruoyi.eims.mapper.RefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.PurchaseReturnMapper;
import com.ruoyi.eims.domain.PurchaseReturn;
import com.ruoyi.eims.service.IPurchaseReturnService;

/**
 * 采购退货Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-06
 */
@Service
public class PurchaseReturnServiceImpl implements IPurchaseReturnService 
{
    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private StockService stockService;

    /**
     * 查询采购退货
     * 
     * @param id 采购退货主键
     * @return 采购退货
     */
    @Override
    public PurchaseReturn selectPurchaseReturnById(Long id)
    {
        return purchaseReturnMapper.selectPurchaseReturnById(id);
    }

    /**
     * 查询采购退货列表
     * 
     * @param purchaseReturn 采购退货
     * @return 采购退货
     */
    @Override
    public List<PurchaseReturn> selectPurchaseReturnList(PurchaseReturn purchaseReturn)
    {
        return purchaseReturnMapper.selectPurchaseReturnList(purchaseReturn);
    }

    /**
     * 新增采购退货
     * 
     * @param purchaseReturnDto 采购退货
     * @return 结果
     */
    @Override
    public int insertPurchaseReturn(PurchaseReturnDto purchaseReturnDto)
    {
        PurchaseReturn purchaseReturn = new PurchaseReturn();
        String supplierName="";
        String information="";
        String operator="";
        List<String> numberList = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseReturnDto.getPurchaseOrderListInfo()) {
            supplierName += purchaseOrder.getSupplierName()+",";
            information += purchaseOrder.getInformation()+",";
            operator = purchaseOrder.getOperator();
            numberList.add(purchaseOrder.getNumber());
        }
        purchaseReturn.setSupplierName(supplierName.substring(0,supplierName.length()-1));
        int str=0;
        for(int j = 0; j< 100; j++) {
            str=(int) ((Math.random() * 9 + 1) * 100000);
        }
        purchaseReturn.setNumber("CGD"+str);
        purchaseReturn.setInformation(information.substring(0,information.length()-1));
        purchaseReturn.setDocumentDate(new Date());
        purchaseReturn.setOperator(operator);
        purchaseReturn.setReturnMoney(purchaseReturnDto.getReturnMoney());
        purchaseReturn.setActualMoney(purchaseReturnDto.getActualMoney());
        purchaseReturn.setState(4L);
        purchaseReturn.setRemarks(purchaseReturnDto.getRemarks());
        purchaseReturn.setDiscount(purchaseReturnDto.getDiscount());
        purchaseReturn.setSettlementAccount(purchaseReturnDto.getSettlementAccount());
        purchaseReturn.setArrears(purchaseReturnDto.getArrears());
        purchaseReturn.setWarehouseId(purchaseReturnDto.getWarehouseId());

        //添加退款单
        Refund refund = new Refund();
        refund.setReturnId(purchaseReturn.getNumber());
        refund.setActualMoney(purchaseReturnDto.getActualMoney());
        refund.setSettlementAccount(purchaseReturnDto.getSettlementAccount());
        refund.setReturnTime(new Date());
        refund.setState(0L);
        refundMapper.insertRefund(refund);

        //修改结算账户信息
        AjaxResult info = settlementService.getInfo(Long.valueOf(purchaseReturnDto.getSettlementAccount()));
        Object data = info.get("data");
        ObjectMapper mapper = new ObjectMapper();
        Settlement settlement = mapper.convertValue(data, Settlement.class);
        BigDecimal currentBalance = settlement.getCurrentBalance();
        BigDecimal paymentMoney = purchaseReturnDto.getActualMoney();
        settlement.setCurrentBalance(currentBalance.add(paymentMoney));
        settlementService.edit(settlement);

        //修改库存信息
        for (String number : numberList){
            List<OrderDetails> orderDetailsList = orderDetailsMapper.selectOrderDetailsByNumber(number);
            for (OrderDetails orderDetails : orderDetailsList) {
                AjaxResult infoByBarCode = stockService.getInfoByBarCode(orderDetails.getBarCode());
                Object data1 = infoByBarCode.get("data");
                ObjectMapper objectMapper = new ObjectMapper();
                Stock stock = objectMapper.convertValue(data1, Stock.class);
                long amount = stock.getStock() - orderDetails.getQuantity();
                stock.setStock(amount);
                stockService.edit(stock);
            }

        }

        return purchaseReturnMapper.insertPurchaseReturn(purchaseReturn);
    }

    /**
     * 修改采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    @Override
    public int updatePurchaseReturn(PurchaseReturn purchaseReturn)
    {
        return purchaseReturnMapper.updatePurchaseReturn(purchaseReturn);
    }

    /**
     * 批量删除采购退货
     * 
     * @param ids 需要删除的采购退货主键
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnByIds(Long[] ids)
    {
        return purchaseReturnMapper.deletePurchaseReturnByIds(ids);
    }

    /**
     * 删除采购退货信息
     * 
     * @param id 采购退货主键
     * @return 结果
     */
    @Override
    public int deletePurchaseReturnById(Long id)
    {
        return purchaseReturnMapper.deletePurchaseReturnById(id);
    }
}
