package com.homework.coindesk.service.coindesk;

import com.homework.coindesk.controller.coindesk.resp.ConvertedCoindeskResp;
import com.homework.coindesk.service.api.CoindeskAPI;
import com.homework.coindesk.service.api.dto.CoindeskApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
public class CoindeskService {

    private final CoindeskAPI coindeskAPI;

    public CoindeskService(CoindeskAPI coindeskAPI) {
        this.coindeskAPI = coindeskAPI;
    }

    public CoindeskApiDto getCoindeskDataRaw() {
        return coindeskAPI.getCoindeskData();
    }

    // 轉換後的 Coindesk 資料
    public ConvertedCoindeskResp getCoindeskDataConverted() {
        CoindeskApiDto dto = coindeskAPI.getCoindeskData();
        return buildConvertedCoindeskResp(dto);
    }

    /**
     * 轉換coindesk API response 成 ConvertedCoindeskResp
     */
    private ConvertedCoindeskResp buildConvertedCoindeskResp(CoindeskApiDto dto) {
        List<ConvertedCoindeskResp.BpiDto> bpiDtoList = new ArrayList<>();

        dto.getBpi().forEach((code, bpi) -> bpiDtoList.add(ConvertedCoindeskResp.BpiDto.builder()
                .code(bpi.getCode())
                .codeName(Currency.getInstance(bpi.getCode()).getDisplayName())
                .rateFloat(bpi.getRateFloat())
                .build()));

        return ConvertedCoindeskResp.builder()
                .updatedTime(formatTime(dto.getTime().getUpdatedISO()))
                .chartName(dto.getChartName())
                .bpi(bpiDtoList)
                .build();
    }

    /**
     * 轉換 ISO時間格式 成 指定時間格式 yyyy/MM/dd HH:mm:ss
     */
    private String formatTime(String timeISO) {

        OffsetDateTime offsetDateTime = OffsetDateTime.parse(timeISO);

        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();

        // 轉換成指定時間格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

}
