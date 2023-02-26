package com.ruoyi.eims.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.eims.domain.SalesOrder;
import com.ruoyi.eims.service.ISalesOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author denglin
 * @date 2023-02-06
 */
@RestController
@RequestMapping("/SalesOrder")
public class SalesOrderController extends BaseController
{
    @Autowired
    private ISalesOrderService salesOrderService;

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("eims:SalesOrder:list")
    @GetMapping("/list")
    public TableDataInfo list(SalesOrder salesOrder)
    {
        startPage();
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @RequiresPermissions("eims:SalesOrder:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SalesOrder salesOrder)
    {
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        ExcelUtil<SalesOrder> util = new ExcelUtil<SalesOrder>(SalesOrder.class);
        util.exportExcel(response, list, "销售订单数据");
    }

    /**
     * 获取销售订单详细信息
     */
    @RequiresPermissions("eims:SalesOrder:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(salesOrderService.selectSalesOrderById(id));
    }

    /**
     * 新增销售订单
     */
    @RequiresPermissions("eims:SalesOrder:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SalesOrder salesOrder)
    {
        return toAjax(salesOrderService.insertSalesOrder(salesOrder));
    }

    /**
     * 修改销售订单
     */
    @RequiresPermissions("eims:SalesOrder:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SalesOrder salesOrder)
    {
        return toAjax(salesOrderService.updateSalesOrder(salesOrder));
    }

    /**
     * 删除销售订单
     */
    @RequiresPermissions("eims:SalesOrder:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(salesOrderService.deleteSalesOrderByIds(ids));
    }
}
