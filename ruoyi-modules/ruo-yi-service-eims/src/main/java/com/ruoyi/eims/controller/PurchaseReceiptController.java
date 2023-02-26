package com.ruoyi.eims.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.eims.domain.PurchaseReceiptDto;
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
import com.ruoyi.eims.domain.PurchaseReceipt;
import com.ruoyi.eims.service.IPurchaseReceiptService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 采购入库Controller
 * 
 * @author denglin
 * @date 2023-02-05
 */
@RestController
@RequestMapping("/receipt")
public class PurchaseReceiptController extends BaseController
{
    @Autowired
    private IPurchaseReceiptService purchaseReceiptService;

    /**
     * 查询采购入库列表
     */
    @RequiresPermissions("eims:receipt:list")
    @GetMapping("/list")
    public TableDataInfo list(PurchaseReceipt purchaseReceipt)
    {
        startPage();
        List<PurchaseReceipt> list = purchaseReceiptService.selectPurchaseReceiptList(purchaseReceipt);
        return getDataTable(list);
    }

    /**
     * 导出采购入库列表
     */
    @RequiresPermissions("eims:receipt:export")
    @Log(title = "采购入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PurchaseReceipt purchaseReceipt)
    {
        List<PurchaseReceipt> list = purchaseReceiptService.selectPurchaseReceiptList(purchaseReceipt);
        ExcelUtil<PurchaseReceipt> util = new ExcelUtil<PurchaseReceipt>(PurchaseReceipt.class);
        util.exportExcel(response, list, "采购入库数据");
    }

    /**
     * 获取采购入库详细信息
     */
    @RequiresPermissions("eims:receipt:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(purchaseReceiptService.selectPurchaseReceiptById(id));
    }

    /**
     * 新增采购入库
     */
    @RequiresPermissions("eims:receipt:add")
    @Log(title = "采购入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PurchaseReceiptDto purchaseReceiptDto)
    {
        return toAjax(purchaseReceiptService.insertPurchaseReceipt(purchaseReceiptDto));
    }

    /**
     * 修改采购入库
     */
    @RequiresPermissions("eims:receipt:edit")
    @Log(title = "采购入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PurchaseReceipt purchaseReceipt)
    {
        return toAjax(purchaseReceiptService.updatePurchaseReceipt(purchaseReceipt));
    }

    /**
     * 删除采购入库
     */
    @RequiresPermissions("eims:receipt:remove")
    @Log(title = "采购入库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseReceiptService.deletePurchaseReceiptByIds(ids));
    }
}
