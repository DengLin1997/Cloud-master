package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.PaymentDoc;

/**
 * 付快但Service接口
 * 
 * @author denglin
 * @date 2023-02-05
 */
public interface IPaymentDocService 
{
    /**
     * 查询付快但
     * 
     * @param id 付快但主键
     * @return 付快但
     */
    public PaymentDoc selectPaymentDocById(Long id);

    /**
     * 查询付快但列表
     * 
     * @param paymentDoc 付快但
     * @return 付快但集合
     */
    public List<PaymentDoc> selectPaymentDocList(PaymentDoc paymentDoc);

    /**
     * 新增付快但
     * 
     * @param paymentDoc 付快但
     * @return 结果
     */
    public int insertPaymentDoc(PaymentDoc paymentDoc);

    /**
     * 修改付快但
     * 
     * @param paymentDoc 付快但
     * @return 结果
     */
    public int updatePaymentDoc(PaymentDoc paymentDoc);

    /**
     * 批量删除付快但
     * 
     * @param ids 需要删除的付快但主键集合
     * @return 结果
     */
    public int deletePaymentDocByIds(Long[] ids);

    /**
     * 删除付快但信息
     * 
     * @param id 付快但主键
     * @return 结果
     */
    public int deletePaymentDocById(Long id);
}
