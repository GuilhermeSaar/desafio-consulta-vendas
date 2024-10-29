package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SummaryMinProjection;

public class SummaryMinDTO {

    private String sellerName;
    private Double total;

    // construtores


    public SummaryMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SummaryMinDTO(SummaryMinProjection projection) {
        this.sellerName = projection.getSellerName();
        this.total = projection.getTotal();
    }

    // getters


    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
