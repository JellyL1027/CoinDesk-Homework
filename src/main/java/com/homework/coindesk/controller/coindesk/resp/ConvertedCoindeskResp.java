package com.homework.coindesk.controller.coindesk.resp;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ConvertedCoindeskResp {
    private String updatedTime;
    private String chartName;
    private List<BpiDto> bpi;

    @Data
    @Builder
    public static class BpiDto {
        private String code;
        private String codeName;
        private BigDecimal rateFloat;
    }
}