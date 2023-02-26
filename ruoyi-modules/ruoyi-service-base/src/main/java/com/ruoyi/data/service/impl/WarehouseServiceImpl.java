package com.ruoyi.data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.data.mapper.WarehouseMapper;
import com.ruoyi.data.domain.Warehouse;
import com.ruoyi.data.service.IWarehouseService;

/**
 * 仓库管理Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-04
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService 
{
    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理主键
     * @return 仓库管理
     */
    @Override
    public Warehouse selectWarehouseById(Long id)
    {
        return warehouseMapper.selectWarehouseById(id);
    }

    /**
     * 查询仓库管理列表
     * 
     * @param warehouse 仓库管理
     * @return 仓库管理
     */
    @Override
    public List<Warehouse> selectWarehouseList(Warehouse warehouse)
    {
        return warehouseMapper.selectWarehouseList(warehouse);
    }

    /**
     * 新增仓库管理
     * 
     * @param warehouse 仓库管理
     * @return 结果
     */
    @Override
    public int insertWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.insertWarehouse(warehouse);
    }

    /**
     * 修改仓库管理
     * 
     * @param warehouse 仓库管理
     * @return 结果
     */
    @Override
    public int updateWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 批量删除仓库管理
     * 
     * @param ids 需要删除的仓库管理主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseByIds(Long[] ids)
    {
        return warehouseMapper.deleteWarehouseByIds(ids);
    }

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseById(Long id)
    {
        return warehouseMapper.deleteWarehouseById(id);
    }
}
