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
import com.ruoyi.eims.domain.Refund;
import com.ruoyi.eims.service.IRefundService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 退款Controller
 * 
 * @author denglin
 * @date 2023-02-06
 */
@RestController
@RequestMapping("/refund")
public class RefundController extends BaseController
{
    @Autowired
    private IRefundService refundService;

    /**
     * 查询退款列表
     */
    @RequiresPermissions("eims:refund:list")
    @GetMapping("/list")
    public TableDataInfo list(Refund refund)
    {
        startPage();
        List<Refund> list = refundService.selectRefundList(refund);
        return getDataTable(list);
    }

    /**
     * 导出退款列表
     */
    @RequiresPermissions("eims:refund:export")
    @Log(title = "退款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Refund refund)
    {
        List<Refund> list = refundService.selectRefundList(refund);
        ExcelUtil<Refund> util = new ExcelUtil<Refund>(Refund.class);
        util.exportExcel(response, list, "退款数据");
    }

    /**
     * 获取退款详细信息
     */
    @RequiresPermissions("eims:refund:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(refundService.selectRefundById(id));
    }

    /**
     * 新增退款
     */
    @RequiresPermissions("eims:refund:add")
    @Log(title = "退款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Refund refund)
    {
        return toAjax(refundService.insertRefund(refund));
    }

    /**
     * 修改退款
     */
    @RequiresPermissions("eims:refund:edit")
    @Log(title = "退款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Refund refund)
    {
        return toAjax(refundService.updateRefund(refund));
    }

    /**
     * 删除退款
     */
    @RequiresPermissions("eims:refund:remove")
    @Log(title = "退款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(refundService.deleteRefundByIds(ids));
    }
}
