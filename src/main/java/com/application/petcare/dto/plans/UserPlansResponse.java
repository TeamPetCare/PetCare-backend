package com.application.petcare.dto.plans;

import com.application.petcare.entities.PlanType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@Setter
@Data
@AllArgsConstructor
public class UserPlansResponse {
    private Integer id;
    private LocalDateTime subscriptionDate;
    private String name;
    private Double price;
    private Boolean active;
    private int renewal;
    private Boolean hasDiscount;
    private String description;
    private PlanType planType;
    private List<Integer> servicesIds;
    private List<Integer> repeatQuantity;
    private List<Integer> paymentIds;
    private List<Integer> petIds;
}
