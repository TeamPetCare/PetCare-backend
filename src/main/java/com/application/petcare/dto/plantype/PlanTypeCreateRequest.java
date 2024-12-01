package com.application.petcare.dto.plantype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanTypeCreateRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "PaymentInterval is required")
    private Integer paymentInterval;
    @NotBlank(message = "Disponibility is required")
    private Boolean disponibility;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Price is required")
    private Double price;
    @NotBlank(message = "Service ids is required")
    private List<Integer> serviceIds;

}
