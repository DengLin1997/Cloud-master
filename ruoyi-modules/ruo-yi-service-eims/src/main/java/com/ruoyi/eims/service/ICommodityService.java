package com.ruoyi.eims.service;

import java.util.List;
import com.ruoyi.eims.domain.Commodity;

/**
 * 商品Service接口
 * 
 * @author denglin
 * @date 2023-02-04
 */
public interface ICommodityService 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Commodity selectCommodityById(Long id);

    /**
     * 查询商品列表
     * 
     * @param commodity 商品
     * @return 商品集合
     */
    public List<Commodity> selectCommodityList(Commodity commodity);

    /**
     * 新增商品
     * 
     * @param commodity 商品
     * @return 结果
     */
    public int insertCommodity(Commodity commodity);

    /**
     * 修改商品
     * 
     * @param commodity 商品
     * @return 结果
     */
    public int updateCommodity(Commodity commodity);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteCommodityByIds(Long[] ids);

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteCommodityById(Long id);
}
