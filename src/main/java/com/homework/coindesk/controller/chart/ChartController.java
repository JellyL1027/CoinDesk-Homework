package com.homework.coindesk.controller.chart;

import com.homework.coindesk.controller.chart.req.ChartSaveReq;
import com.homework.coindesk.controller.chart.resp.ChartResp;
import com.homework.coindesk.service.chart.ChartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {

    private final ChartService chartService;

    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    // Create
    @PostMapping
    public ChartResp createChart(@RequestBody ChartSaveReq req) {
        return chartService.createChart(req);
    }

    // Read all
    @GetMapping
    public List<ChartResp> getAllCharts() {
        return chartService.getAllCharts();
    }

    // Read by id
    @GetMapping("/id/{id}")
    public ResponseEntity<ChartResp> getChartById(@PathVariable Long id) {
        return chartService.getChartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Read by chart name
    @GetMapping("/name/{chartName}")
    public ResponseEntity<ChartResp> getChartByChartName(@PathVariable String chartName) {
        return chartService.getChartByChartName(chartName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ChartResp> updateChart(@PathVariable Long id, @RequestBody ChartSaveReq req) {
        ChartResp chart = chartService.updateChart(id, req);
        return ResponseEntity.ok(chart);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChart(@PathVariable Long id) {
        try {
            chartService.deleteChart(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
