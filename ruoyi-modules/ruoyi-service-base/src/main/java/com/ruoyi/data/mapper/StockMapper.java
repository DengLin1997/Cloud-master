package com.ruoyi.data.mapper;

import java.util.List;
import com.ruoyi.data.domain.Stock;

/**
 * 商品Mapper接口
 * 
 * @author denglin
 * @date 2023-02-05
 */
public interface StockMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Stock selectStockById(Long id);

    /**
     * 查询商品列表
     * 
     * @param stock 商品
     * @return 商品集合
     */
    public List<Stock> selectStockList(Stock stock);

    /**
     * 新增商品
     * 
     * @param stock 商品
     * @return 结果
     */
    public int insertStock(Stock stock);

    /**
     * 修改商品
     * 
     * @param stock 商品
     * @return 结果
     */
    public int updateStock(Stock stock);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteStockById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStockByIds(Long[] ids);

    Stock selectStockByNumber(String str);

    Stock selectStockByBarCode(String str);
}
