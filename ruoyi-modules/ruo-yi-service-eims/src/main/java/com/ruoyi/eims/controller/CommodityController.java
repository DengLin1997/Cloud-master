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
import com.ruoyi.eims.domain.Commodity;
import com.ruoyi.eims.service.ICommodityService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author denglin
 * @date 2023-02-04
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController extends BaseController
{
    @Autowired
    private ICommodityService commodityService;

    /**
     * 查询商品列表
     */
    @RequiresPermissions("eims:commodity:list")
    @GetMapping("/list")
    public TableDataInfo list(Commodity commodity)
    {
        startPage();
        List<Commodity> list = commodityService.selectCommodityList(commodity);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("eims:commodity:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Commodity commodity)
    {
        List<Commodity> list = commodityService.selectCommodityList(commodity);
        ExcelUtil<Commodity> util = new ExcelUtil<Commodity>(Commodity.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @RequiresPermissions("eims:commodity:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(commodityService.selectCommodityById(id));
    }

    /**
     * 新增商品
     */
    @RequiresPermissions("eims:commodity:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Commodity commodity)
    {
        return toAjax(commodityService.insertCommodity(commodity));
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("eims:commodity:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Commodity commodity)
    {
        return toAjax(commodityService.updateCommodity(commodity));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("eims:commodity:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commodityService.deleteCommodityByIds(ids));
    }
}
