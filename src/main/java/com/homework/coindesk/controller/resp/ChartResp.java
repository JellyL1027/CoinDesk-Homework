package com.homework.coindesk.controller.resp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ChartResp {
    private Long id;
    private String chartName;
    private String disclaimer;
    private LocalDateTime updated;
    private List<BpiResp> bpiList;
}