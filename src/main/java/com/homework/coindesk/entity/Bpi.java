package com.homework.coindesk.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * SQL 指令
 * CREATE TABLE BPI (
 *     id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *     chart_id BIGINT,
 *     code VARCHAR(5),
 *     symbol VARCHAR(20),
 *     rate VARCHAR(20),
 *     description VARCHAR(255),
 *     rate_float DECIMAL(15,8),
 *     FOREIGN KEY (chart_id) REFERENCES CHART (id)
 * );
 */
@Entity
@Table(name = "BPI")
public class Bpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "rate")
    private String rate;

    @Column(name = "description")
    private String description;

    @Column(name = "rate_float")
    private BigDecimal rateFloat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chart_id")
    private Chart chart;

}
