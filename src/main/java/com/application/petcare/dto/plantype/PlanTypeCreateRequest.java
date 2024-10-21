package com.application.petcare.dto.plantype;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanTypeCreateRequest {
    private String name;
    private Integer paymentInterval;
}
