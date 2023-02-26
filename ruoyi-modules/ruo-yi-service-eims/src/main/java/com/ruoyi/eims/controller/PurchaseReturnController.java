package com.ruoyi.eims.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.eims.dto.PurchaseReturnDto;
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
import com.ruoyi.eims.domain.PurchaseReturn;
import com.ruoyi.eims.service.IPurchaseReturnService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 采购退货Controller
 * 
 * @author denglin
 * @date 2023-02-06
 */
@RestController
@RequestMapping("/return")
public class PurchaseReturnController extends BaseController
{
    @Autowired
    private IPurchaseReturnService purchaseReturnService;

    /**
     * 查询采购退货列表
     */
    @RequiresPermissions("eims:return:list")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseReturn purchaseReturn)
    {
        startPage();
        List<PurchaseReturn> list = purchaseReturnService.selectPurchaseReturnList(purchaseReturn);
        return getDataTable(list);
    }

    /**
     * 导出采购退货列表
     */
    @RequiresPermissions("eims:return:export")
    @Log(title = "采购退货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseReturn purchaseReturn)
    {
        List<PurchaseReturn> list = purchaseReturnService.selectPurchaseReturnList(purchaseReturn);
        ExcelUtil<PurchaseReturn> util = new ExcelUtil<PurchaseReturn>(PurchaseReturn.class);
        util.exportExcel(response, list, "采购退货数据");
    }

    /**
     * 获取采购退货详细信息
     */
    @RequiresPermissions("eims:return:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(purchaseReturnService.selectPurchaseReturnById(id));
    }

    /**
     * 新增采购退货
     */
    @RequiresPermissions("eims:return:add")
    @Log(title = "采购退货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseReturnDto purchaseReturnDto)
    {
        return toAjax(purchaseReturnService.insertPurchaseReturn(purchaseReturnDto));
    }

    /**
     * 修改采购退货
     */
    @RequiresPermissions("eims:return:edit")
    @Log(title = "采购退货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseReturn purchaseReturn)
    {
        return toAjax(purchaseReturnService.updatePurchaseReturn(purchaseReturn));
    }

    /**
     * 删除采购退货
     */
    @RequiresPermissions("eims:return:remove")
    @Log(title = "采购退货", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseReturnService.deletePurchaseReturnByIds(ids));
    }
}
