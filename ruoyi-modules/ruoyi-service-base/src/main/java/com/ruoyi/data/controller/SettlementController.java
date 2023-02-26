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
import com.ruoyi.data.domain.Settlement;
import com.ruoyi.data.service.ISettlementService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 结算账户Controller
 * 
 * @author denglin
 * @date 2023-02-04
 */
@RestController
@RequestMapping("/settlement")
public class SettlementController extends BaseController
{
    @Autowired
    private ISettlementService settlementService;

    /**
     * 查询结算账户列表
     */
    @RequiresPermissions("data:settlement:list")
    @GetMapping("/list")
    public TableDataInfo list(Settlement settlement)
    {
        startPage();
        List<Settlement> list = settlementService.selectSettlementList(settlement);
        return getDataTable(list);
    }

    /**
     * 导出结算账户列表
     */
    @RequiresPermissions("data:settlement:export")
    @Log(title = "结算账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Settlement settlement)
    {
        List<Settlement> list = settlementService.selectSettlementList(settlement);
        ExcelUtil<Settlement> util = new ExcelUtil<Settlement>(Settlement.class);
        util.exportExcel(response, list, "结算账户数据");
    }

    /**
     * 获取结算账户详细信息
     */
    @RequiresPermissions("data:settlement:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(settlementService.selectSettlementById(id));
    }

    /**
     * 新增结算账户
     */
    @RequiresPermissions("data:settlement:add")
    @Log(title = "结算账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Settlement settlement)
    {
        return toAjax(settlementService.insertSettlement(settlement));
    }

    /**
     * 修改结算账户
     */
    @RequiresPermissions("data:settlement:edit")
    @Log(title = "结算账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Settlement settlement)
    {
        return toAjax(settlementService.updateSettlement(settlement));
    }

    /**
     * 删除结算账户
     */
    @RequiresPermissions("data:settlement:remove")
    @Log(title = "结算账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(settlementService.deleteSettlementByIds(ids));
    }
}
