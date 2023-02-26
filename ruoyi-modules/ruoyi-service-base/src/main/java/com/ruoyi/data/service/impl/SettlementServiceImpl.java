package com.ruoyi.data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.data.mapper.SettlementMapper;
import com.ruoyi.data.domain.Settlement;
import com.ruoyi.data.service.ISettlementService;

/**
 * 结算账户Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-04
 */
@Service
public class SettlementServiceImpl implements ISettlementService 
{
    @Autowired
    private SettlementMapper settlementMapper;

    /**
     * 查询结算账户
     * 
     * @param id 结算账户主键
     * @return 结算账户
     */
    @Override
    public Settlement selectSettlementById(Long id)
    {
        return settlementMapper.selectSettlementById(id);
    }

    /**
     * 查询结算账户列表
     * 
     * @param settlement 结算账户
     * @return 结算账户
     */
    @Override
    public List<Settlement> selectSettlementList(Settlement settlement)
    {
        return settlementMapper.selectSettlementList(settlement);
    }

    /**
     * 新增结算账户
     * 
     * @param settlement 结算账户
     * @return 结果
     */
    @Override
    public int insertSettlement(Settlement settlement)
    {
        return settlementMapper.insertSettlement(settlement);
    }

    /**
     * 修改结算账户
     * 
     * @param settlement 结算账户
     * @return 结果
     */
    @Override
    public int updateSettlement(Settlement settlement)
    {
        return settlementMapper.updateSettlement(settlement);
    }

    /**
     * 批量删除结算账户
     * 
     * @param ids 需要删除的结算账户主键
     * @return 结果
     */
    @Override
    public int deleteSettlementByIds(Long[] ids)
    {
        return settlementMapper.deleteSettlementByIds(ids);
    }

    /**
     * 删除结算账户信息
     * 
     * @param id 结算账户主键
     * @return 结果
     */
    @Override
    public int deleteSettlementById(Long id)
    {
        return settlementMapper.deleteSettlementById(id);
    }
}
