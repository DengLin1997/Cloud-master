package com.ruoyi.data.mapper;

import java.util.List;
import com.ruoyi.data.domain.Settlement;

/**
 * 结算账户Mapper接口
 * 
 * @author denglin
 * @date 2023-02-04
 */
public interface SettlementMapper 
{
    /**
     * 查询结算账户
     * 
     * @param id 结算账户主键
     * @return 结算账户
     */
    public Settlement selectSettlementById(Long id);

    /**
     * 查询结算账户列表
     * 
     * @param settlement 结算账户
     * @return 结算账户集合
     */
    public List<Settlement> selectSettlementList(Settlement settlement);

    /**
     * 新增结算账户
     * 
     * @param settlement 结算账户
     * @return 结果
     */
    public int insertSettlement(Settlement settlement);

    /**
     * 修改结算账户
     * 
     * @param settlement 结算账户
     * @return 结果
     */
    public int updateSettlement(Settlement settlement);

    /**
     * 删除结算账户
     * 
     * @param id 结算账户主键
     * @return 结果
     */
    public int deleteSettlementById(Long id);

    /**
     * 批量删除结算账户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSettlementByIds(Long[] ids);
}
