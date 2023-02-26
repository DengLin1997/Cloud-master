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
import com.ruoyi.eims.domain.PaymentDoc;
import com.ruoyi.eims.service.IPaymentDocService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 付快但Controller
 * 
 * @author denglin
 * @date 2023-02-05
 */
@RestController
@RequestMapping("/doc")
public class PaymentDocController extends BaseController
{
    @Autowired
    private IPaymentDocService paymentDocService;

    /**
     * 查询付快但列表
     */
    @RequiresPermissions("eims:doc:list")
    @GetMapping("/list")
    public TableDataInfo list(PaymentDoc paymentDoc)
    {
        startPage();
        List<PaymentDoc> list = paymentDocService.selectPaymentDocList(paymentDoc);
        return getDataTable(list);
    }

    /**
     * 导出付快但列表
     */
    @RequiresPermissions("eims:doc:export")
    @Log(title = "付快但", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentDoc paymentDoc)
    {
        List<PaymentDoc> list = paymentDocService.selectPaymentDocList(paymentDoc);
        ExcelUtil<PaymentDoc> util = new ExcelUtil<PaymentDoc>(PaymentDoc.class);
        util.exportExcel(response, list, "付快但数据");
    }

    /**
     * 获取付快但详细信息
     */
    @RequiresPermissions("eims:doc:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(paymentDocService.selectPaymentDocById(id));
    }

    /**
     * 新增付快但
     */
    @RequiresPermissions("eims:doc:add")
    @Log(title = "付快但", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentDoc paymentDoc)
    {
        return toAjax(paymentDocService.insertPaymentDoc(paymentDoc));
    }

    /**
     * 修改付快但
     */
    @RequiresPermissions("eims:doc:edit")
    @Log(title = "付快但", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentDoc paymentDoc)
    {
        return toAjax(paymentDocService.updatePaymentDoc(paymentDoc));
    }

    /**
     * 删除付快但
     */
    @RequiresPermissions("eims:doc:remove")
    @Log(title = "付快但", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(paymentDocService.deletePaymentDocByIds(ids));
    }
}
