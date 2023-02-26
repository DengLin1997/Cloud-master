package com.ruoyi.base.api.factory;

import com.ruoyi.base.api.StockService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.data.domain.Stock;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class StockFallbackFactory implements FallbackFactory<StockService> {
    @Override
    public StockService create(Throwable cause) {
        return new StockService() {
            @Override
            public AjaxResult edit(Stock stock) {
                return AjaxResult.error();
            }

            @Override
            public AjaxResult getInfoByBarCode(String str) {
                return AjaxResult.error();
            }
        };
    }
}
