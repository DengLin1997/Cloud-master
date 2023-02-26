package com.ruoyi.base.api.factory;

import com.ruoyi.base.api.SettlementService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.data.domain.Settlement;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SettlementFallbackFactory implements FallbackFactory<SettlementService> {
    @Override
    public SettlementService create(Throwable cause) {
        return new SettlementService() {
            @Override
            public AjaxResult edit(Settlement settlement) {
                return AjaxResult.error();
            }

            @Override
            public AjaxResult getInfo(Long id) {
                return AjaxResult.error();
            }
        };
    }
}
