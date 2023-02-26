package com.ruoyi.base.api;

import com.ruoyi.base.api.factory.SettlementFallbackFactory;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.data.domain.Settlement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ruoyi-service-base",
        fallbackFactory = SettlementFallbackFactory.class)
public interface SettlementService {

    /**
     *
     * @param settlement
     * @return
     */
    @PutMapping("/settlement")
    public AjaxResult edit(@RequestBody Settlement settlement);

    @GetMapping(value = "/settlement/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id);
}
