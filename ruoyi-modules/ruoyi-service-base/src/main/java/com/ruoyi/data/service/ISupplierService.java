package com.ruoyi.data.service;

import java.util.List;
import com.ruoyi.data.domain.Supplier;

/**
 * 供应商管理Service接口
 * 
 * @author denglin
 * @date 2023-02-04
 */
public interface ISupplierService 
{
    /**
     * 查询供应商管理
     * 
     * @param id 供应商管理主键
     * @return 供应商管理
     */
    public Supplier selectSupplierById(Long id);

    /**
     * 查询供应商管理列表
     * 
     * @param supplier 供应商管理
     * @return 供应商管理集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商管理
     * 
     * @param supplier 供应商管理
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除供应商管理
     * 
     * @param ids 需要删除的供应商管理主键集合
     * @return 结果
     */
    public int deleteSupplierByIds(Long[] ids);

    /**
     * 删除供应商管理信息
     * 
     * @param id 供应商管理主键
     * @return 结果
     */
    public int deleteSupplierById(Long id);
}
