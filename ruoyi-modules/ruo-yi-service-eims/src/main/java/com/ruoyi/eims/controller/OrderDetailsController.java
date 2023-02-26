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
import com.ruoyi.eims.domain.OrderDetails;
import com.ruoyi.eims.service.IOrderDetailsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author denglin
 * @date 2023-02-05
 */
@RestController
@RequestMapping("/details")
public class OrderDetailsController extends BaseController
{
    @Autowired
    private IOrderDetailsService orderDetailsService;

    /**
     * 查询商品列表
     */
    @RequiresPermissions("eims:details:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderDetails orderDetails)
    {
        startPage();
        List<OrderDetails> list = orderDetailsService.selectOrderDetailsList(orderDetails);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("eims:details:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderDetails orderDetails)
    {
        List<OrderDetails> list = orderDetailsService.selectOrderDetailsList(orderDetails);
        ExcelUtil<OrderDetails> util = new ExcelUtil<OrderDetails>(OrderDetails.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @RequiresPermissions("eims:details:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderDetailsService.selectOrderDetailsById(id));
    }

    /**
     * 新增商品
     */
    @RequiresPermissions("eims:details:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderDetails orderDetails)
    {
        return toAjax(orderDetailsService.insertOrderDetails(orderDetails));
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("eims:details:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderDetails orderDetails)
    {
        return toAjax(orderDetailsService.updateOrderDetails(orderDetails));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("eims:details:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderDetailsService.deleteOrderDetailsByIds(ids));
    }
}
