package com.homework.coindesk.controller.resp;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BpiResp {
    private Long id;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rateFloat;
}