package com.ruoyi.base.api;

import com.ruoyi.base.api.factory.SettlementFallbackFactory;
import com.ruoyi.base.api.factory.StockFallbackFactory;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.data.domain.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ruoyi-service-base",
        fallbackFactory = StockFallbackFactory.class)
public interface StockService {

    /**
     *
     * @param stock
     * @return
     */
    @PutMapping("/stock")
    public AjaxResult edit(@RequestBody Stock stock);

    @GetMapping(value = "/stock/barCode/{str}")
    public AjaxResult getInfoByBarCode(@PathVariable("str") String str);
}
