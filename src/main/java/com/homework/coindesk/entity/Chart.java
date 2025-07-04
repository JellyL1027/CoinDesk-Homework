package com.homework.coindesk.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * SQL 指令
 * CREATE TABLE CHART (
 *     id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *     updated TIMESTAMP,
 *     disclaimer VARCHAR(255),
 *     chart_name VARCHAR(50)
 * );
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CHART")
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "disclaimer")
    private String disclaimer;

    @Column(name = "chart_name")
    private String chartName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chart", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<Bpi> bpiList;

}
