package com.ruoyi.eims.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.eims.mapper.CommodityMapper;
import com.ruoyi.eims.domain.Commodity;
import com.ruoyi.eims.service.ICommodityService;

/**
 * 商品Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-04
 */
@Service
public class CommodityServiceImpl implements ICommodityService 
{
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public Commodity selectCommodityById(Long id)
    {
        return commodityMapper.selectCommodityById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param commodity 商品
     * @return 商品
     */
    @Override
    public List<Commodity> selectCommodityList(Commodity commodity)
    {
        return commodityMapper.selectCommodityList(commodity);
    }

    /**
     * 新增商品
     * 
     * @param commodity 商品
     * @return 结果
     */
    @Override
    public int insertCommodity(Commodity commodity)
    {
        return commodityMapper.insertCommodity(commodity);
    }

    /**
     * 修改商品
     * 
     * @param commodity 商品
     * @return 结果
     */
    @Override
    public int updateCommodity(Commodity commodity)
    {
        return commodityMapper.updateCommodity(commodity);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteCommodityByIds(Long[] ids)
    {
        return commodityMapper.deleteCommodityByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteCommodityById(Long id)
    {
        return commodityMapper.deleteCommodityById(id);
    }
}
