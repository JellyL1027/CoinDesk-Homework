package com.homework.coindesk.controller;

import com.homework.coindesk.controller.req.ChartSaveReq;
import com.homework.coindesk.controller.resp.ChartResp;
import com.homework.coindesk.entity.Chart;
import com.homework.coindesk.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

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
        chartService.deleteChart(id);
        return ResponseEntity.noContent().build();
    }

}
