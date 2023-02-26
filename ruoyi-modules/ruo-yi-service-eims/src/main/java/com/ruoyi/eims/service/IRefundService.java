package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.Refund;

/**
 * 退款Service接口
 * 
 * @author denglin
 * @date 2023-02-06
 */
public interface IRefundService 
{
    /**
     * 查询退款
     * 
     * @param id 退款主键
     * @return 退款
     */
    public Refund selectRefundById(Long id);

    /**
     * 查询退款列表
     * 
     * @param refund 退款
     * @return 退款集合
     */
    public List<Refund> selectRefundList(Refund refund);

    /**
     * 新增退款
     * 
     * @param refund 退款
     * @return 结果
     */
    public int insertRefund(Refund refund);

    /**
     * 修改退款
     * 
     * @param refund 退款
     * @return 结果
     */
    public int updateRefund(Refund refund);

    /**
     * 批量删除退款
     * 
     * @param ids 需要删除的退款主键集合
     * @return 结果
     */
    public int deleteRefundByIds(Long[] ids);

    /**
     * 删除退款信息
     * 
     * @param id 退款主键
     * @return 结果
     */
    public int deleteRefundById(Long id);
}
