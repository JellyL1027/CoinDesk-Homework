package com.homework.coindesk.repository;

import com.homework.coindesk.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {
    Optional<Chart> findByChartName(String chartName);
}
