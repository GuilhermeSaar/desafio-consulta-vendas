package com.devsuperior.dsmeta.projection;

import com.devsuperior.dsmeta.entities.Seller;

import java.time.LocalDate;


public interface ReportMinProjection {

    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}
