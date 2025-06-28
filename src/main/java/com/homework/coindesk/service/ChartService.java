package com.homework.coindesk.service;

import com.homework.coindesk.controller.req.ChartSaveReq;
import com.homework.coindesk.controller.resp.BpiResp;
import com.homework.coindesk.controller.resp.ChartResp;
import com.homework.coindesk.entity.Bpi;
import com.homework.coindesk.entity.Chart;
import com.homework.coindesk.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    // Create
    public ChartResp createChart(ChartSaveReq req) {
        Chart chart = buildChartEntity(req);
        Chart saved = chartRepository.save(chart);
        return toDto(saved);
    }

    // Read all
    public List<ChartResp> getAllCharts() {
        return chartRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Read by id
    public Optional<ChartResp> getChartById(Long id) {
        return chartRepository.findById(id).map(this::toDto);
    }

    // Read by chart name
    public Optional<ChartResp> getChartByChartName(String chartName) {
        return chartRepository.findByChartName(chartName).map(this::toDto);
    }

    // Update
    public ChartResp updateChart(Long id, ChartSaveReq req) {
        return chartRepository.findById(id)
                .map(chart -> {
                    OffsetDateTime odt = OffsetDateTime.parse(req.getTime().getUpdatedISO());

                    chart.setUpdated(odt.toLocalDateTime());
                    chart.setDisclaimer(req.getDisclaimer());
                    chart.setChartName(req.getChartName());

                    List<Bpi> bpiList = new ArrayList<>();
                    req.getBpi().forEach((code, bpiDto) -> {
                        Bpi bpi = buildBpiEntity(bpiDto, chart);
                        bpi.setChart(chart);
                        bpiList.add(bpi);
                    });

                    chart.setBpiList(bpiList);

                    return toDto(chartRepository.save(chart));
                })
                .orElseThrow(() -> new RuntimeException("Chart with id " + id + " not found"));
    }

    // Delete
    public void deleteChart(Long id) {
        chartRepository.deleteById(id);
    }

    /**
     * 轉換Create request 成 Chart Entity
     */
    private Chart buildChartEntity(ChartSaveReq req) {
        OffsetDateTime odt = OffsetDateTime.parse(req.getTime().getUpdatedISO());

        Chart chart = Chart.builder()
                .disclaimer(req.getDisclaimer())
                .chartName(req.getChartName())
                .updated(odt.toLocalDateTime())
                .build();

        List<Bpi> bpiList = new ArrayList<>();
        req.getBpi().forEach((code, bpiDto) -> {
            Bpi bpi = buildBpiEntity(bpiDto, chart);
            bpi.setChart(chart);
            bpiList.add(bpi);
        });

        chart.setBpiList(bpiList);
        return chart;
    }

    /**
     * 轉換create request 成 Bpi Entity
     */
    private Bpi buildBpiEntity(ChartSaveReq.BpiDto bpiDto, Chart chart) {
        return Bpi.builder()
                .code(bpiDto.getCode())
                .symbol(bpiDto.getSymbol())
                .rate(bpiDto.getRate())
                .description(bpiDto.getDescription())
                .rateFloat(bpiDto.getRate_float())
                .chart(chart)
                .build();
    }

    private ChartResp toDto(Chart chart) {
        ChartResp chartResp = ChartResp.builder()
                .id(chart.getId())
                .chartName(chart.getChartName())
                .disclaimer(chart.getDisclaimer())
                .updated(chart.getUpdated())
                .build();

        List<BpiResp> bpiRespList = chart.getBpiList().stream()
                .map(bpi -> {
                    BpiResp bpiResp = BpiResp.builder()
                            .code(bpi.getCode())
                            .symbol(bpi.getSymbol())
                            .rate(bpi.getRate())
                            .description(bpi.getDescription())
                            .rateFloat(bpi.getRateFloat())
                            .build();
                    return bpiResp;
                }).collect(Collectors.toList());

        chartResp.setBpiList(bpiRespList);
        return chartResp;
    }


}
