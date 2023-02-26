package com.ruoyi.eims.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.RefundMapper;
import com.ruoyi.eims.domain.Refund;
import com.ruoyi.eims.service.IRefundService;

/**
 * 退款Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-06
 */
@Service
public class RefundServiceImpl implements IRefundService 
{
    @Autowired
    private RefundMapper refundMapper;

    /**
     * 查询退款
     * 
     * @param id 退款主键
     * @return 退款
     */
    @Override
    public Refund selectRefundById(Long id)
    {
        return refundMapper.selectRefundById(id);
    }

    /**
     * 查询退款列表
     * 
     * @param refund 退款
     * @return 退款
     */
    @Override
    public List<Refund> selectRefundList(Refund refund)
    {
        return refundMapper.selectRefundList(refund);
    }

    /**
     * 新增退款
     * 
     * @param refund 退款
     * @return 结果
     */
    @Override
    public int insertRefund(Refund refund)
    {
        return refundMapper.insertRefund(refund);
    }

    /**
     * 修改退款
     * 
     * @param refund 退款
     * @return 结果
     */
    @Override
    public int updateRefund(Refund refund)
    {
        return refundMapper.updateRefund(refund);
    }

    /**
     * 批量删除退款
     * 
     * @param ids 需要删除的退款主键
     * @return 结果
     */
    @Override
    public int deleteRefundByIds(Long[] ids)
    {
        return refundMapper.deleteRefundByIds(ids);
    }

    /**
     * 删除退款信息
     * 
     * @param id 退款主键
     * @return 结果
     */
    @Override
    public int deleteRefundById(Long id)
    {
        return refundMapper.deleteRefundById(id);
    }
}
