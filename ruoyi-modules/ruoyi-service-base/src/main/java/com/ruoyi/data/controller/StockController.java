package com.ruoyi.data.controller;

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
import com.ruoyi.data.domain.Stock;
import com.ruoyi.data.service.IStockService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 库存Controller
 * 
 * @author denglin
 * @date 2023-02-05
 */
@RestController
@RequestMapping("/stock")
public class StockController extends BaseController
{
    @Autowired
    private IStockService stockService;

    /**
     * 查询商品列表
     */
    @RequiresPermissions("data:stock:list")
    @GetMapping("/list")
    public TableDataInfo list(Stock stock)
    {
        startPage();
        List<Stock> list = stockService.selectStockList(stock);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("data:stock:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Stock stock)
    {
        List<Stock> list = stockService.selectStockList(stock);
        ExcelUtil<Stock> util = new ExcelUtil<Stock>(Stock.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @RequiresPermissions("data:stock:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stockService.selectStockById(id));
    }

    /**
     * 新增商品
     */
    @RequiresPermissions("data:stock:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Stock stock)
    {
        return toAjax(stockService.insertStock(stock));
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("data:stock:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Stock stock)
    {
        return toAjax(stockService.updateStock(stock));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("data:stock:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stockService.deleteStockByIds(ids));
    }

    /**
     * 获取商品详细信息通过采购单号
     */
    @RequiresPermissions("data:stock:query")
    @GetMapping(value = "/number/{str}")
    public AjaxResult getInfoByNumber(@PathVariable("str") String str)
    {
        return success(stockService.selectStockByNumber(str));
    }

    /**
     * 查询商品列表通过barCode
     */
    @RequiresPermissions("data:stock:query")
    @GetMapping(value = "/barCode/{str}")
    public AjaxResult getInfoByBarCode(@PathVariable("str") String str)
    {
        return success(stockService.selectStockByBarCode(str));
    }
}
