package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.ReportMinProjection;

import java.time.LocalDate;

public class ReportMinDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String name;


    // contrutores
    public ReportMinDTO(Long id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = name;
    }

    // contrutores
    public ReportMinDTO(ReportMinProjection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.amount = projection.getAmount();
        this.name = projection.getName();
    }


    // getters

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }
}
