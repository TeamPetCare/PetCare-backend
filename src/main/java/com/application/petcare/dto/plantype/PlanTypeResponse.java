package com.application.petcare.dto.plantype;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanTypeResponse {
    private Integer id;
    private String name;
    private Integer paymentInterval;
    private Boolean disponibility;
    private String description;
    private Double price;
    private List<Integer> serviceIds;
}
