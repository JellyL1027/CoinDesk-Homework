package com.homework.coindesk.service.coindesk;

import com.homework.coindesk.controller.coindesk.resp.ConvertedCoindeskResp;
import com.homework.coindesk.service.api.dto.CoindeskApiDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CoindeskServiceTest {
    private final CoindeskService service = new CoindeskService(null);

    // 正常資料測試
    @Test
    public void testConvertNormal() {
        CoindeskApiDto dto = createMockDto("USD", "2025-01-01T10:00:00+00:00", BigDecimal.TEN);
        dto.getBpi().put("EUR", createBpi("EUR", BigDecimal.ONE));

        ConvertedCoindeskResp resp = service.buildConvertedCoindeskResp(dto);

        assertEquals(2, resp.getBpi().size());
        assertEquals("2025/01/01 10:00:00", resp.getUpdatedTime());
    }

    // 傳入 null 測試
    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullDto() {
        service.buildConvertedCoindeskResp(null);
    }

    // 傳入錯誤幣種測試
    @Test
    public void testConvertUnknownCurrency() {
        CoindeskApiDto dto = createMockDto("ZZZ", "2025-01-01T10:00:00+00:00", BigDecimal.ONE);
        ConvertedCoindeskResp resp = service.buildConvertedCoindeskResp(dto);

        assertEquals("未知幣別", resp.getBpi().get(0).getCodeName());
    }

    private CoindeskApiDto createMockDto(String code, String isoTime, BigDecimal rate) {
        CoindeskApiDto dto = new CoindeskApiDto();
        dto.setChartName("Bitcoin");

        CoindeskApiDto.TimeDto timeDto = new CoindeskApiDto.TimeDto();
        timeDto.setUpdatedISO(isoTime);
        dto.setTime(timeDto);

        Map<String, CoindeskApiDto.BpiDto> map = new HashMap<>();
        map.put(code, createBpi(code, rate));
        dto.setBpi(map);

        return dto;
    }

    private CoindeskApiDto.BpiDto createBpi(String code, BigDecimal rate) {
        CoindeskApiDto.BpiDto bpi = new CoindeskApiDto.BpiDto();
        bpi.setCode(code);
        bpi.setRateFloat(rate);
        return bpi;
    }

}
