package com.ruoyi.data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.data.mapper.StockMapper;
import com.ruoyi.data.domain.Stock;
import com.ruoyi.data.service.IStockService;

/**
 * 商品Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-05
 */
@Service
public class StockServiceImpl implements IStockService 
{
    @Autowired
    private StockMapper stockMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public Stock selectStockById(Long id)
    {
        return stockMapper.selectStockById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param stock 商品
     * @return 商品
     */
    @Override
    public List<Stock> selectStockList(Stock stock)
    {
        return stockMapper.selectStockList(stock);
    }

    /**
     * 新增商品
     * 
     * @param stock 商品
     * @return 结果
     */
    @Override
    public int insertStock(Stock stock)
    {
        return stockMapper.insertStock(stock);
    }

    /**
     * 修改商品
     * 
     * @param stock 商品
     * @return 结果
     */
    @Override
    public int updateStock(Stock stock)
    {
        return stockMapper.updateStock(stock);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteStockByIds(Long[] ids)
    {
        return stockMapper.deleteStockByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteStockById(Long id)
    {
        return stockMapper.deleteStockById(id);
    }

    @Override
    public Stock selectStockByNumber(String str) {
        return stockMapper.selectStockByNumber(str);
    }

    @Override
    public Stock selectStockByBarCode(String str) {
        return stockMapper.selectStockByBarCode(str);
    }
}
