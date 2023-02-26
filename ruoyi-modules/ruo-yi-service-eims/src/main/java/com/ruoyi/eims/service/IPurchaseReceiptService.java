package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.PurchaseReceipt;
import com.ruoyi.eims.domain.PurchaseReceiptDto;

/**
 * 采购入库Service接口
 * 
 * @author denglin
 * @date 2023-02-05
 */
public interface IPurchaseReceiptService 
{
    /**
     * 查询采购入库
     * 
     * @param id 采购入库主键
     * @return 采购入库
     */
    public PurchaseReceipt selectPurchaseReceiptById(Long id);

    /**
     * 查询采购入库列表
     * 
     * @param purchaseReceipt 采购入库
     * @return 采购入库集合
     */
    public List<PurchaseReceipt> selectPurchaseReceiptList(PurchaseReceipt purchaseReceipt);

    /**
     * 新增采购入库
     * 
     * @param purchaseReceiptDto 采购入库
     * @return 结果
     */
    public int insertPurchaseReceipt(PurchaseReceiptDto purchaseReceiptDto);

    /**
     * 修改采购入库
     * 
     * @param purchaseReceipt 采购入库
     * @return 结果
     */
    public int updatePurchaseReceipt(PurchaseReceipt purchaseReceipt);

    /**
     * 批量删除采购入库
     * 
     * @param ids 需要删除的采购入库主键集合
     * @return 结果
     */
    public int deletePurchaseReceiptByIds(Long[] ids);

    /**
     * 删除采购入库信息
     * 
     * @param id 采购入库主键
     * @return 结果
     */
    public int deletePurchaseReceiptById(Long id);
}
