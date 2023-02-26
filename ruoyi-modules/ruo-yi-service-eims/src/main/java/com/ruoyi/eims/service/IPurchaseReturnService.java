package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.PurchaseReturn;
import com.ruoyi.eims.dto.PurchaseReturnDto;

/**
 * 采购退货Service接口
 * 
 * @author denglin
 * @date 2023-02-06
 */
public interface IPurchaseReturnService 
{
    /**
     * 查询采购退货
     * 
     * @param id 采购退货主键
     * @return 采购退货
     */
    public PurchaseReturn selectPurchaseReturnById(Long id);

    /**
     * 查询采购退货列表
     * 
     * @param purchaseReturn 采购退货
     * @return 采购退货集合
     */
    public List<PurchaseReturn> selectPurchaseReturnList(PurchaseReturn purchaseReturn);

    /**
     * 新增采购退货
     * 
     * @param purchaseReturnDto 采购退货
     * @return 结果
     */
    public int insertPurchaseReturn(PurchaseReturnDto purchaseReturnDto);

    /**
     * 修改采购退货
     * 
     * @param purchaseReturn 采购退货
     * @return 结果
     */
    public int updatePurchaseReturn(PurchaseReturn purchaseReturn);

    /**
     * 批量删除采购退货
     * 
     * @param ids 需要删除的采购退货主键集合
     * @return 结果
     */
    public int deletePurchaseReturnByIds(Long[] ids);

    /**
     * 删除采购退货信息
     * 
     * @param id 采购退货主键
     * @return 结果
     */
    public int deletePurchaseReturnById(Long id);
}
