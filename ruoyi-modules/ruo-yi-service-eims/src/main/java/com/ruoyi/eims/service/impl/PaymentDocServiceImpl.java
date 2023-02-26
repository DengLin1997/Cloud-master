package com.ruoyi.eims.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.PaymentDocMapper;
import com.ruoyi.eims.domain.PaymentDoc;
import com.ruoyi.eims.service.IPaymentDocService;

/**
 * 付快但Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-05
 */
@Service
public class PaymentDocServiceImpl implements IPaymentDocService 
{
    @Autowired
    private PaymentDocMapper paymentDocMapper;

    /**
     * 查询付快但
     * 
     * @param id 付快但主键
     * @return 付快但
     */
    @Override
    public PaymentDoc selectPaymentDocById(Long id)
    {
        return paymentDocMapper.selectPaymentDocById(id);
    }

    /**
     * 查询付快但列表
     * 
     * @param paymentDoc 付快但
     * @return 付快但
     */
    @Override
    public List<PaymentDoc> selectPaymentDocList(PaymentDoc paymentDoc)
    {
        return paymentDocMapper.selectPaymentDocList(paymentDoc);
    }

    /**
     * 新增付快但
     * 
     * @param paymentDoc 付快但
     * @return 结果
     */
    @Override
    public int insertPaymentDoc(PaymentDoc paymentDoc)
    {
        return paymentDocMapper.insertPaymentDoc(paymentDoc);
    }

    /**
     * 修改付快但
     * 
     * @param paymentDoc 付快但
     * @return 结果
     */
    @Override
    public int updatePaymentDoc(PaymentDoc paymentDoc)
    {
        return paymentDocMapper.updatePaymentDoc(paymentDoc);
    }

    /**
     * 批量删除付快但
     * 
     * @param ids 需要删除的付快但主键
     * @return 结果
     */
    @Override
    public int deletePaymentDocByIds(Long[] ids)
    {
        return paymentDocMapper.deletePaymentDocByIds(ids);
    }

    /**
     * 删除付快但信息
     * 
     * @param id 付快但主键
     * @return 结果
     */
    @Override
    public int deletePaymentDocById(Long id)
    {
        return paymentDocMapper.deletePaymentDocById(id);
    }
}
