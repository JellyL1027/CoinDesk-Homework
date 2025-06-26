package com.homework.coindesk.repository;

import com.homework.coindesk.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {
}
