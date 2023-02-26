package com.ruoyi.data.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.data.mapper.SupplierMapper;
import com.ruoyi.data.domain.Supplier;
import com.ruoyi.data.service.ISupplierService;

/**
 * 供应商管理Service业务层处理
 * 
 * @author denglin
 * @date 2023-02-04
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询供应商管理
     * 
     * @param id 供应商管理主键
     * @return 供应商管理
     */
    @Override
    public Supplier selectSupplierById(Long id)
    {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询供应商管理列表
     * 
     * @param supplier 供应商管理
     * @return 供应商管理
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除供应商管理
     * 
     * @param ids 需要删除的供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(Long[] ids)
    {
        return supplierMapper.deleteSupplierByIds(ids);
    }

    /**
     * 删除供应商管理信息
     * 
     * @param id 供应商管理主键
     * @return 结果
     */
    @Override
    public int deleteSupplierById(Long id)
    {
        return supplierMapper.deleteSupplierById(id);
    }
}
