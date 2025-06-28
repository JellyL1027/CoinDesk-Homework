package com.homework.coindesk.controller.coindesk;

import com.homework.coindesk.controller.coindesk.resp.ConvertedCoindeskResp;
import com.homework.coindesk.service.coindesk.CoindeskService;
import com.homework.coindesk.service.api.dto.CoindeskApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    private final CoindeskService coindeskService;

    public CoindeskController(CoindeskService coindeskService) {
        this.coindeskService = coindeskService;
    }

    // 取得原始 Coindesk 資料
    @GetMapping("/raw")
    public ResponseEntity<CoindeskApiDto> getCoindeskDataRaw() {
        CoindeskApiDto response = coindeskService.getCoindeskDataRaw();
        return ResponseEntity.ok(response);
    }

    // 轉換後的 Coindesk 資料
    @GetMapping("/converted")
    public ResponseEntity<ConvertedCoindeskResp> getCoindeskDataConverted() {
        ConvertedCoindeskResp response = coindeskService.getCoindeskDataConverted();
        return ResponseEntity.ok(response);
    }

}
